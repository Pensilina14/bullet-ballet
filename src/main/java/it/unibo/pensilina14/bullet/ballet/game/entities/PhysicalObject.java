package it.unibo.pensilina14.bullet.ballet.game.entities;

import it.unibo.pensilina14.bullet.ballet.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.MutablePosition2D;

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
