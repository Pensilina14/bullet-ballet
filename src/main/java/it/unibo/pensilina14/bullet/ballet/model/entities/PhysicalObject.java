package it.unibo.pensilina14.bullet.ballet.model.entities;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

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
  
 /*
 * 
 */
  Dimension2D getDimension();
  
  /*
   * 
  */
  Environment getGameEnvironment();
}
