package it.unibo.pensilina14.bullet.ballet.common;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Optional;

public class MutablePosition2Dimpl implements MutablePosition2D {

  private Optional<MutablePair<Double, Double>> position;

  public MutablePosition2Dimpl(final double x, final double y) {
    this.position = Optional.ofNullable(MutablePair.of(x, y));
  }

  public MutablePosition2Dimpl(final ImmutablePosition2D immutablePos) {
    this.position = Optional.ofNullable(MutablePair.of(immutablePos.getX(), immutablePos.getY()));
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
  public void setPosition(final double x, final double y) {
    this.position = Optional.ofNullable(MutablePair.of(x, y));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((position == null) ? 0 : position.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof MutablePosition2Dimpl)) {
      return false;
    }
    final MutablePosition2Dimpl other = (MutablePosition2Dimpl) obj;
    if (position == null) {
        return other.position == null;
    } else return position.equals(other.position);
  }

  @Override
  public String toString() {
    return "[x=" + this.getX() + " y=" + this.getY() + "]";
  }
}
