package it.unibo.pensilina14.bullet.ballet.model.entities;

import java.util.Optional;

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
    public Optional<MutablePosition2D> getPosition() {
        return this.vector.getPosition();
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

    public Optional<SpeedVector2D> getSpeedVector() {
        return Optional.of(this.vector);
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
        return this.vector.getPosition().get().getX() + x >= 0
        		&& this.vector.getPosition().get().getX() + x < X_AXIS;
    }
    
    private boolean isWithinYaxis(final double y) {
        return this.vector.getPosition().get().getY() + y >= 0
        		&& this.vector.getPosition().get().getY() + y < Y_AXIS;
    }
    
    public double getGravityForce() {
        return gameEnvironment.getGravity() * this.mass;
    }

}