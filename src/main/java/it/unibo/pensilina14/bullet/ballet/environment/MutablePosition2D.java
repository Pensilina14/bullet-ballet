package it.unibo.pensilina14.bullet.ballet.environment;

import org.apache.commons.lang3.tuple.MutablePair;

/**
 * Represents a mutable position in a 2D 
 * environment. It encapsulates apache commons MutablePair class.
 */
public interface MutablePosition2D {
  /*
   * @return a {@link MutablePair} representing position.
   */
  MutablePair<Integer, Integer> getPosition();
  
  /*
   * @return coordinate X of position
   */
  int getX();
  
  /*
   * @return coordinate Y of position
   */
  int getY();
  
  /*
   * @param x is x coordinate. 
   * @param y represents y coordinate.
   */
  void setPosition(int x, int y);
}
