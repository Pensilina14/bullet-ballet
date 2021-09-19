package it.unibo.pensilina14.bullet.ballet.common;

import java.util.Optional;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;

public class MutablePosition2Dimpl implements MutablePosition2D {

  private Optional<MutablePair<Double, Double>> position;

  public MutablePosition2Dimpl(final double x, final double y) {
    this.position = Optional.ofNullable(MutablePair.of(x, y));
  }

  @Override
  public MutablePair<Double, Double> getCoordinates() {
    return this.position.get();
  }

  @Override
  public double getX() {
    return this.position.get().getLeft();
  }

  @Override
  public double getY() {
    return this.position.get().getRight();
  }

  @Override
  public void setPosition(double x, double y) {
    this.position = Optional.ofNullable(MutablePair.of(x, y));
  }
}
