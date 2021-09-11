package it.unibo.pensilina14.bullet.ballet.misc.utilities2D;

import org.apache.commons.lang3.tuple.ImmutablePair;

public interface Dimension2D {
    
  ImmutablePair<Integer, Integer> getSize();
  
  int getHeight();
  
  int getWidth();
    
}
