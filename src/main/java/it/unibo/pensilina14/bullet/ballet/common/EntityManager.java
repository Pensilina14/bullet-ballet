package it.unibo.pensilina14.bullet.ballet.common;

import java.util.List;
import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.Obstacle;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;
import it.unibo.pensilina14.bullet.ballet.model.weapon.BulletImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
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
	Optional<Player> getPlayer();
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
	Optional<List<ObstacleImpl>> getObstacles();
	/**
	 * Weapons getter.
	 * 
	 * @return the weapons, refer to {@link Weapon}.
	 */
	Optional<List<Weapon>> getWeapons();
	/**
	 * Gets a {@link List} of {@link Bullet}(s).
	 * 
	 * @return the bullets in-game.
	 */
	Optional<List<BulletImpl>> getBullets();
	/**
	 * Platforms getter.
	 * 
	 * @return the platforms, refer to {@link Platform}.
	 */
	Optional<List<Platform>> getPlatforms();
	/**
	 * Sets a given player({@link Player}).
	 * 
	 * @param player (to be set)
	 */
	void setPlayer(Optional<Player> player);
	/**
	 * Adds an enemy({@link Enemy}) to the game.
	 * 
	 * @param enemy
	 * @return true if enemy is not yet in the game,
	 * false otherwise.
	 */
	boolean addEnemy(Enemy enemy);
	/**
	 * Adds a pickup item({@link PickupItem}) to the game.
	 * 
	 * @param item
	 * @return true if item is not yet in the game,
	 * false otherwise.
	 */
	boolean addItem(Item item);
	/**
	 * Adds an obstacle({@link Obstacle}) to the game.
	 * 
	 * @param obstacle
	 * @return true if obstacle is not yet in the game,
	 * false otherwise.
	 */
	boolean addObstacle(Obstacle obstacle);
	/**
	 * @param platform indicates the {@link Platform} to be added.
	 * 
	 * @return boolean representing the success of the operation.
	 * Unsuccess is guaranteed if platform is already present.
	 */
	boolean addPlatform(Platform platform);
	/**
	 * Adds a weapon({@link Weapon}) to the game.
	 * 
	 * @param weapon
	 * @return true if weapon is not yet in the game,
	 * false otherwise.
	 */
	boolean addWeapon(Weapon weapon);
	/**
	 * Adds a bullet({@link Bullet}) to the game.
	 * 
	 * @param bullet
	 * @return true if bullet is added, false if not.
	 */
	boolean addBullet(Bullet bullet);
	/**
	 * @return a {@link List<PhysicalObject>} that contains every object in the game.
	 */
	Optional<List<PhysicalObject>> getObjsList();
}
