package it.unibo.pensilina14.bullet.ballet.game.entities;

import it.unibo.pensilina14.bullet.ballet.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.MutablePosition2D;

public abstract class AbstractDynamicComponent implements PhysicalObject{
    
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
    
    public boolean moveUP(int y) {
        return this.move(0, Math.abs(y));
    }
    
    public boolean moveDOWN(int y) {
        return this.move(0, -Math.abs(y));
    }
    
    public boolean moveRIGHT(int x) {
        return this.move(Math.abs(x), 0);
    }
    
    public boolean moveLEFT(int x) {
        return this.move(-Math.abs(x), 0);
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
        return isWithinXaxis(x) && isWithinYaxis(y);
    }
    
    private boolean isWithinXaxis(int x) {
        Dimension2D envDimension = this.gameEnvironment.getDimension();
        return this.position.getX() + x >= -envDimension.getWidth()
                && this.position.getX() + x + this.dimension.getWidth() <= 0;
    }
    
    private boolean isWithinYaxis(int y) {
        Dimension2D envDimension = this.gameEnvironment.getDimension();
        return this.position.getY() + y <= 0
                && this.position.getY() + y - this.dimension.getHeight() >= -envDimension.getHeight();
    }
    
    public double getGravityForce() {
        return gameEnvironment.getGravity() * this.mass;
    }
}