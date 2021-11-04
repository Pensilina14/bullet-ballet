package it.unibo.pensilina14.bullet.ballet.model.entities;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public abstract class AbstractDynamicComponent implements PhysicalObject{
    
    private static final double MS_TO_S = 0.001;
    private static final double X_AXIS = 31.5;
    private static final double Y_AXIS = 17.5;
    
    private final Dimension2D dimension;
    private final Environment gameEnvironment;
    private final double mass;
    private final SpeedVector2D vector;
    
    public AbstractDynamicComponent(final Dimension2D dimension, 
            final Environment gameEnvironment, final double mass, 
            final SpeedVector2D vector) {
        this.dimension = dimension;
        this.gameEnvironment = gameEnvironment;
        this.mass = mass;
        this.vector = vector;
    }

    @Override
    public MutablePosition2D getPosition() {
        return this.vector.getPosition();
    }

    @Override
    public Boolean isCollidingWith(final PhysicalObject other) {
        return this.getPosition().getX() * this.dimension.getWidth() / 2 > other.getPosition().getX() &&
        		this.getPosition().getX() < other.getPosition().getX() * other.getDimension().getWidth() / 2 &&
        		this.getPosition().getY() * this.getDimension().getHeight() / 2 > other.getPosition().getY() &&
        		this.getPosition().getY() < other.getPosition().getY() * other.getDimension().getWidth() / 2;
    }

    @Override
    public Dimension2D getDimension() {
        return this.dimension;
    }

    @Override
    public Environment getGameEnvironment() {
        return this.gameEnvironment;
    }

    public SpeedVector2D getSpeedVector() {
        return this.vector;
    }
    
    public double getMass() {
        return this.mass;
    }
    
    public void moveUP(final double y) {
        this.move(0, -y);
    }
    
    public void moveDOWN(final double y) {
        this.move(0, y);
    }
    
    public void moveRIGHT(final double x) {
        this.move(x, 0);
    }
    
    public void moveLEFT(final double x) {
        this.move(-x, 0);
    }
    
    public void move(final double x, final double y) {
        if (this.isWithinMapBoundaries(x, y)) {
            this.vector.vectorSum(x, y);
        }
        
    }
    
    public void updateState() {
        this.vector.noSpeedVectorSum(MS_TO_S, MS_TO_S);
    }
    
    
    private boolean isWithinMapBoundaries(final double x, final double y) {    
        return isWithinXaxis(x) && isWithinYaxis(y);
    }
    
    private boolean isWithinXaxis(final double x) {
        return this.vector.getPosition().getX() + x >= 0
        		&& this.vector.getPosition().getX() + x < X_AXIS;
    }
    
    private boolean isWithinYaxis(final double y) {
        return this.vector.getPosition().getY() + y >= 0
        		&& this.vector.getPosition().getY() + y < Y_AXIS;
    }
    
    public double getGravityForce() {
        return gameEnvironment.getGravity() * this.mass;
    }

}