package it.unibo.pensilina14.bullet.ballet.environment;

import org.apache.commons.lang3.tuple.Pair;

public interface Dimension2D {
    
    Pair<Integer, Integer> getSize();
    int getHeight();
    int getWidth();
    
}
