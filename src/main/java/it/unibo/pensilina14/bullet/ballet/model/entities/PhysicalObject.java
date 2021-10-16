package it.unibo.pensilina14.bullet.ballet.model.entities;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

/**
 * 
 *
 */
public interface PhysicalObject {
	/**
	 * 
	 * @return object's coordinates
	 */
	MutablePosition2D getPosition();

	/**
	 * 
	 * @param other
	 * @return true if physicalObject is colliding with another one
	 */
	Boolean isCollidingWith(PhysicalObject other);

	/**
	 * 
	 * @return object's dimension
	 */
	Dimension2D getDimension();

	/**
	 * 
	 * @return gameEnvironment istance
	 */
	Environment getGameEnvironment();
}
