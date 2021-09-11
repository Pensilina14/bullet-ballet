package it.unibo.pensilina14.bullet.ballet.misc.utilities2D;

import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * Represents an immutable position in a
 * choosen point of the environment map.
 *
 */
public interface ImmutablePosition2D {
  /*
   * @return an {@link ImmutablePair} representing the position.
   */
  ImmutablePair<Integer, Integer> getPosition();
  
  /*
   * @return coordinate X of position
   */
  int getX();
  
  /*
   * @return coordinate Y of position
   */
  int getY();
}
