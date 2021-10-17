package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.CollisionEventChecker;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.EventChecker;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.DynamicObstacle;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.StaticObstacle;
import it.unibo.pensilina14.bullet.ballet.model.weapon.DynamicPickupItem;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;

import java.util.ArrayList;
import java.util.List;
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
	
	public GameEnvironment(final double height, final double width) {
		this.gravity = GravityConstants.EARTH.getValue();
		this.dimension = new Dimension2Dimpl(height, width);
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
		final EventChecker checkPlayerItem = new CollisionEventChecker(this.items.get(), List.of(this.player.get()));
		final EventChecker checkEnemiesItems = new CollisionEventChecker(this.enemies.get(), this.items.get());
		final EventChecker checkPlayerEnemy = new CollisionEventChecker(this.enemies.get(), List.of(this.player.get()));
		final EventChecker checkPlayerObstacle = new CollisionEventChecker(this.obstacles.get(), List.of(this.player.get()));
		final EventChecker checkEnemiesObstacles = new CollisionEventChecker(this.enemies.get(), this.obstacles.get());
		
		checkPlayerItem.check();
		checkEnemiesItems.check();
		checkPlayerEnemy.check();
		checkPlayerObstacle.check();
		checkEnemiesObstacles.check();
		
		// Notify everything to the {@link GameEventListener}.
		if (!checkPlayerItem.getBuffer().getEvents().isEmpty()) {
			checkPlayerItem.getBuffer().getEvents().stream().forEach(e -> {
				this.eventListener.get().notifyEvent(e);
			});
		} 
		if (!checkEnemiesItems.getBuffer().getEvents().isEmpty()) {
			checkEnemiesItems.getBuffer().getEvents().stream().forEach(e -> {
				this.eventListener.get().notifyEvent(e);
			});
		}
		if (!checkPlayerEnemy.getBuffer().getEvents().isEmpty()) {
			checkPlayerEnemy.getBuffer().getEvents().stream().forEach(e -> {
				this.eventListener.get().notifyEvent(e);
			});
		}
		if (!checkPlayerObstacle.getBuffer().getEvents().isEmpty()) {
			checkPlayerObstacle.getBuffer().getEvents().stream().forEach(e -> {
				this.eventListener.get().notifyEvent(e);
			});
		}
		if (!checkEnemiesObstacles.getBuffer().getEvents().isEmpty()) {
			checkEnemiesObstacles.getBuffer().getEvents().stream().forEach(e -> {
				this.eventListener.get().notifyEvent(e);
			});
		}
	}
	
	//TODO: add checkBoundaries()
}
