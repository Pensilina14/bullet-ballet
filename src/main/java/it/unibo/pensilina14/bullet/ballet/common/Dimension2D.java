package it.unibo.pensilina14.bullet.ballet.common;

import org.apache.commons.lang3.tuple.ImmutablePair;

public interface Dimension2D {
  /**
   * 
   * @return object's dimensions
   */
  ImmutablePair<Integer, Integer> getSize();
  
  /**
   * 
   * @return object's height
   */
  int getHeight();
  
  /**
   * 
   * @return object's width
   */
  int getWidth();
    
}
