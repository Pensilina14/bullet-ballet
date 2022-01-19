package it.unibo.pensilina14.bullet.ballet.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import it.unibo.pensilina14.bullet.ballet.AnimationTimerImpl;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.core.controller.ModelController;
import it.unibo.pensilina14.bullet.ballet.core.controller.ModelControllerImpl;
import it.unibo.pensilina14.bullet.ballet.core.controller.ViewController;
import it.unibo.pensilina14.bullet.ballet.core.controller.ViewControllerImpl;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import it.unibo.pensilina14.bullet.ballet.input.Command;
import it.unibo.pensilina14.bullet.ballet.input.Controller;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.EnemyHitsPlatformEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.PlayerHitsEnemyEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.PlayerHitsItemEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.PlayerHitsObstacleEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.PlayerHitsPlatformEvent;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleImpl;
import javafx.animation.AnimationTimer;

/**
 * Manages a variety of game aspects such as input commands processing, event handling 
 * and putting into communication model and view.
 * 
 * This class could be considered the core of the game itself.
 *
 */
public class GameEngine implements Controller, GameEventListener {
	/**
	 * Constant used to define command queue capacity.
	 */
	private static final int QUEUE_CAPACITY = 100;
	
	//private final long period = 1000; // 20 ms = 50 FPS 
	
	private Optional<ViewController> viewController;
	private Optional<ModelController> modelController;
	private final BlockingQueue<Command> cmdQueue;
	/**
	 * Data structure, for instance a {@link List}, whose goal is to
	 * store all the incoming events from the model and view (?)
	 */
	private final List<GameEvent> eventQueue;
	/**
	 * This is the timer that temporizes the program.
	 */
	private final Optional<AnimationTimer> timer;
	
	/*
	 * CONSTRUCTORS
	 */
	public GameEngine() {
		this.cmdQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
		this.eventQueue = new LinkedList<>();
		this.viewController = Optional.empty();
		this.modelController = Optional.empty();
		this.timer = Optional.of(new AnimationTimerImpl(this));
	}
	
	public GameEngine(final ViewController view, final ModelController game) {
		this.cmdQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
		this.eventQueue = new LinkedList<>();
		this.viewController = Optional.of(view);
		this.modelController = Optional.of(game);
		this.timer = Optional.of(new AnimationTimerImpl(this));
	}
	
	public final void setup() {
		if (this.viewController.isEmpty()) {
			this.viewController = Optional.of(new ViewControllerImpl(
					Optional.of(new MapScene(this.modelController.get().getGameState().get(), this)))
					);
			this.viewController.get().getGameView().setup(this);
			AppLogger.getAppLogger().debug("View was empty so it was initialized.");
		} else {
			this.viewController.get().getGameView().setup(this);
			this.viewController.get().getGameView().setInputController(this);
			AppLogger.getAppLogger().debug("View input controller set.");
		}

		if (this.modelController.isEmpty()) {
			this.modelController = Optional.of(new ModelControllerImpl(new GameState()));
			this.modelController.get().setEventListener(this);
			AppLogger.getAppLogger().debug("There was no game state, new one instantiated.");
		} else {
			this.modelController.get().setEventListener(this);
			AppLogger.getAppLogger().debug("Game state present, event listener set only.");
		}
	}
	
	public final void mainLoop() {
		while (!this.modelController.get().isGameOver()) {
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
			cmd.execute(this.modelController.get().getGameState().get());
		}
	}
	
	public void updateGame() {
		this.modelController.get().update();
		this.checkEvents();
	}
	
	public final void render() {
		this.viewController.get().render();
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
		final Environment env = this.modelController.get().getGameEnvironment();
		this.eventQueue.stream().forEach(e -> {
			if (e instanceof PlayerHitsItemEvent) {
				playerHitsPickUpObjEventHandler(env, e);
			} else if (e instanceof PlayerHitsEnemyEvent) {
				playerHitsEnemyEventHandler(env, e);
			} else if (e instanceof PlayerHitsObstacleEvent) {
				playerHitsObstacleEventHandler(env, e);
			} else if (e instanceof PlayerHitsPlatformEvent) {
				playerHitsPlatformEventHandler(env, e);
			} else if (e instanceof EnemyHitsPlatformEvent) {
				enemyHitsPlatformEventHandler(env, e);
			}
		});
		this.eventQueue.clear();
	}
	
	private void enemyHitsPlatformEventHandler(final Environment env, final GameEvent e) {
		final Enemy enemy = ((EnemyHitsPlatformEvent) e).getEnemy();
		final Platform platform = ((EnemyHitsPlatformEvent) e).getPlatform();
		enemy.land();
		enemy.moveUp(env.getGravity());
//		AppLogger.getAppLogger().info("enemy hits platform");
	}
	
	private void playerHitsPlatformEventHandler(final Environment env, final GameEvent e) {
		final Player player = ((PlayerHitsPlatformEvent) e).getPlayer();
		final Platform platform = ((PlayerHitsPlatformEvent) e).getPlatform();
		player.land();
		player.moveUp(env.getGravity());
	}
	
	private void playerHitsObstacleEventHandler(final Environment env, final GameEvent e) {
		AppLogger.getAppLogger().collision("player hit an obstacle.");
		final Player player = ((PlayerHitsObstacleEvent) e).getPlayer();
		final ObstacleImpl obstacle = ((PlayerHitsObstacleEvent) e).getObstacle();
		player.decreaseHealth((double) (obstacle.getMass() / 50));
	}

	private void playerHitsEnemyEventHandler(final Environment env, final GameEvent e) {
		AppLogger.getAppLogger().collision("player hit an enemy.");
		final Player player = ((PlayerHitsEnemyEvent) e).getPlayer();
		final Enemy enemy = ((PlayerHitsEnemyEvent) e).getEnemy();
		player.setHealth(player.getHealth() - 0.01);
		// TODO: enemy.setHealth(enemy.getHealth() - player.COLLISION_DAMAGE);
		if (!player.isAlive()) {
			// DELETE PLAYER SPRITE this.view.get()
		}
		if (!enemy.isAlive()) {
			this.viewController.get().getGameView().deleteEnemySpriteImage(new MutablePosition2Dimpl(enemy.getPosition().get().getX(),
					enemy.getPosition().get().getY()));	
		}
	}

	private void playerHitsPickUpObjEventHandler(final Environment env, final GameEvent e) {
		AppLogger.getAppLogger().collision("player picked up an obj.");
		final Player player = ((PlayerHitsItemEvent) e).getPlayer();
		// Apply item effect on character
		((PlayerHitsItemEvent) e).getItem()
			.getEffect()
			.applyEffect(player);
		// Update environment
		final MutablePosition2D pickupPos = ((PlayerHitsItemEvent) e).getItem().getPosition().get();
		env.deleteObjByPosition(new ImmutablePosition2Dimpl(pickupPos.getX(), pickupPos.getY()));
	}
	
	public void start() {
		this.timer.get().start();
	}
	
	public void stop() {
		this.timer.get().stop();
	}
}
