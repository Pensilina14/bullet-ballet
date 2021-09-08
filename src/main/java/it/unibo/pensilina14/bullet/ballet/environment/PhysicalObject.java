package it.unibo.pensilina14.bullet.ballet.environment;


/**
 * 
 *
 */
public interface PhysicalObject {
	/*
	 * @return position in coordinates.
	 */
    
	MutablePosition2D getPosition();
	
	/*
	 * @return true if this PhysicalObject is colliding with {@param other},
	 * @return false otherwise.
	 */
	Boolean isCollidingWith(PhysicalObject other);
	
	Dimension2D getDimension();
}
