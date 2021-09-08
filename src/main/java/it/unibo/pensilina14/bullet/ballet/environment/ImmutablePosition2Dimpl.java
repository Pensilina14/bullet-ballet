package it.unibo.pensilina14.bullet.ballet.environment;

import java.util.Optional;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class ImmutablePosition2Dimpl implements ImmutablePosition2D {

  private final Optional<ImmutablePair<Integer, Integer>> position;

  public ImmutablePosition2Dimpl(final int x, final int y) {
    this.position = Optional.ofNullable(ImmutablePair.of(x, y));
  }

  @Override
  public ImmutablePair<Integer, Integer> getPosition() {
    return this.position.get();
  }

  @Override
  public int getX() {
    return this.position.get().getLeft();
  }

  @Override
  public int getY() {
    return this.position.get().getRight();
  }
}
