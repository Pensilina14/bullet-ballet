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

public class EntityContainer extends AbstractContainer<GameEntity> implements EntityManager {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<Player>> getPlayer() {
		final List<GameEntity> player = super.container.get(GameEntities.PLAYER);
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
		final List<GameEntity> enemies = super.container.get(GameEntities.ENEMY);
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
		final List<GameEntity> items = super.container.get(GameEntities.PICKUP_ITEM);
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
		final List<GameEntity> obstacles = super.container.get(GameEntities.OBSTACLE);
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
		final List<GameEntity> weapons = super.container.get(GameEntities.WEAPON);
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
		final List<GameEntity> platforms = super.container.get(GameEntities.PLATFORM);
		if (platforms.get(0) instanceof Enemy) {
			return Optional.of(platforms.stream()
					.map(e -> (Platform) e)
					.collect(Collectors.toList()));
		}
		return Optional.empty();
	}

}
