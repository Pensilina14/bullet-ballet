package it.unibo.pensilina14.bullet.ballet.model.environment;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.exceptions.NotAnObstacleException;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;

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
	 * @return a {@link List<PhysicalObject>} that contains every object in the game.
	 */
	Optional<List<PhysicalObject>> getObjsList();

	/**
	 * @param enemy which is the {@link Enemy} to be added.
	 * 
	 * @return boolean representing the success of the operation.
	 * Unsuccess is guaranteed if character is already present.
	 */
	boolean addEnemy(Enemy enemy);
	
	/**
	 * @param obstacle which is an Obstacle, {@link StaticObstacle} or {@link DynamicObstacle}, to be added.
	 * 
	 * @return boolean representing the success of the operation.
	 * Unsuccess is guaranteed if obstacle is already present.
	 * 
	 * @throws an exception if parameter is not a {@link DynamicObstacle} or a {@link StaticObstacle}.
	 */
	boolean addObstacle(PhysicalObject obstacle) throws NotAnObstacleException;
	
	/**
	 * @param item which is the {@link item} to be added.
	 * 
	 * @return boolean representing the success of the operation.
	 * Unsuccess is guaranteed if item is already present.
	 */
	boolean addItem(Item item);

	/**
	 * @param position of the object to be deleted.
	 * 
	 * The {@link PhysicalObject} to be deleted is first searched and then removed.
	 * 
	 * @return true if a {@link PhysicalObject} has been deleted.
	 * 		   false otherwise. 
	 * 		   Could be false, for example, if there was no object at position.
	 */
	boolean deleteObjByPosition(ImmutablePosition2D position);
	
	/**
	 * @param dt is the given time instant when the environment must be updated
	 * 
	 * This must recall every {@link AbstractDynamicComponent}'s {@link AbstractDynamicComponent#updateState} method,
	 * in order to update the whole environment. 
	 */
	void updateState(int dt);
}
