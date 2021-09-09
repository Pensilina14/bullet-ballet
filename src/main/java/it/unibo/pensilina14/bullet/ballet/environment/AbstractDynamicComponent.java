package it.unibo.pensilina14.bullet.ballet.environment;

public abstract class AbstractDynamicComponent implements PhysicalObject{
    
    private final Dimension2D dimension;
    private final MutablePosition2D position;
    private final double mass;
    
    public AbstractDynamicComponent(final Dimension2D dimension, 
            final MutablePosition2D position, final double mass) {
        this.dimension = dimension;
        this.position = position;
        this.mass = mass;
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

    public double getMass() {
        return this.mass;
    }
    
    public boolean move() {
        return false;
    }
    
    private boolean isWithinMapBoundaries() {
        /*
         * TODO
        */
        return false;
    }
    
}
