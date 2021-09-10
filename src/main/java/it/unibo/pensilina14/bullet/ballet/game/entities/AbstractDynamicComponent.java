package it.unibo.pensilina14.bullet.ballet.game.entities;

import it.unibo.pensilina14.bullet.ballet.environment.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.environment.MutablePosition2D;

public abstract class AbstractDynamicComponent implements PhysicalObject{
    
    private final Dimension2D dimension;
    private final MutablePosition2D position;
    private final Environment environment;
    private final double mass;
    
    public AbstractDynamicComponent(final Dimension2D dimension, 
            final MutablePosition2D position, final Environment environment,
            final double mass) {
        this.dimension = dimension;
        this.position = position;
        this.environment = environment;
        this.mass = mass;
    }

    @Override
    public MutablePosition2D getPosition() {
        return this.position;
    }

    @Override
    public Boolean isCollidingWith(PhysicalObject other) {
        return this.position.getX() == other.getPosition().getX()
                || this.position.getY() == other.getPosition().getY();
    }

    @Override
    public Dimension2D getDimension() {
        return this.dimension;
    }

    public double getMass() {
        return this.mass;
    }
    
    public boolean move(int x, int y) {
        if (isWithinMapBoundaries(x, y)) {
            vectorialSum(x, y);
            return true;
        }
        return false;
    }
    
    private void vectorialSum(int x, int y) {
        this.position.setPosition(x + this.position.getX(), y + this.position.getY());
    }
    
    private boolean isWithinMapBoundaries(int x, int y) {    
        return this.isWithinLeftUpperCorner() && 
                this.isWithinLeftLowerCorner(y) &&
                this.isWithinRightLowerCorner(x, y) &&
                this.isWithinRightUpperCorner(x);
    }
    
    private boolean isWithinLeftUpperCorner() {
        return this.position.getX() < GameEnvironment.DEFAULT_DIM && this.position.getY() > 0;
    }
    
    private boolean isWithinLeftLowerCorner(int y) {
        return this.position.getX() < GameEnvironment.DEFAULT_DIM && this.position.getY() + y < GameEnvironment.DEFAULT_DIM;
    }
    
    private boolean isWithinRightLowerCorner(int x, int y) {
        return this.position.getX() - x > 0 && this.position.getY() + y < GameEnvironment.DEFAULT_DIM;
    }

    private boolean isWithinRightUpperCorner(int x) {
        return this.position.getX() - x < GameEnvironment.DEFAULT_DIM && this.position.getY() > 0;
    }
    
    public Environment getEnvironment() {
        return this.environment;
    }
    
}
