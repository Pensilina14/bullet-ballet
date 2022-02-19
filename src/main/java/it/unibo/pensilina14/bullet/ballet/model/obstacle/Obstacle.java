package it.unibo.pensilina14.bullet.ballet.model.obstacle;

import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

public interface Obstacle extends PhysicalObject{
	/**
	 * This method will make obstacles move up and down
	 */
	void spin();
	/**
	 * 
	 * @return true, if the obstacle needs to move Up, false otherwise
	 */
	boolean hasUpMovement();
	/**
	 * 
	 * @return true, if the obstacle needs to move Down, false otherwise
	 */
	boolean hasDownMovement();
}
