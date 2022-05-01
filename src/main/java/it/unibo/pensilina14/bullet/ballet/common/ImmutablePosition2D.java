package it.unibo.pensilina14.bullet.ballet.common;

import org.apache.commons.lang3.tuple.ImmutablePair;

/** Represents an immutable position in a choosen point of the environment map. */
public interface ImmutablePosition2D {
  /*
   * @return an {@link ImmutablePair} representing the position.
   */
  ImmutablePair<Double, Double> getPosition();

  /*
   * @return coordinate X of position
   */
  double getX();

  /*
   * @return coordinate Y of position
   */
  double getY();
}
