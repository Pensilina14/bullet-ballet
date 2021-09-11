package it.unibo.pensilina14.bullet.ballet.misc.utilities2D;

import java.util.Optional;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;

public class MutablePosition2Dimpl implements MutablePosition2D {

  private Optional<MutablePair<Integer, Integer>> position;

  public MutablePosition2Dimpl(final int x, final int y) {
    this.position = Optional.ofNullable(MutablePair.of(x, y));
  }

  @Override
  public MutablePair<Integer, Integer> getPosition() {
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

  @Override
  public void setPosition(int x, int y) {
    this.position = Optional.ofNullable(MutablePair.of(x, y));
  }
}
