package it.unibo.pensilina14.bullet.ballet.game.entities;

import it.unibo.pensilina14.bullet.ballet.environment.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.environment.MutablePosition2D;

public abstract class AbstractStaticComponent implements PhysicalObject{

    private final Dimension2D dimension;
    private final MutablePosition2D position;
    
    public AbstractStaticComponent(final Dimension2D dimension, final MutablePosition2D position) {
        this.dimension = dimension;
        this.position = position;
    }

    @Override
    public MutablePosition2D getPosition() {
        return this.position;
    }

    @Override
    public Boolean isCollidingWith(PhysicalObject other) {
        return null;
    }

    @Override
    public Dimension2D getDimension() {
        return this.dimension;
    }
    
}
