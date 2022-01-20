package it.unibo.pensilina14.bullet.ballet.model.environment;

import java.util.List;
import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.Obstacle;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
import it.unibo.pensilina14.bullet.ballet.model.weapon.PickupItem;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;

/**
 * This interface wraps all the virtual game world and permits interaction
 * between objects inside a virtual map.
 */
public interface Environment {
	/**
	 * @return environment's gravity.
	 */
	double getGravity();
	
	/**
	 * @return game environment dimension: height and width.
	 */
	Dimension2D getDimension();

	/**
	 * @return a {@link List<PhysicalObject>} that contains every object in the game.
	 */
	Optional<List<PhysicalObject>> getObjsList();
	
	/**
	 * @return player of environment.
	 */
	Optional<Player> getPlayer();

	/**
	 * @return {@link List} of enemies({@link Enemy}) present in the environment.
	 */
	Optional<List<Enemy>> getEnemies();
	
	/**
	 * @return {@link List} of obstacles({@link StaticObstacle}, {@link DynamicObstacle})
	 * present in the environment.
	 */
	Optional<List<ObstacleImpl>> getObstacles();
	
	/**
	 * @return {@link List} of items({@link Item}) present in the environment.
	 */
	Optional<List<PickupItem>> getItems();
	
	/**
	 * 
	 * @return {@link List} of platforms{@link Platform}) that compose the game trail.
	 */
	Optional<List<Platform>> getPlatforms();
	
	/**
	 * 
	 * @return {@link List} of weapons({@link Weapon}) present in the environment.
	 */
	Optional<List<Weapon>> getWeapons();

	/**
	 *
	 * @return {@link List} of coins ({@link Coin}) present in the environment.
	 */
	//Optional<List<Coin>> getCoins();
	
	/**
	 * 
	 * @return {@link List} of bullets({@link Bullet}) present in the environment.
	 */
	Optional<List<Bullet>> getBullets();
	
	/**
	 * Sets the player.
	 * 
	 * @param player is the main character of the game.
	 */
	void setPlayer(Player player);
	
	/**
	 * @param enemy which is the {@link Enemy} to be added.
	 * 
	 * @return boolean representing the success of the operation.
	 * Unsuccess is guaranteed if character is already present.
	 */
	boolean addEnemy(Enemy enemy);
	
	/**
	 * @param obstacle which is an {@link ObstacleImpl} to be added.
	 * 
	 * @return boolean representing the success of the operation.
	 * Unsuccess is guaranteed if obstacle is already present and in case of a wrong parameter.
	 */
	boolean addObstacle(Obstacle obstacle);
	
	/**
	 * @param item which is the {@link PickupItem} to be added.
	 * 
	 * @return boolean representing the success of the operation.
	 * Unsuccess is guaranteed if item is already present.
	 */
	boolean addItem(Item item);
	
	/**
	 * @param platform indicates the {@link Platform} to be added.
	 * 
	 * @return boolean representing the success of the operation.
	 * Unsuccess is guaranteed if platform is already present.
	 */
	boolean addPlatform(Platform platform);
	
	/**
	 * @param weapon is the {@link Weapon} to be added.
	 * 
	 * @return boolean representing the success of the operation.
	 * Unsuccess is guaranteed if weapon is already present.
	 */
	boolean addWeapon(Weapon weapon);
	
	/**
	 * @param bullet is the {@link Bullet} to be added.
	 * 
	 * @return boolean representing the success of the operation.
	 * Unsuccess is guaranteed if bullet is already present.
	 */
	boolean addBullet(Bullet bullet);

	/**
	 *
	 * @param coin is the {@link Coin} to be added.
	 * @return boolean representing the success of the operation.
	 */
	//boolean addCoin(Coin coin);

	/**
	 * @param position of the object to be deleted.
	 * 
	 * The {@link PhysicalObject} to be deleted is first searched and then removed.
	 * 
	 * @return true if a {@link PhysicalObject} has been deleted.
	 * 		   false otherwise. 
	 * 		   Could be false if there was no object at position..
	 */
	boolean deleteObjByPosition(ImmutablePosition2D position);
	
	/**
	 * @param dt is the given time instant when the environment must be updated
	 * 
	 * This must recall every {@link AbstractDynamicComponent}'s {@link AbstractDynamicComponent#updateState} method,
	 * in order to update the whole environment. 
	 */
	void updateState();
	
	/**
	 * Sets the event listener for the environment, so it
	 * can notify events to the listener aka the controller.
	 * 
	 * @param listener to be set
	 */
	void setEventListener(GameEventListener listener);
	
	/**
	 * Provides important constants for gravity representation.
	 * EARTH and MOON's provided.
	 */
	enum GravityConstants {
		TEST(1.0),
		EARTH(9.81),
		MOON(6.673);
		
		private final double value;

		GravityConstants(final double value) {
			this.value = value;
		}
		
		public double getValue() {
			return this.value;
		}
	}

}
