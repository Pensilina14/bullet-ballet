package it.unibo.pensilina14.bullet.ballet.common;

import org.apache.commons.lang3.tuple.MutablePair;

/**
 * Represents a mutable position in a 2D 
 * environment. It encapsulates apache commons MutablePair class.
 */
public interface MutablePosition2D {
  /*
   * @return a {@link MutablePair} representing position.
   */
  MutablePair<Double, Double> getCoordinates();
  
  /*
   * @return coordinate X of position
   */
  double getX();
  
  /*
   * @return coordinate Y of position
   */
  double getY();
  
  /*
   * @param x is x coordinate. 
   * @param y represents y coordinate.
   */
  void setPosition(double x, double y);
}
