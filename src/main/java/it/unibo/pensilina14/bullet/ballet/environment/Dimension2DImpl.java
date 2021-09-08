package it.unibo.pensilina14.bullet.ballet.environment;

import org.apache.commons.lang3.tuple.Pair;

public class Dimension2DImpl implements Dimension2D{

    private final int height;
    private final int width;
    
    public Dimension2DImpl(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public Pair<Integer, Integer> getSize() {
        return Pair.of(this.height, this.width);
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
