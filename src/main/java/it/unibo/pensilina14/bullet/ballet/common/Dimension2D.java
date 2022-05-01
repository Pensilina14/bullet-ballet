package it.unibo.pensilina14.bullet.ballet.common;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface Dimension2D {

  /**
   * @return object's dimensions
   */
  Optional<ImmutablePair<Double, Double>> getSize();

  /**
   * @return object's height
   */
  double getHeight();

  /**
   * @return object's width
   */
  double getWidth();
}
