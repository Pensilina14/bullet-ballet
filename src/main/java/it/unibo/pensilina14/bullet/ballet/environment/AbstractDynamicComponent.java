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
    
    public boolean move(int x, int y, Environment env) {
        if (isWithinMapBoundaries(env)) {
            this.position.setPosition(x + this.position.getX(), y + this.position.getY());
            return true;
        }
        return false;
    }
    
    private boolean isWithinMapBoundaries(Environment env) {
        return this.position.getX() + this.dimension.getWidth() <= env.getDimension().getWidth() && 
                this.position.getY() + this.dimension.getHeight() <= env.getDimension().getHeight();
         
    }
    
}
