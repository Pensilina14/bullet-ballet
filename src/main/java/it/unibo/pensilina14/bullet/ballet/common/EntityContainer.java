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
		final List<GameEntity> player = this.getContainer().get(GameEntities.PLAYER).get()
				.stream()
				.collect(Collectors.toList());
		if (!player.isEmpty() && player.get(0) instanceof Player) {
			return Optional.of((Player) player.get(0));
		}
		return Optional.empty();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<Enemy>> getEnemies() {
		final List<GameEntity> enemies = this.getContainer().get(GameEntities.ENEMY).get()
				.stream()
				.collect(Collectors.toList());
		if (!enemies.isEmpty() && enemies.get(0) instanceof Enemy) {
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
		final List<GameEntity> items = this.getContainer().get(GameEntities.PICKUP_ITEM).get()
				.stream()
				.collect(Collectors.toList());
		if (!items.isEmpty() && items.get(0) instanceof PickupItem) {
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
	public Optional<List<ObstacleImpl>> getObstacles() {
		final List<GameEntity> obstacles = this.getContainer().get(GameEntities.OBSTACLE).get()
				.stream()
				.collect(Collectors.toList());
		if (!obstacles.isEmpty() && obstacles.get(0) instanceof ObstacleImpl) {
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
		final List<GameEntity> weapons = this.getContainer().get(GameEntities.WEAPON).get()
				.stream()
				.collect(Collectors.toList());
		if (!weapons.isEmpty() && weapons.get(0) instanceof Weapon) {
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
	public Optional<List<BulletImpl>> getBullets() {
		final List<GameEntity> bullets = this.getContainer().get(GameEntities.BULLET).get()
				.stream()
				.collect(Collectors.toList());
		if (!bullets.isEmpty() && bullets.get(0) instanceof BulletImpl) {
			return Optional.of(bullets.stream()
					.map(e -> (BulletImpl) e)
					.collect(Collectors.toList()));
		}
		return Optional.empty();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<Platform>> getPlatforms() {
		final List<GameEntity> platforms = this.getContainer().get(GameEntities.PLATFORM).get()
				.stream()
				.collect(Collectors.toList());
		if (!platforms.isEmpty() && platforms.get(0) instanceof Platform) {
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
	public void setPlayer(final Optional<Player> player) {
		this.getContainer().get(GameEntities.PLAYER).get().add(player.get());
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
		Optional<PickupItem> pickupItem = Optional.empty();
		try {
			pickupItem = Optional.of((PickupItem) item);
		} catch (final ClassCastException e) {
			AppLogger.getAppLogger().error("Error while casting Item object to PickupItem object.");
			e.printStackTrace();
		}
		if (pickupItem.isPresent()) {
			if (this.getContainer().get(GameEntities.PICKUP_ITEM).get().contains(pickupItem.get())) {
				return false;
			} else {
				this.getContainer().get(GameEntities.PICKUP_ITEM).get().add(pickupItem.get());
				return true;
			}
		}
		return false;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addObstacle(final Obstacle obstacle) {
		Optional<ObstacleImpl> obstacleImpl = Optional.empty();
		try {
			obstacleImpl = Optional.of((ObstacleImpl) obstacle);
		} catch (final ClassCastException e) {
			AppLogger.getAppLogger().error("Error while casting Item object to PickupItem object.");
			e.printStackTrace();
		}
		if (obstacleImpl.isPresent()) {
			if (this.getContainer().get(GameEntities.OBSTACLE).get().contains(obstacleImpl.get())) {
				return false;
			} else {
				this.getContainer().get(GameEntities.OBSTACLE).get().add(obstacleImpl.get());
				return true;
			}
		}
		return false;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addWeapon(final Weapon weapon) {
		Optional<WeaponImpl> weaponImpl = Optional.empty();
		try {
			weaponImpl = Optional.of((WeaponImpl) weapon);
		} catch (final ClassCastException e) {
			AppLogger.getAppLogger().error("Error while casting Item object to PickupItem object.");
			e.printStackTrace();
		}
		if (weaponImpl.isPresent()) {
			if (this.getContainer().get(GameEntities.WEAPON).get().contains(weaponImpl.get())) {
				return false;
			} else {
				this.getContainer().get(GameEntities.WEAPON).get().add(weaponImpl.get());
				return true;
			}
		}
		return false;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addBullet(final Bullet bullet) {
		Optional<BulletImpl> bulletImpl = Optional.empty();
		try {
			bulletImpl = Optional.of((BulletImpl) bullet);
		} catch (final ClassCastException e) {
			AppLogger.getAppLogger().error("Error while casting Bullet object to BulletImpl object.");
			e.printStackTrace();
		}
		if (bulletImpl.isPresent()) {
			if (this.getContainer().get(GameEntities.BULLET).get().contains(bulletImpl.get())) {
				return false;
			} else {
				this.getContainer().get(GameEntities.BULLET).get().add(bulletImpl.get());
				return true;
			}
		}
		return false;
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
		if (this.getPlayer().isPresent()) {
			mergedList.get().addAll(List.of(this.getPlayer().get()));
		}
		if (this.getEnemies().isPresent()) {
			mergedList.get().addAll(this.getEnemies().get());
		}
		if (this.getObstacles().isPresent()) {
			mergedList.get().addAll(this.getObstacles().get());
		}
		if (this.getItems().isPresent()) {
            mergedList.get().addAll(this.getItems().get());
		}
		if (this.getPlatforms().isPresent()) {
			mergedList.get().addAll(this.getPlatforms().get());
		}
		if (this.getWeapons().isPresent()) {
			mergedList.get().addAll(this.getWeapons().get());
		}
		if (this.getBullets().isPresent()) {
			mergedList.get().addAll(this.getBullets().get());
		}
		return mergedList;
	}
	
}
