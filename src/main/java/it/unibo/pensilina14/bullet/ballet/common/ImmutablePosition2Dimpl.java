package it.unibo.pensilina14.bullet.ballet.common;

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

  @Override
  public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((position == null) ? 0 : position.hashCode());
	return result;
}

  @Override
  public boolean equals(Object obj) {
	if (this == obj) {
		return true;
	}
	if (!(obj instanceof ImmutablePosition2Dimpl)) {
		return false;
	}
	final ImmutablePosition2Dimpl other = (ImmutablePosition2Dimpl) obj;
	if (position == null) {
		if (other.position != null) {
			return false;
		}
	} else if (!position.equals(other.position)) {
		return false;
	}
	return true;
  }
}
