package it.unibo.pensilina14.bullet.ballet.common;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public class ImmutablePosition2Dimpl implements ImmutablePosition2D {

  private final Optional<ImmutablePair<Double, Double>> position;

  public ImmutablePosition2Dimpl(final double x, final double y) {
    this.position = Optional.ofNullable(ImmutablePair.of(x, y));
  }

  public ImmutablePosition2Dimpl(final MutablePosition2D pos) {
    this.position = Optional.ofNullable(ImmutablePair.of(pos.getX(), pos.getY()));
  }

  @Override
  public ImmutablePair<Double, Double> getPosition() {
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
    if (!(obj instanceof ImmutablePosition2Dimpl)) {
      return false;
    }
    final ImmutablePosition2Dimpl other = (ImmutablePosition2Dimpl) obj;
    if (position == null) {
        return other.position == null;
    } else return position.equals(other.position);
  }
}
