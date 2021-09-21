package it.unibo.pensilina14.bullet.ballet.common;

public class SpeedVector2DImpl implements SpeedVector2D{

    private final MutablePosition2D position;
    private final double speed;
    
    public SpeedVector2DImpl(final MutablePosition2D position, final double speed) {
        this.position = position;
        this.speed = speed;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public void vectorSum(final double x, final double y) {
        this.noSpeedVectorSum(x * speed, y * speed);
    }
    
    @Override
    public void noSpeedVectorSum(final double x, final double y) {
        this.position.setPosition(this.position.getX() + x, this.position.getY() + y);
        
    }

    @Override
    public MutablePosition2D getPosition() {
        return this.position;
    }

}
