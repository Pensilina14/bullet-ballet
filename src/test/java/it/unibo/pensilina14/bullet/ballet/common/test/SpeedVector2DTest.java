package it.unibo.pensilina14.bullet.ballet.common.test;

import org.junit.Test;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;

public class SpeedVector2DTest {

    private static final double POS = -5.0;
    private static final double SPEED = 1.5;
    private final SpeedVector2D speedVector = new SpeedVector2DImpl(new MutablePosition2Dimpl(POS, POS), SPEED);
    
    @Test
    public void testVectorSum() {
        //TODO
    }
    
    @Test
    public void testNoSpeedVectorSum() {
        //TODO
    }
    
}
