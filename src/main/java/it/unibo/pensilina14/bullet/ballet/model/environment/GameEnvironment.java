package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.EntityContainer;
import it.unibo.pensilina14.bullet.ballet.common.EntityManager;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameOverEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.CollisionEventChecker;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.EventChecker;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEvent;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.Obstacle;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;
import it.unibo.pensilina14.bullet.ballet.model.weapon.BulletImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
import it.unibo.pensilina14.bullet.ballet.model.weapon.PickupItem;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/**
 * Implementation of {@link Environment}.
 * 
 * {@inheritDoc}
 */
public class GameEnvironment implements Environment {

    /**
     * GameEnvironment height and width value for testing.
     */
    public static final double DEFAULT_DIM = 20.0;

    private final double gravity;
    private final Dimension2D dimension;
    private final EntityManager entities;
    private Optional<GameEventListener> eventListener;
	
	/**
	 * <p>
	 * This constructor uses {@link #DEFAULT_DIM} so use it wisely.
	 * Ideally we don't want this if not for <strong>testing</strong> purposes.
	 * </p>
	 */
	public GameEnvironment() {
		this.gravity = GravityConstants.TEST.getValue();
		this.dimension = new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM);
		this.entities = new EntityContainer();
		this.eventListener = Optional.empty();
	}
	
	/**
	 * Adds dimension configuration to {@link #dimension}.
	 * 
	 * @param height
	 * @param width
	 */
	public GameEnvironment(final double height, final double width) {
		this.gravity = GravityConstants.TEST.getValue();
		this.dimension = new Dimension2Dimpl(height, width);
		this.entities = new EntityContainer();
		this.eventListener = Optional.empty();
	}
	
	/**
	 * Parameter-full constructor that provides a well-defined
	 * {@link GameEnvironment} capable of connecting with a bunch
	 * of different objects.
	 * 
	 * @param gravity
	 * @param height
	 * @param width
	 * @param container is the {@link EntityContainer} to be set as the one and only in this game environment.
	 * @param l is the event listener that is going to "listen" to the events launched in this {@link Environment}.
	 */
	public GameEnvironment(final double gravity, final double height, final double width, final EntityManager container, final GameEventListener l) {
		this.gravity = gravity;
		this.dimension = new Dimension2Dimpl(height, width);
		this.entities = container;
		this.eventListener = Optional.of(l);
	}
	
	@Override
	public final double getGravity() {
		return this.gravity;
	}
	
	@Override
	public final Dimension2D getDimension() {
		return this.dimension;
	}

	@Override
	public final boolean deleteObjByPosition(final ImmutablePosition2D position) {
		final List<PhysicalObject> allObjsList = this.entities.getObjsList().get();
		for (final PhysicalObject obj : allObjsList) {
			final MutablePosition2D objPos = obj.getPosition().get();
			if (objPos.getX() == position.getX() && objPos.getY() == position.getY()) {
				if (obj instanceof Enemy) {
					this.entities.getEnemies().get().remove(obj);
					return true;
				} else if (obj instanceof ObstacleImpl) {
					this.entities.getObstacles().get().remove(obj);
					return true;
				} else if (obj instanceof PickupItem) {
					this.entities.getItems().get().remove(obj); 
					return true;
				} else if (obj instanceof WeaponImpl) {
					AppLogger.getAppLogger().debug("Delete weapon model");
					this.entities.getWeapons().get().remove(obj);
					return true;
				} else if (obj instanceof BulletImpl) {
					this.entities.getBullets().get().remove(obj);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public final void updateState() {
		final Optional<Player> player = this.entities.getPlayer();
		if (!player.get().hasLanded()) {
			player.get().moveDown(this.gravity);
		} else {
			player.get().resetLanding();
		}
		
		player.get().updateState();
		player.get().getCurrentScore().increase();
		this.entities.getEnemies().get().stream().forEach(e -> {
			if (!e.hasLanded()) {
				e.moveDown(this.gravity);
			} else {
				e.resetLanding();
			}
			e.updateState();
			//AppLogger.getAppLogger().debug("Enemy pos: " + e.getPosition().toString());
		}); 
		this.entities.getObstacles().get().forEach(o -> {
			if (!o.hasLanded()) {
				o.moveDown(this.gravity);
			} else {
				o.resetLanding();
			}
			o.updateState();
		});
		this.entities.getItems().get().forEach(i -> i.updateState());
		this.entities.getWeapons().get().forEach(i -> {
			if (!i.isOn()) {
				i.updateState();
			} else {
				final MutablePosition2D pos = player.get().getPosition().get();
				i.setPosition(new MutablePosition2Dimpl(pos.getX() + 15, pos.getY() + 7));
			}
		});

		if (this.entities.getBullets().isPresent()) {
			this.entities.getBullets().get().stream().forEach(i -> i.updateState());
		} 

		this.entities.getPlatforms().get().stream().forEach(i -> i.updateState());
		if (!player.get().isAlive()) {
			this.eventListener.get().notifyEvent(new GameOverEvent(player.get()));
		}
		this.checkCollisions();
	}
	
	
	@Override
	public final void setEventListener(final GameEventListener listener) {
		this.eventListener = Optional.ofNullable(listener);
	}

	private void checkCollisions() {
		final Map<String, EventChecker> eventCheckers = new HashMap<>();
		eventCheckers.putAll(Map.of(
				"playeritem", new CollisionEventChecker(this.entities.getItems().get(), List.of(this.entities.getPlayer().get())), 
				"playerenemy", new CollisionEventChecker(this.entities.getEnemies().get(), List.of(this.entities.getPlayer().get())), 
				"playerobstacle", new CollisionEventChecker(this.entities.getObstacles().get(), List.of(this.entities.getPlayer().get())), 
				"playerplatform", new CollisionEventChecker(this.entities.getPlatforms().get(), List.of(this.entities.getPlayer().get())), 
				"enemyplatform", new CollisionEventChecker(this.entities.getPlatforms().get(), this.entities.getEnemies().get()),
				"playerweapon", new CollisionEventChecker(this.entities.getWeapons().get(), List.of(this.entities.getPlayer().get()))
				));
		if(this.entities.getBullets().isPresent()) {
			eventCheckers.put("bulletEnemy", new CollisionEventChecker(this.entities.getBullets().get(), this.entities.getEnemies().get()));
			eventCheckers.put("bulletEnemy", new CollisionEventChecker(this.entities.getBullets().get(), this.entities.getPlatforms().get()));
			eventCheckers.put("bulletEnemy", new CollisionEventChecker(this.entities.getBullets().get(), this.entities.getObstacles().get()));
		}
		this.checkAll(eventCheckers);
	}
	
	private void checkAll(final Map<String, EventChecker> checkersMap) {
		for (final EventChecker checker : checkersMap.values()) {
			checker.check();
			final List<GameEvent> events = new ArrayList<>(checker.getBuffer().getEvents());
			if (!events.isEmpty()) {
				events.forEach(e -> {
					this.eventListener.get().notifyEvent(e);
				});
			}
		}
	}

	@Override
	public final EntityManager getEntityManager() {
		return this.entities;
	}
}
