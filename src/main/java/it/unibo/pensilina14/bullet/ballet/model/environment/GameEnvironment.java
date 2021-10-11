package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
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

	private final double gravity;
	private Optional<Player> player;
	private final Optional<List<Enemy>> enemies;
	private final Optional<List<PhysicalObject>> obstacles;
	private final Optional<List<Item>> items;
	
	public GameEnvironment(final double gravity, final Optional<Player> player) {
		this.gravity = gravity;
		this.player = player;
		this.enemies = Optional.of(new ArrayList<>());
		this.obstacles = Optional.of(new ArrayList<>());
		this.items = Optional.of(new ArrayList<>());
	}
	
	@Override
	public double getGravity() {
		return this.gravity;
	}

	@Override
	public Optional<List<PhysicalObject>> getObjsList() {
		return this.mergeLists();
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
	public void setPlayer(Player player) {
		this.player = Optional.ofNullable(player);
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
	}

	private Optional<List<PhysicalObject>> mergeLists() {
		final Optional<List<PhysicalObject>> mergedList = Optional.of(new ArrayList<>());
		mergedList.get().addAll(List.of(this.player.get()));
		mergedList.get().addAll(this.enemies.get());
		mergedList.get().addAll(this.obstacles.get());
		//mergedList.get().addAll(this.items.get());   #to be added when Item class is changed
		return mergedList;
	}

	
}
