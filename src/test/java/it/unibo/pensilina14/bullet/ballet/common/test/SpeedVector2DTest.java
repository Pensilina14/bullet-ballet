package it.unibo.pensilina14.bullet.ballet.common.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;

public class SpeedVector2DTest {

    private static final double POS = -5.0;
    private static final double SPEED = 1.5;
    private final SpeedVector2D speedVector = new SpeedVector2DImpl(new MutablePosition2Dimpl(POS, POS), SPEED);
    
    @Test
    public void testNoSpeedVectorSum() {
        final double addictionFactor = 5;
        final MutablePosition2D position = new MutablePosition2Dimpl(POS + addictionFactor, POS +addictionFactor);
        this.speedVector.noSpeedVectorSum(addictionFactor, addictionFactor);
        assertEquals(this.speedVector.getPosition().getCoordinates(), position.getCoordinates());
        
        
    }
    
    @Test
    public void testVectorSum() {
        final double addictionFactor = 5;
        final MutablePosition2D position = new MutablePosition2Dimpl(POS + (addictionFactor * SPEED)
                , POS + (addictionFactor * SPEED));
        this.speedVector.vectorSum(addictionFactor, addictionFactor);
        assertEquals(this.speedVector.getPosition().getCoordinates(), position.getCoordinates());
    }
    
    
    
}
