package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.PlayerHitsEnemyEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.CharacterHitsPickupObjEvent;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEvent;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.DynamicObstacle;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.StaticObstacle;
import it.unibo.pensilina14.bullet.ballet.model.weapon.DynamicPickupItem;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/**
 * Implementation of {@link Environment}.
 * 
 * {@inheritDoc}
 */
public class GameEnvironment implements Environment {

    public static final double DEFAULT_DIM = 20.0;
	
	private final double gravity;
	private final Dimension2D dimension;
	private Optional<Player> player;
	private final Optional<List<Enemy>> enemies;
	private final Optional<List<PhysicalObject>> obstacles;
	private final Optional<List<Item>> items;
	private Optional<GameEventListener> eventListener;
	
	public GameEnvironment() {
		this.gravity = GravityConstants.EARTH.getValue();
		this.dimension = new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM);
		this.player = Optional.empty();
		this.enemies = Optional.of(new ArrayList<>());
		this.obstacles = Optional.of(new ArrayList<>());
		this.items = Optional.of(new ArrayList<>());
		this.eventListener = Optional.empty();
	}
	
	public GameEnvironment(final double gravity, final double height, final double width, final Optional<Player> player, final GameEventListener l) {
		this.gravity = gravity;
		this.dimension = new Dimension2Dimpl(height, width);
		this.player = player;
		this.enemies = Optional.of(new ArrayList<>());
		this.obstacles = Optional.of(new ArrayList<>());
		this.items = Optional.of(new ArrayList<>());
		this.eventListener = Optional.of(l);
	}
	
	@Override
	public double getGravity() {
		return this.gravity;
	}
	
	public Dimension2D getDimension() {
		return this.dimension;
	}

	@Override
	public Optional<List<PhysicalObject>> getObjsList() {
		return this.mergeLists();
	}
	
	@Override
	public Optional<Player> getPlayer() {
		return this.player;
	}
	
	@Override
	public Optional<List<Enemy>> getEnemies() {
		if (this.enemies.isPresent()) {
			return Optional.of(List.copyOf(this.enemies.get()));
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<PhysicalObject>> getObstacles() {
		if (this.obstacles.isPresent()) {
			return Optional.of(List.copyOf(this.obstacles.get()));
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<Item>> getItems() {
		if (this.items.isPresent()) {
			return Optional.of(List.copyOf(this.items.get()));
		}
		return Optional.empty();
	}
	
    @Override
	public void setPlayer(final Player player) {
		this.player = Optional.ofNullable(player);
	}

	@Override
	public boolean addEnemy(final Enemy enemy) {
		if (this.enemies.get().contains(enemy)) {
			return false;
		} else {
			this.enemies.get().add(enemy);
			return true;
		}
	}
	
	@Override
	public boolean addObstacle(final PhysicalObject obstacle) {
		/*
		 * Type validity check: 
		 * 	verify if obstacle is a StaticObstacle or DynamicObstacle since 
		 * 	parameter type is open to all PhysicalObject.
		 * 	Use wisely.
		 */
		if (obstacle instanceof StaticObstacle || obstacle instanceof DynamicObstacle) {
			if (this.obstacles.get().contains(obstacle)) {
				return false;
			} else {
				this.obstacles.get().add(obstacle);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addItem(final Item item) {
		if (this.items.get().contains(item)) {
			return false;
		} else {
			this.items.get().add(item);
			return true;
		}
	}

	@Override
	public boolean deleteObjByPosition(final ImmutablePosition2D position) {
		final List<PhysicalObject> allObjsList = this.mergeLists().get();
		for (final PhysicalObject obj : allObjsList) {
			final MutablePosition2D objPos = obj.getPosition();
			if (objPos.getX() == position.getX() && objPos.getY() == position.getY()) {
				if (obj instanceof Player) {
					this.player = Optional.empty();
					return true;
				} else if (obj instanceof Enemy) {
					this.enemies.get().remove(obj);
					return true;
				} else if (obj instanceof StaticObstacle || obj instanceof DynamicObstacle) {
					this.obstacles.get().remove(obj);
					return true;
				} else if (obj instanceof Item) {
					this.items.get().remove(obj); //Requires Item interface inheritance to be changed
					return true;
				} 
			}
		}
		return false;
	}

	@Override
	public void updateState(final int dt) {
		this.player.get().updateState(dt);
		this.enemies.get().stream().forEach(e -> e.updateState(dt));
		this.obstacles.get().stream()
							.filter(o -> o instanceof DynamicObstacle)
							.map(o -> (DynamicObstacle) o)
							.forEach(o -> o.updateState(dt));
		this.items.get().stream()
						.filter(i -> i instanceof DynamicPickupItem)
						.map(i -> (DynamicPickupItem) i)
						.forEach(i -> i.updateState(dt));
		this.checkCollisions();
	}
	
	@Override
	public void setEventListener(final GameEventListener listener) {
		this.eventListener = Optional.ofNullable(listener);
	}

	private Optional<List<PhysicalObject>> mergeLists() {
		final Optional<List<PhysicalObject>> mergedList = Optional.of(new ArrayList<>());
		if (this.player.isPresent()) {
			mergedList.get().addAll(List.of(this.player.get()));
		}
		if (this.enemies.isPresent()) {
			mergedList.get().addAll(this.enemies.get());
		}
		if (this.obstacles.isPresent()) {
			mergedList.get().addAll(this.obstacles.get());
		}
		if (this.obstacles.isPresent()) {
            mergedList.get().addAll(this.items.get());   
		}
		return mergedList;
	}
	
	private void checkCollisions() {
		Optional<Item> playerCollectedItem = Optional.empty();
		final List<GameEvent> enemiesPickupEvents = new LinkedList<>();
		Optional<Enemy> playerHitEnemy = Optional.empty();
		
		// Check collisions between player and an item.
		for (final Item item : this.items.get()) {
			if (this.player.get().isCollidingWith(item)) {
				playerCollectedItem = Optional.ofNullable(item);
				break;
			} 
		}
		
		// Check collisions between enemies and items.
		for (final Item item : this.items.get()) {
			for (final Enemy enemy : this.enemies.get()) {
				if (enemy.isCollidingWith(item)) {
					enemiesPickupEvents.add(new CharacterHitsPickupObjEvent(enemy, item));
				}
			}
		}
		
		// Check collisions between player and an enemy.
		for (final Enemy enemy : this.enemies.get()) {
			if (this.player.get().isCollidingWith(enemy)) {
				playerHitEnemy = Optional.ofNullable(enemy);
				break;
			}
		}
		
		// Notify everything to the {@link GameEventListener}.
		if (playerCollectedItem.isPresent()) {
			this.eventListener.get().notifyEvent(new CharacterHitsPickupObjEvent(this.player.get(), playerCollectedItem.get()));
		} 
		if (!enemiesPickupEvents.isEmpty()) {
			for (final GameEvent event : enemiesPickupEvents) {
				this.eventListener.get().notifyEvent(event);
			}
		}
		if (playerHitEnemy.isPresent()) {
			this.eventListener.get().notifyEvent(new PlayerHitsEnemyEvent(this.player.get(), playerHitEnemy.get()));
		}
		//TODO: add player/obstacles, enemies/obstacles.
	}
	
	//TODO: add checkBoundaries()
}
