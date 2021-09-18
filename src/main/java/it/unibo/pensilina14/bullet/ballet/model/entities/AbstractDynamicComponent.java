package it.unibo.pensilina14.bullet.ballet.model.entities;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public abstract class AbstractDynamicComponent implements PhysicalObject{
    
    private static final double MS_TO_S = 0.001;
    private final Dimension2D dimension;
    private final MutablePosition2D position;
    private final Environment gameEnvironment;
    private final double mass;
    
    public AbstractDynamicComponent(final Dimension2D dimension, 
            final MutablePosition2D position, final Environment gameEnvironment,
            final double mass) {
        this.dimension = dimension;
        this.position = position;
        this.gameEnvironment = gameEnvironment;
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

    @Override
    public Environment getGameEnvironment() {
        return this.gameEnvironment;
    }

    public double getMass() {
        return this.mass;
    }
    
    public boolean moveUP(double y) {
        return this.move(0, Math.abs(y));
    }
    
    public boolean moveDOWN(double y) {
        return this.move(0, -Math.abs(y));
    }
    
    public boolean moveRIGHT(double x) {
        return this.move(Math.abs(x), 0);
    }
    
    public boolean moveLEFT(double x) {
        return this.move(-Math.abs(x), 0);
    }
    
    public boolean move(double x, double y) {
        if (isWithinMapBoundaries(x, y)) {
            vectorialSum(x, y);
            return true;
        }
        return false;
    }
    
    public void updateState(double dt) {
        this.vectorialSum(dt * MS_TO_S, dt * MS_TO_S);
    }
    
    private void vectorialSum(double x, double y) {
        this.position.setPosition(x + this.position.getX(), y + this.position.getY());
    }
    
    private boolean isWithinMapBoundaries(double x, double y) {    
        return isWithinXaxis(x) && isWithinYaxis(y);
    }
    
    private boolean isWithinXaxis(double x) {
        Dimension2D envDimension = this.gameEnvironment.getDimension();
        return this.position.getX() + x >= -envDimension.getWidth()
                && this.position.getX() + x + this.dimension.getWidth() <= 0;
    }
    
    private boolean isWithinYaxis(double y) {
        Dimension2D envDimension = this.gameEnvironment.getDimension();
        return this.position.getY() + y <= 0
                && this.position.getY() + y - this.dimension.getHeight() >= -envDimension.getHeight();
    }
    
    public double getGravityForce() {
        return gameEnvironment.getGravity() * this.mass;
    }
}