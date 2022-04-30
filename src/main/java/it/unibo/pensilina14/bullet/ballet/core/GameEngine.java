package it.unibo.pensilina14.bullet.ballet.core;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
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
import it.unibo.pensilina14.bullet.ballet.input.InputController;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.menu.controller.Frames;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.collision.CollisionSides;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.BulletHitsEnemyEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.BulletHitsObstacleEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.BulletHitsPlatformEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.EnemyHitsPlatformEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameOverEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.PlayerHitsEnemyEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.PlayerHitsItemEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.PlayerHitsObstacleEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.PlayerHitsWeaponEvent;
import it.unibo.pensilina14.bullet.ballet.save.Save;
import javafx.animation.AnimationTimer;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.PlayerHitsPlatformEvent;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.Obstacle;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleImpl;
import it.unibo.pensilina14.bullet.ballet.model.score.ScoreSystem;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Items;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.sounds.Sound;
import it.unibo.pensilina14.bullet.ballet.sounds.Sounds;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactory;
import it.unibo.pensilina14.bullet.ballet.sounds.SoundsFactoryImpl;

/**
 * Manages a variety of game aspects such as input commands processing, event handling 
 * and putting into communication model and view.
 * 
 * This class could be considered the core of the game itself.
 *
 */
public class GameEngine implements InputController, GameEventListener, Engine {
    /**
     * Constant used to define command queue capacity.
     */
	private static final int QUEUE_CAPACITY = 100;

