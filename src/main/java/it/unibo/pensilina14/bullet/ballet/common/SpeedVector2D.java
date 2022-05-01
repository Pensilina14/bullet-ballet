package it.unibo.pensilina14.bullet.ballet.common;

import java.util.Optional;

public interface SpeedVector2D {

  /**
   * @return speed value
   */
  double getSpeed();
  /**
   * This method moves an object, it also computes speed.
   *
   * @param x represents movement in x axis direction
   * @param y represents movement in y axis direction
   */
  void vectorSum(double x, double y);
  /**
   * This method moves an object, it doesn't calculate speed.
   *
   * @param x represents movement in x axis direction
   * @param y represents movement in y axis direction
   */
  void noSpeedVectorSum(double x, double y);
  /**
   * @return vector's position
   */
  Optional<MutablePosition2D> getPosition();
}
