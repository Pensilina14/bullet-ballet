package it.unibo.pensilina14.bullet.ballet.model.entities;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public abstract class AbstractStaticComponent implements PhysicalObject{

    private final Dimension2D dimension;
    private final MutablePosition2D position;
    private final Environment gameEnvironment;
    
    public AbstractStaticComponent(final Dimension2D dimension, final MutablePosition2D position, 
            final Environment gameEnvironment) {
        this.dimension = dimension;
        this.position = position;
        this.gameEnvironment = gameEnvironment;
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

    @Override
    public Environment getGameEnvironment() {
        return this.gameEnvironment;
    }
    
    
    
}