	private Optional<ViewController> viewController;
	private Optional<ModelController> modelController;
	private final SoundsFactory soundsFactory;
	private final Sound soundtrack;
	private final BlockingQueue<Command> cmdQueue;
	/**
	 * Data structure, for instance a {@link List}, whose goal is to
	 * store all the incoming events from the model and view. 
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
		this.soundsFactory = new SoundsFactoryImpl();
		this.soundtrack = this.soundsFactory.createRandomSoundtrack();
	}
	
	public GameEngine(final ViewController view, final ModelController game) {
		this.cmdQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
		this.eventQueue = new LinkedList<>();
		this.viewController = Optional.of(view);
		this.modelController = Optional.of(game);
		this.timer = Optional.of(new AnimationTimerImpl(this));
		this.soundsFactory = new SoundsFactoryImpl();
		this.soundtrack = this.soundsFactory.createRandomSoundtrack();
	}
	
	@Override
	public final void setup() {
		if (this.viewController.isEmpty()) {
			this.viewController = Optional.of(new ViewControllerImpl(
					Optional.of(new MapScene(this.modelController.get().getGameState().get(), this)))
					);
			this.viewController.get().getGameView().setup(this);
		} else {
			this.viewController.get().getGameView().setup(this);
			this.viewController.get().getGameView().setInputController(this);
		}

		if (this.modelController.isEmpty()) {
			this.modelController = Optional.of(new ModelControllerImpl(new GameState()));
			this.modelController.get().setEventListener(this);
		} else {
			this.modelController.get().setEventListener(this);
		}
	}
	
	@Override
	public final void mainLoop() {
		while (!this.modelController.get().isGameOver()) {
			this.processInput();
			this.updateGame();
			this.render();
		}
		// GAME OVER
	}
	
	@Override
	public final void processInput() {
		final Command cmd = this.cmdQueue.poll();
		if (cmd != null) {
			cmd.execute(this.modelController.get().getGameState().get());
		}
	}
	
	@Override
	public final void updateGame() {
		if (this.modelController.get().getGameState().get().isGameOver()) {
			this.timer.get().stop();
		}
		if (!this.soundtrack.getAudioClip().isPlaying()) {
			this.soundtrack.play();
		}
		this.modelController.get().update();
		this.checkEvents();
	}
	

	@Override
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
				try {
					playerHitsPickUpObjEventHandler(env, e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e instanceof PlayerHitsEnemyEvent) {
				playerHitsEnemyEventHandler(e);
			} else if (e instanceof PlayerHitsObstacleEvent) {
				playerHitsObstacleEventHandler(e);
			} else if (e instanceof PlayerHitsWeaponEvent) {
				playerHitsWeaponEventHandler(env, e);
			} else if (e instanceof BulletHitsEnemyEvent) {
				bulletHitsEnemyEventHandler(env, e);
			} else if (e instanceof PlayerHitsPlatformEvent) {
				playerHitsPlatformEventHandler(env, e);
			} else if (e instanceof EnemyHitsPlatformEvent) {
				enemyHitsPlatformEventHandler(env, e);
			} else if (e instanceof BulletHitsPlatformEvent) {
				bulletHitsPlatformEventHandler(env, e);
			} else if (e instanceof GameOverEvent) {
				try {
					gameOverEventHandler(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (e instanceof BulletHitsObstacleEvent) {
				bulletHitsObstacleEventHandler(env, e);
			}
		});
		this.eventQueue.clear();
	}
	
	private void enemyHitsPlatformEventHandler(final Environment env, final GameEvent e) {
		final Enemy enemy = ((EnemyHitsPlatformEvent) e).getEnemy();
		enemy.land();
		enemy.moveUp(env.getGravity());
	}
	
	private void playerHitsPlatformEventHandler(final Environment env, final GameEvent e) {
		final Player player = ((PlayerHitsPlatformEvent) e).getPlayer();
		final CollisionSides side = ((PlayerHitsPlatformEvent) e).getCollisionSide();
		if (side == CollisionSides.WEST) {
			player.blockX();
		} else {
			if (player.hasBlockedX()) {
				player.unblockX();
			}
			player.land();
			player.moveUp(env.getGravity());
		}
	}
	
	private void playerHitsObstacleEventHandler(final GameEvent e) {
		final Player player = ((PlayerHitsObstacleEvent) e).getPlayer();
		final ObstacleImpl obstacle = ((PlayerHitsObstacleEvent) e).getObstacle();
		player.decreaseHealth((double) (obstacle.getMass() / 2));
	}

	private void playerHitsEnemyEventHandler(final GameEvent e) {
		final Player player = ((PlayerHitsEnemyEvent) e).getPlayer();
		final Enemy enemy = ((PlayerHitsEnemyEvent) e).getEnemy();
		player.setHealth(player.getHealth() - 1);
		if (!enemy.isAlive()) {
			this.viewController.get().getGameView().deleteEnemySpriteImage(new MutablePosition2Dimpl(enemy.getPosition().get().getX(),
					enemy.getPosition().get().getY()));	
		}
	}

	private void playerHitsPickUpObjEventHandler(final Environment env, final GameEvent e) throws IOException {
		final Player player = ((PlayerHitsItemEvent) e).getPlayer();
		final Item item = ((PlayerHitsItemEvent) e).getItem();
		if (item.getItemId().equals(Items.HEART)) {
			this.soundsFactory.createSound(Sounds.HEALTH_INCREMENT).play();
		} else if (item.getItemId().equals(Items.COIN)) {
			this.soundsFactory.createSound(Sounds.COIN).play();
			this.modelController.get().getGameEnvironment().getEntityManager().getPlayer().get().getCurrentScore().increase(ScoreSystem.ScoreBonus.COLLECT_COIN.getBonus());
		} else if (item.getItemId().equals(Items.CHARGER)) {
			this.soundsFactory.createSound(Sounds.RELOAD);
		} else if (item.getItemId().equals(Items.FLAG)) {
			this.viewController.get().stopPlayerAnimation();
			this.viewController.get().getGameView().autoKill();
			this.viewController.get().changeScene(Frames.HOMEPAGE);
			this.soundsFactory.createSound(Sounds.WIN).play();
			final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
			final double multiplier = 0.15;
		    final Date date = new Date();
			Save.saveGameStatistics(this.modelController.flatMap(ModelController::getGameState).get().getPlayerName(),
					player.getCurrentScore().showScore() + player.getCurrentScore().showScore() * multiplier, formatter.format(date)); 
			this.stop();
		} else {
			this.soundsFactory.createSound(Sounds.DAMAGE).play();
		}
		// Apply item effect on character
		((PlayerHitsItemEvent) e).getItem()
			.getEffect()
			.applyEffect(player);
		// Update environment
		final MutablePosition2D pickupPos = ((PlayerHitsItemEvent) e).getItem().getPosition().get();
		env.deleteObjByPosition(new ImmutablePosition2Dimpl(pickupPos.getX(), pickupPos.getY()));
		this.viewController.get().getGameView().deleteItemSprite(pickupPos);
	}
	
	private void playerHitsWeaponEventHandler(final Environment env, final GameEvent e) {
		final Player player = ((PlayerHitsWeaponEvent) e).getPlayer();
		// Set Weapon to Player
		final Weapon weapon = ((PlayerHitsWeaponEvent) e).getWeapon();
		if (!weapon.isOn()) {
			if (player.hasWeapon()) {
				final Weapon actualWeapon = player.getWeapon().get();
				if (player.getWeapon().get().getTypeOfWeapon().equals(weapon.getTypeOfWeapon())) {
					player.getWeapon().get().recharge();
					env.deleteObjByPosition(new ImmutablePosition2Dimpl(weapon.getPosition().get().getX(),
							weapon.getPosition().get().getY()));	
					this.viewController.get().getGameView().deleteWeaponSpriteImage(weapon.getPosition().get());
				} else {
					player.getWeapon().get().setOff();
					player.removeWeapon();
					env.deleteObjByPosition(new ImmutablePosition2Dimpl(actualWeapon.getPosition().get().getX(),
							actualWeapon.getPosition().get().getY()));
					this.viewController.get().getGameView().deleteWeaponSpriteImage(actualWeapon.getPosition().get());
					weapon.setOn();
					player.setWeapon(weapon);
				}
			} else {
				weapon.setOn();
				player.setWeapon(weapon);
			}
		}
	}
	
	private void bulletHitsEnemyEventHandler(final Environment env, final GameEvent e) {
		final Enemy enemy = ((BulletHitsEnemyEvent) e).getEnemy();
		((BulletHitsEnemyEvent) e).getBullet()
			.getEffect()
			.applyEffect(enemy);
		final MutablePosition2D bulletPos = ((BulletHitsEnemyEvent) e).getBullet().getPosition().get();
		env.deleteObjByPosition(new ImmutablePosition2Dimpl(bulletPos.getX(), bulletPos.getY()));
		this.viewController.get().getGameView().deleteBulletSpriteImage(bulletPos);
		env.deleteObjByPosition(new ImmutablePosition2Dimpl(enemy.getPosition().get().getX(),
				enemy.getPosition().get().getY()));
		this.viewController.get().getGameView().deleteEnemySpriteImage(enemy.getPosition().get());
		this.modelController.get().getGameEnvironment().getEntityManager().getPlayer().get().getCurrentScore().increase(ScoreSystem.ScoreBonus.KILL_ENEMY.getBonus());
	}
	
	private void bulletHitsObstacleEventHandler(final Environment env, final GameEvent e) {
		final Obstacle obstacle = ((BulletHitsObstacleEvent) e).getObstacle();
		final MutablePosition2D bulletPos = ((BulletHitsObstacleEvent) e).getBullet().getPosition().get();
		env.deleteObjByPosition(new ImmutablePosition2Dimpl(bulletPos.getX(), bulletPos.getY()));
		this.viewController.get().getGameView().deleteBulletSpriteImage(bulletPos);
		env.deleteObjByPosition(new ImmutablePosition2Dimpl(obstacle.getPosition().get().getX(),
				obstacle.getPosition().get().getY()));
		this.viewController.get().getGameView().deleteObstacleSpriteImage(obstacle.getPosition().get());
		this.modelController.get().getGameEnvironment().getEntityManager().getPlayer().get().getCurrentScore().increase(ScoreSystem.ScoreBonus.DESTROY_OBSTACLE.getBonus());
	}
	
	private void bulletHitsPlatformEventHandler(final Environment env, final GameEvent e) {
		final MutablePosition2D bulletPos = ((BulletHitsPlatformEvent) e).getBullet().getPosition().get();
		env.deleteObjByPosition(new ImmutablePosition2Dimpl(bulletPos.getX(), bulletPos.getY()));
		this.viewController.get().getGameView().deleteBulletSpriteImage(bulletPos);
	}
	
	private void gameOverEventHandler(final GameEvent e) throws IOException {
		final Player player = ((GameOverEvent) e).getPlayer();
		this.viewController.get().stopPlayerAnimation();
		this.viewController.get().getGameView().autoKill();
		this.viewController.get().changeScene(Frames.HOMEPAGE);
		this.soundsFactory.createSound(Sounds.DIE).play();
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
	    final Date date = new Date();
		Save.saveGameStatistics(this.modelController.flatMap(ModelController::getGameState).get().getPlayerName(),
				player.getCurrentScore().showScore(), formatter.format(date));
		this.stop();
	}
	
	@Override
	public final void start() {
		this.soundtrack.play();
		this.timer.get().start();
	}
	
	@Override
	public final void stop() {
		this.soundtrack.stop();
		this.timer.get().stop();
	}
}
