package it.unibo.pensilina14.bullet.ballet.environment;

import org.apache.commons.lang3.tuple.*;

/**
 * 
 *
 */
public interface PhysicalObject {
	/*
	 * @return position in coordinates.
	 */
    /*
	Pair<Integer, Integer> getPosition();
	*/
	/*
	 * @return true if this PhysicalObject is colliding with {@param other},
	 * @return false otherwise.
	 */
	Boolean isCollidingWith(PhysicalObject other);
}
