package it.unibo.pensilina14.bullet.ballet.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.Obstacle;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;
import it.unibo.pensilina14.bullet.ballet.model.weapon.BulletImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
import it.unibo.pensilina14.bullet.ballet.model.weapon.PickupItem;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponImpl;

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
	public Optional<Player> getPlayer() {
		final List<GameEntity> player = this.getContainer().get(GameEntities.PLAYER).get();
		if (!player.isEmpty() && player.get(0) instanceof Player) {
			return Optional.ofNullable((Player) player.get(0));
		}
		return Optional.empty();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<Enemy>> getEnemies() {
		final List<GameEntity> enemies = this.getContainer().get(GameEntities.ENEMY).get();
		if (!enemies.isEmpty() && enemies.get(0) instanceof Enemy) {
			return Optional.ofNullable(enemies.stream()
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
		final List<GameEntity> items = this.getContainer().get(GameEntities.PICKUP_ITEM).get();
		if (!items.isEmpty() && items.get(0) instanceof PickupItem) {
			return Optional.ofNullable(items.stream()
					.map(e -> (PickupItem) e)
					.collect(Collectors.toList()));
		}
		return Optional.empty();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<ObstacleImpl>> getObstacles() {
		final List<GameEntity> obstacles = this.getContainer().get(GameEntities.OBSTACLE).get();
		if (!obstacles.isEmpty() && obstacles.get(0) instanceof ObstacleImpl) {
			return Optional.ofNullable(obstacles.stream()
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
		final List<GameEntity> weapons = this.getContainer().get(GameEntities.WEAPON).get();
		if (!weapons.isEmpty() && weapons.get(0) instanceof Weapon) {
			return Optional.ofNullable(weapons.stream()
					.map(e -> (Weapon) e)
					.collect(Collectors.toList()));
		}
		return Optional.empty();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<Bullet>> getBullets() {
		final List<GameEntity> bullets = this.getContainer().get(GameEntities.BULLET).get();
		if (!bullets.isEmpty() && bullets.get(0) instanceof Bullet) {
			return Optional.ofNullable(bullets.stream()
					.map(e -> (Bullet) e)
					.collect(Collectors.toList()));
		}
		return Optional.empty();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<Platform>> getPlatforms() {
		final List<GameEntity> platforms = this.getContainer().get(GameEntities.PLATFORM).get();
		if (!platforms.isEmpty() && platforms.get(0) instanceof Platform) {
			return Optional.ofNullable(platforms.stream()
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
		this.getContainer().get(GameEntities.PLAYER).get().add(player);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addEnemy(final Enemy enemy) {
		if (this.getContainer().get(GameEntities.ENEMY).get().contains(enemy)) {
			return false;
		} else {
			this.getContainer().get(GameEntities.ENEMY).get().add(enemy);
			return true;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addItem(final Item item) {
		final PickupItem pickupItem = (PickupItem) item;
		if (this.getContainer().get(GameEntities.PICKUP_ITEM).get().contains(pickupItem)) {
			return false;
		} else {
			this.getContainer().get(GameEntities.PICKUP_ITEM).get().add(pickupItem);
			return true;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addObstacle(final Obstacle obstacle) {
		final ObstacleImpl obstacleImpl = (ObstacleImpl) obstacle;
		if (this.getContainer().get(GameEntities.OBSTACLE).get().contains(obstacleImpl)) {
			return false;
		} else {
			this.getContainer().get(GameEntities.OBSTACLE).get().add(obstacleImpl);
			return true;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addWeapon(final Weapon weapon) {
		/*
		 * Explicit cast from Weapon to WeaponImpl, 
		 * in order to match GameEntity type of elements 
		 * in container.
		 */
		final WeaponImpl weaponImpl = (WeaponImpl) weapon;
		if (this.getContainer().get(GameEntities.WEAPON).get().contains(weaponImpl)) {
			return false;
		} else {
			this.getContainer().get(GameEntities.WEAPON).get().add(weaponImpl);
			return true;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addBullet(final Bullet bullet) {
		final BulletImpl bulletImpl = (BulletImpl) bullet;
		if (this.getContainer().get(GameEntities.BULLET).get().contains(bulletImpl)) {
			return false;
		} else {
			this.getContainer().get(GameEntities.BULLET).get().add(bulletImpl);
			return true;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addPlatform(final Platform platform) {
		if (this.getContainer().get(GameEntities.PLATFORM).get().contains(platform)) {
			return false;
		} else {
			this.getContainer().get(GameEntities.PLATFORM).get().add(platform);
			return true;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<PhysicalObject>> getObjsList() {
		final Optional<List<PhysicalObject>> mergedList = Optional.of(new ArrayList<>());
		for (final Optional<List<GameEntity>> entityList : this.getContainer().values()) {
			entityList.ifPresent(e -> mergedList.get().addAll(e));
		}
		return mergedList;
	}
	
}
