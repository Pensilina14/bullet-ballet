package it.unibo.pensilina14.bullet.ballet.common;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.PickupItem;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;

/**
 * This class is responsible of storing and managing 
 * the different entities in the game with the use of
 * a data structure that maps every {@link List} of entities, each
 * one has a different type, to their relative label which is an element
 * of the {@link GameEntities} enumeration that states the typo of the {@link List}
 * associated. Also note that this is model side.
 *
 */
public class EntityContainer extends AbstractContainer<GameEntity> implements EntityManager {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<Player>> getPlayer() {
		final List<GameEntity> player = this.getContainer().get(GameEntities.PLAYER);
		if (player.get(0) instanceof Player) {
			return Optional.of(player.stream()
					.map(e -> (Player) e)
					.collect(Collectors.toList()));
		}
		return Optional.empty();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<Enemy>> getEnemies() {
		final List<GameEntity> enemies = this.getContainer().get(GameEntities.ENEMY);
		if (enemies.get(0) instanceof Enemy) {
			return Optional.of(enemies.stream()
					.map(e -> (Enemy) e)
					.collect(Collectors.toList()));
		}
		return Optional.empty();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<PickupItem>> getItems() {
		final List<GameEntity> items = this.getContainer().get(GameEntities.PICKUP_ITEM);
		if (items.get(0) instanceof PickupItem) {
			return Optional.of(items.stream()
					.map(e -> (PickupItem) e)
					.collect(Collectors.toList()));
		}
		return Optional.empty();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<ObstacleImpl>> getObstacle() {
		final List<GameEntity> obstacles = this.getContainer().get(GameEntities.OBSTACLE);
		if (obstacles.get(0) instanceof ObstacleImpl) {
			return Optional.of(obstacles.stream()
					.map(e -> (ObstacleImpl) e)
					.collect(Collectors.toList()));
		}
		return Optional.empty();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<Weapon>> getWeapons() {
		final List<GameEntity> weapons = this.getContainer().get(GameEntities.WEAPON);
		if (weapons.get(0) instanceof Weapon) {
			return Optional.of(weapons.stream()
					.map(e -> (Weapon) e)
					.collect(Collectors.toList()));
		}
		return Optional.empty();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<Platform>> getPlatforms() {
		final List<GameEntity> platforms = this.getContainer().get(GameEntities.PLATFORM);
		if (platforms.get(0) instanceof Enemy) {
			return Optional.of(platforms.stream()
					.map(e -> (Platform) e)
					.collect(Collectors.toList()));
		}
		return Optional.empty();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPlayer(final Player player) {
		this.getContainer().get(GameEntities.PLAYER).add(player);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addEnemy(final Enemy enemy) {
		if (this.getContainer().get(GameEntities.ENEMY).contains(enemy)) {
			return false;
		} else {
			this.getContainer().get(GameEntities.ENEMY).add(enemy);
			return true;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addItem(final PickupItem item) {
		if (this.getContainer().get(GameEntities.PICKUP_ITEM).contains(item)) {
			return false;
		} else {
			this.getContainer().get(GameEntities.PICKUP_ITEM).add(item);
			return true;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addObstacle(final ObstacleImpl obstacle) {
		if (this.getContainer().get(GameEntities.OBSTACLE).contains(obstacle)) {
			return false;
		} else {
			this.getContainer().get(GameEntities.OBSTACLE).add(obstacle);
			return true;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addWeapon(final Weapon weapon) {
		if (this.getContainer().get(GameEntities.WEAPON).contains(weapon)) {
			return false;
		} else {
			this.getContainer().get(GameEntities.WEAPON).add(weapon);
			return true;
		}
	}
}
