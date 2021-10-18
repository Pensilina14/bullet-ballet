package it.unibo.pensilina14.bullet.ballet.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.GameView;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import it.unibo.pensilina14.bullet.ballet.input.Command;
import it.unibo.pensilina14.bullet.ballet.input.Controller;
import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.CharacterHitsPickupObjEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.EnemyHitsObstacleEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.PlayerHitsEnemyEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.PlayerHitsObstacleEvent;

public class GameEngine implements Controller, GameEventListener {
	
	private static final int QUEUE_CAPACITY = 100;
	
	private final long period = 20;
	
	private Optional<GameView> view;
	private Optional<GameState> gameState;
	private final BlockingQueue<Command> cmdQueue;
	private final List<GameEvent> eventQueue;
	
	public GameEngine() {
		this.cmdQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
		this.eventQueue = new LinkedList<>();
		this.view = Optional.empty();
		this.gameState = Optional.empty();
	}
	
	public GameEngine(final GameView view, final GameState game) {
		this.cmdQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
		this.eventQueue = new LinkedList<>();
		this.view = Optional.of(view);
		this.gameState = Optional.of(game);
	}
	
	public final void setup() {
		if (this.view.isEmpty()) {
			this.view = Optional.of(new MapScene(this.gameState.get(), this));
			this.view.get().setup();
		} else {
			this.view.get().setInputController(this);
		}

		if (this.gameState.isEmpty()) {
			this.gameState = Optional.of(new GameState());
			this.gameState.get().setEventListener(this);
		} else {
			this.gameState.get().setEventListener(this);
		}
	}
	
	public void mainLoop() {
	    long lastTime = System.currentTimeMillis();
		while (!this.gameState.get().isGameOver()) {
			final long current = System.currentTimeMillis();
			final int elapsed = (int) (current - lastTime);
			this.processInput();
			this.updateGame(elapsed);
			this.render();
			this.waitForNextFrame(elapsed);
			lastTime = current;
		}
		// GAME OVER
	}
	
	private void waitForNextFrame(final long current) {
		final long dt = System.currentTimeMillis() - current;
		if (dt < this.period) {
			try {
				Thread.sleep(period - dt);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void processInput() {
		final Command cmd = this.cmdQueue.poll();
		if (cmd != null) {
			cmd.execute(this.gameState.get());
		}
	}
	
	private void updateGame(final int elapsed) {
		this.gameState.get().update(elapsed);
		this.checkEvents();
	}
	
	private void render() {
		this.view.get().draw();
	}
	
	@Override
	public void notifyCommand(final Command cmd) {
        this.cmdQueue.add(cmd);
	}

	@Override
	public void notifyEvent(final GameEvent e) {
		this.eventQueue.add(e);
	}
	
	private void checkEvents() {
		final Environment env = this.gameState.get().getGameEnvironment();
		this.eventQueue.stream().forEach(e -> {
			if (e instanceof CharacterHitsPickupObjEvent) {
				// Apply item effect on character
				((CharacterHitsPickupObjEvent) e).getPickupObj()
					.getEffect()
					.applyEffect(((CharacterHitsPickupObjEvent) e).getCharacter());
				// Update environment
				final MutablePosition2D pickupPos = ((CharacterHitsPickupObjEvent) e).getPickupObj().getPosition();
				env.deleteObjByPosition(new ImmutablePosition2Dimpl(pickupPos.getX(), pickupPos.getY()));
			} else if (e instanceof PlayerHitsEnemyEvent) {
				final Player player = ((PlayerHitsEnemyEvent) e).getPlayer();
				final Enemy enemy = ((PlayerHitsEnemyEvent) e).getEnemy();
				// TODO: player.setHealth(player.getHealth() - enemy.COLLISION_DAMAGE);
				// TODO: enemy.setHealth(enemy.getHealth() - player.COLLISION_DAMAGE);
			} else if (e instanceof PlayerHitsObstacleEvent) {
				final Characters player = ((PlayerHitsObstacleEvent) e).getPlayer();
				final PhysicalObject obstacle = ((PlayerHitsObstacleEvent) e).getObstacle();
				// TODO: player.setHealth(player.getHealth() - obstacle.COLLISION_DAMAGE);
			} else if (e instanceof EnemyHitsObstacleEvent) {
				final Characters enemy = ((EnemyHitsObstacleEvent) e).getEnemy();
				final PhysicalObject obstacle = ((EnemyHitsObstacleEvent) e).getObstacle();
				// TODO: enemy.setHealth(enemy.getHealth() - obstacle.COLLISION_DAMAGE);
			}
		});
		this.eventQueue.clear();
	}
}
