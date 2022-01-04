package it.unibo.pensilina14.bullet.ballet.model.entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;

public class GameEntityTest {
	
	private static final double APPROXIMATION = 0.01;
	
	PhysicalObject gameObject = new GameEntity(new SpeedVector2DImpl(new MutablePosition2Dimpl(0, 0), 1)
			, new GameEnvironment(), 3, new Dimension2Dimpl(2, 2));
	
	@Test
    public void testMutablePosition2D() {
        final MutablePosition2D position = gameObject.getPosition().get();
        assertEquals(position.getCoordinates(), MutablePair.of(0, 0));
    }
	
    
//    @Test
//    public void testMoveUP() {
//        boolean isMoving = !gameObject.moveUp();
//        final double yAxisPositionBeforeMovement = gameObject.getPosition().get().getY();
//        assertTrue(isMoving);
//        isMoving = gameObject.moveUp(2);
//        assertTrue(isMoving);
//        assertEquals(yAxisPositionBeforeMovement + (2 * gameObject.getSpeedVector().get().getSpeed())
//                , gameObject.getPosition().get().getY(), APPROXIMATION);
//    }
    
    
//    @Test
//    public void testMoveDOWN() {
//        final double yAxisPositionBeforeMovement = dynamicObstacle.getPosition().getY();
//        boolean isMoving = dynamicObstacle.moveDOWN(10);
//        assertTrue(isMoving);
//        isMoving = !dynamicObstacle.moveDOWN(245);
//        assertTrue(isMoving);
//        assertEquals(yAxisPositionBeforeMovement - (10 * dynamicObstacle.getSpeedVector().getSpeed())
//                , dynamicObstacle.getPosition().getY(), APPROXIMATION);
//        
//    }
//    
//    @Test
//    public void testMoveRIGHT() {
//        boolean isMoving = !dynamicObstacle.moveRIGHT(10);
//        assertTrue(isMoving);
//        dynamicObstacle.getPosition().setPosition(-100, -10);
//        final double xAxisPositionBeforeMovement = dynamicObstacle.getPosition().getX();
//        isMoving = dynamicObstacle.moveRIGHT(10);
//        assertTrue(isMoving);
//        assertEquals(xAxisPositionBeforeMovement + (10 * dynamicObstacle.getSpeedVector().getSpeed())
//                , dynamicObstacle.getPosition().getX(), APPROXIMATION);
//    }
//    
//    @Test
//    public void testMoveLEFT() {
//        final double xAxisPositionBeforeMovement = dynamicObstacle.getPosition().getX();
//        boolean isMoving = dynamicObstacle.moveLEFT(10);
//        assertTrue(isMoving);
//        isMoving = !dynamicObstacle.moveLEFT(243);
//        assertTrue(isMoving);
//        assertEquals(xAxisPositionBeforeMovement - (10 * dynamicObstacle.getSpeedVector().getSpeed())
//                , dynamicObstacle.getPosition().getX(), APPROXIMATION);
//    }
//    
//    @Test
//    public void testUpdateState() {
//        final SpeedVector2D vector = dynamicObstacle.getSpeedVector();
//        final MutablePosition2D prevPosition = vector.getPosition();
//        prevPosition.setPosition(dynamicObstacle.getPosition().getX() + TIME * MS_TO_S, 
//                dynamicObstacle.getPosition().getY() + TIME * MS_TO_S);
//        dynamicObstacle.updateState(TIME);
//        assertEquals(dynamicObstacle.getPosition(), prevPosition); 
//    }
	
	
}
