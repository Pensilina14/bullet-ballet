package it.unibo.pensilina14.bullet.ballet.environment;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class Dimension2Dimpl implements Dimension2D {

  private final int height;
  private final int width;
    
  public Dimension2Dimpl(final int height, final int width) {
    this.height = height;
    this.width = width;
  }

  @Override
  public ImmutablePair<Integer, Integer> getSize() {
    return ImmutablePair.of(this.height, this.width);
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.getWidth();
  }
    
}
