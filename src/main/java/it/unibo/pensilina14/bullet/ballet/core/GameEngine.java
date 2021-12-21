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
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
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
	
	//private final long period = 1000; // 20 ms = 50 FPS 
	
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
			this.view.get().setup(this);
			AppLogger.getAppLogger().debug("View was empty so it was initialized.");
		} else {
			this.view.get().setup(this);
			this.view.get().setInputController(this);
			AppLogger.getAppLogger().debug("View input controller set.");
		}

		if (this.gameState.isEmpty()) {
			this.gameState = Optional.of(new GameState());
			this.gameState.get().setEventListener(this);
			AppLogger.getAppLogger().debug("There was no game state, new one instantiated.");
		} else {
			this.gameState.get().setEventListener(this);
			AppLogger.getAppLogger().debug("Game state present, event listener set only.");
		}
        AppLogger.getAppLogger().debug("Engine setup done.. Starting main loop.");
        this.mainLoop();
	}
	
	public final void mainLoop() {
		while (!this.gameState.get().isGameOver()) {
			this.processInput();
			AppLogger.getAppLogger().debug("Input processed.");
			this.updateGame();
			AppLogger.getAppLogger().debug("Game model updated.");
			this.render();
			AppLogger.getAppLogger().debug("Rendering ultimated.");
		}
		// GAME OVER
	}
	
	public final void processInput() {
		final Command cmd = this.cmdQueue.poll();
		if (cmd != null) {
			cmd.execute(this.gameState.get());
		}
	}
	
	public void updateGame() {
		this.gameState.get().update();
		this.checkEvents();
	}
	
	public final void render() {
		this.view.get().draw();
	}
	
	@Override
	public final void notifyCommand(final Command cmd) {
        this.cmdQueue.add(cmd);
	}

	@Override
	public final void notifyEvent(final GameEvent e) {
		this.eventQueue.add(e);
	}
	
	private void checkEvents() {
		final Environment env = this.gameState.get().getGameEnvironment();
		this.eventQueue.stream().forEach(e -> {
			if (e instanceof CharacterHitsPickupObjEvent) {
				characterHitsPickUpObjEventHandler(env, e);
			} else if (e instanceof PlayerHitsEnemyEvent) {
				playerHitsEnemyEventHandler(env, e);

			} else if (e instanceof PlayerHitsObstacleEvent) {
				playerHitsObstacleEventHandler(env, e);
			} else if (e instanceof EnemyHitsObstacleEvent) {
				AppLogger.getAppLogger().info("enemy hits something");
				/*
				final Characters enemy = ((EnemyHitsObstacleEvent) e).getEnemy();
				final PhysicalObject obstacle = ((EnemyHitsObstacleEvent) e).getObstacle();
				// TODO: enemy.setHealth(enemy.getHealth() - obstacle.COLLISION_DAMAGE);
				 * */
			} 
		});
		this.eventQueue.clear();
	}

	private void playerHitsObstacleEventHandler(final Environment env, final GameEvent e) {
		final Player player = ((PlayerHitsObstacleEvent) e).getPlayer();
		//final PhysicalObject obstacle = ((PlayerHitsObstacleEvent) e).getObstacle();
		if (!player.isAlive()) {
			env.deleteObjByPosition(new ImmutablePosition2Dimpl(player.getPosition().get().getX()
					, player.getPosition().get().getY()));
		}
		// TODO: player.setHealth(player.getHealth() - obstacle.COLLISION_DAMAGE);
		AppLogger.getAppLogger().info("player hits obstacle");
	}

	private void playerHitsEnemyEventHandler(final Environment env, final GameEvent e) {
		final Player player = ((PlayerHitsEnemyEvent) e).getPlayer();
		final Enemy enemy = ((PlayerHitsEnemyEvent) e).getEnemy();
		// TODO: player.setHealth(player.getHealth() - enemy.COLLISION_DAMAGE);
		// TODO: enemy.setHealth(enemy.getHealth() - player.COLLISION_DAMAGE);
		if (!player.isAlive()) {
			env.deleteObjByPosition(new ImmutablePosition2Dimpl(player.getPosition().get().getX()
					, player.getPosition().get().getY()));
		}
		
		if (!enemy.isAlive()) {
			env.deleteObjByPosition(new ImmutablePosition2Dimpl(enemy.getPosition().get().getX()
					, enemy.getPosition().get().getY()));
		}
		// TODO: player.setHealth(player.getHealth() - enemy.COLLISION_DAMAGE);
		// TODO: enemy.setHealth(enemy.getHealth() - player.COLLISION_DAMAGE);
		AppLogger.getAppLogger().info("player hits enemy");
	}

	private void characterHitsPickUpObjEventHandler(final Environment env, final GameEvent e) {
		// Apply item effect on character
		((CharacterHitsPickupObjEvent) e).getPickupObj()
			.getEffect()
			.applyEffect(((CharacterHitsPickupObjEvent) e).getCharacter());
		// Update environment
		final MutablePosition2D pickupPos = ((CharacterHitsPickupObjEvent)e).getPickupObj().getPosition().get();
		env.deleteObjByPosition(new ImmutablePosition2Dimpl(pickupPos.getX(), pickupPos.getY()));
		AppLogger.getAppLogger().info("player hits item");
	}
}
