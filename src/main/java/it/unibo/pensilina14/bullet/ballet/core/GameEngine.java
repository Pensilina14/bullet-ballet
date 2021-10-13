package it.unibo.pensilina14.bullet.ballet.core;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import it.unibo.pensilina14.bullet.ballet.input.Command;
import it.unibo.pensilina14.bullet.ballet.input.Controller;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.CharacterHitsPickupObjEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.PlayerHitsEnemyEvent;

public class GameEngine implements Controller, GameEventListener {
	
	private static final int QUEUE_CAPACITY = 100;
	
	private final long period = 20;
	
	private MapScene view;
	private GameState gameState;
	private final BlockingQueue<Command> cmdQueue;
	private final List<GameEvent> eventQueue;
	
	public GameEngine() {
		this.cmdQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
		this.eventQueue = new LinkedList<>();
	}
	
	public void setup() {
		this.gameState = new GameState();
		this.view = new MapScene();
		/*
		 * TODO: call rengo.
		 */
	}
	
	public void mainLoop() {
	    long lastTime = System.currentTimeMillis();
		while (!this.gameState.isGameOver()) {
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
			cmd.execute(this.gameState);
		}
	}
	
	private void updateGame(final int elapsed) {
		this.gameState.update(elapsed);
		this.checkEvents();
	}
	
	private void render() {
		this.view.draw();
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
		final Environment env = this.gameState.getGameEnvironment();
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
				final double actualHealth = player.getHealth();
				// TODO: player.setHealth(actualHealth - CONFLICT_DAMAGE);
			}
		});
		this.eventQueue.clear();
	}
}
