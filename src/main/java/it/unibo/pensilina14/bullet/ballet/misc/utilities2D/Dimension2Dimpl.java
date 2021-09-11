package it.unibo.pensilina14.bullet.ballet.misc.utilities2D;

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
    return this.width;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + height;
    result = prime * result + width;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Dimension2Dimpl other = (Dimension2Dimpl) obj;
    if (height != other.height) {
      return false;
    }
    if (width != other.width) {
      return false;
    }
    return true;
  }
}
