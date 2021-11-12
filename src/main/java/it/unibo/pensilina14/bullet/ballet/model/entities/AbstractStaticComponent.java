package it.unibo.pensilina14.bullet.ballet.model.entities;

import java.util.Optional;

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
    public Optional<MutablePosition2D> getPosition() {
        return Optional.of(this.position);
    }

    @Override
    public Boolean isCollidingWith(final PhysicalObject other) {
        return this.getPosition().get().getX() * this.dimension.getWidth() / 2 > other.getPosition().get().getX() &&
        		this.getPosition().get().getX() < other.getPosition().get().getX() * other.getDimension().get().getWidth() / 2 &&
        		this.getPosition().get().getY() * this.getDimension().get().getHeight() / 2 > other.getPosition().get().getY() &&
        		this.getPosition().get().getY() < other.getPosition().get().getY() * other.getDimension().get().getWidth() / 2;
    }

    @Override
    public Optional<Dimension2D> getDimension() {
        return Optional.of(this.dimension);
    }

    @Override
    public Optional<Environment> getGameEnvironment() {
        return Optional.of(this.gameEnvironment);
    }
    
    
    
}
