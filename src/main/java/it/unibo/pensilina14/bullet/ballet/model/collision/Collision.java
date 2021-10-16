package it.unibo.pensilina14.bullet.ballet.model.collision;

import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

public interface Collision {

	/*
	 *  @return true if the first object collides with the second.
	 */
	boolean isCollidingWith(PhysicalObject firstObject, PhysicalObject otherObject);
}
