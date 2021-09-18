package it.unibo.pensilina14.bullet.ballet.common;

public interface SpeedVector2D {

    double getSpeed();
    void vectorSum(double x, double y);
    void noSpeedVectorSum(double x, double y);
    MutablePosition2D getPosition();
    
}
