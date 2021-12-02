package it.unibo.pensilina14.bullet.ballet.model.item.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Items;

public class PickupItemTest {

    private static final int DIMENSION = 1;
    private static final int POSITION = -5;
    private static final int MASS = 10;
    private static final int SPEED = 1;
    
//    private final DynamicPickupItem dynItem = new DynamicPickupItem(new Dimension2Dimpl(DIMENSION, DIMENSION),
//            new GameEnvironment(), MASS, new SpeedVector2DImpl(new MutablePosition2Dimpl(POSITION, POSITION), SPEED),
//            Items.HEART, null);
    
    @Test
    public void yAxisMovementTest() {
        /*
        final Environment gameEnv = new GameEnvironment();
        final Dimension2D gameEnvDim = gameEnv.getDimension();
        final Dimension2D itemDim = dynItem.getDimension();
        final MutablePosition2D itemPos = dynItem.getPosition();
        dynItem.yAxisMovement();
        final MutablePosition2D actualPosition = dynItem.getPosition();
        final MutablePosition2D expectedPosition = new MutablePosition2Dimpl(itemPos.getX()
                , -gameEnvDim.getHeight() + itemDim.getHeight()); 
        assertEquals(actualPosition.getCoordinates(), expectedPosition.getCoordinates());
        */
    }
    
    
    @Test 
    public void xAxisMovementTest() {
        /*
        final Environment gameEnv = new GameEnvironment();
        final Dimension2D gameEnvDim = gameEnv.getDimension();
        final MutablePosition2D itemPos = dynItem.getPosition();
        dynItem.xAxisMovement();
        final MutablePosition2D actualPosition = dynItem.getPosition();
        final MutablePosition2D expectedPosition = new MutablePosition2Dimpl(-gameEnvDim.getWidth()
                , itemPos.getY()); 
        assertEquals(actualPosition.getCoordinates(), expectedPosition.getCoordinates());*/
    }
    
}
