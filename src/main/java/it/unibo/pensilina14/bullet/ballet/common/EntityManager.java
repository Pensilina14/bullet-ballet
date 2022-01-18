package it.unibo.pensilina14.bullet.ballet.common;

import java.util.List;
import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.PickupItem;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;

/**
 * Contract for classes that implement {@link AbstractContainer} 
 * and have a need to retrieve container content.
 * 
 * This is a game-specific interface so it is supposed to 
 * give out all the different entities of the game.
 */
public interface EntityManager {
	/**
	 * {@link Player} getter.
	 * 
	 * @return the player
	 */
	Optional<List<Player>> getPlayer();
	/**
	 * Enemies getter.
	 * 
	 * @return the enemies, refer to {@link Enemy}.
	 */
	Optional<List<Enemy>> getEnemies();
	/**
	 * Pickup Items getter.
	 * 
	 * @return the PickupItem list, refer to {@link PickupItem}.
	 */
	Optional<List<PickupItem>> getItems();
	/**
	 * Obstacles getter.
	 * 
	 * @return the obstacles, refer to {@link ObstacleImpl}.
	 */
	Optional<List<ObstacleImpl>> getObstacle();
	/**
	 * Weapons getter.
	 * 
	 * @return the weapons, refer to {@link Weapon}.
	 */
	Optional<List<Weapon>> getWeapons();
	/**
	 * Platforms getter.
	 * 
	 * @return the platforms, refer to {@link Platform}.
	 */
	Optional<List<Platform>> getPlatforms();
}
