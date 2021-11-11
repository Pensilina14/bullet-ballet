package it.unibo.pensiliina14.bullet.ballet.model.obstacle.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.DynamicObstacle;

public class DynamicObstacleTest {

    private final static double APPROXIMATION = 0.001;
    private final static double SPEED = 2.0;
    private final static int SIZE = 1;
    private final static double MASS = 5;
    private final static double POSITION = -5;
    private final static double TIME = 10;
    private final static double MS_TO_S = 0.001;
    private final static double ENV_DIM = 500.0;
    
    private final DynamicObstacle dynamicObstacle = new DynamicObstacle(new Dimension2Dimpl(SIZE, SIZE),
            new GameEnvironment(ENV_DIM, ENV_DIM), MASS, new SpeedVector2DImpl(new MutablePosition2Dimpl(POSITION, POSITION), SPEED)); 
    
    @Test
    public void testMutablePosition2D() {
        final MutablePosition2D position = dynamicObstacle.getPosition().get();
        assertEquals(position.getCoordinates(), MutablePair.of(-5.0, -5.0));
    }
    
    @Test
    public void testGravityForce() {
        final double gravity = dynamicObstacle.getGameEnvironment().get().getGravity();
        final double mass = dynamicObstacle.getMass();
        assertEquals(gravity * mass, dynamicObstacle.getGravityForce(), APPROXIMATION);
    }
    
//    @Test
//    public void testMove() {
//        boolean isMoving = dynamicObstacle.move(-5, -5);
//        assertTrue(isMoving);
//        isMoving = !dynamicObstacle.move(-506, -506);
//        assertTrue(isMoving);
//    }
    
//    @Test
//    public void testMoveUP() {
//        boolean isMoving = !dynamicObstacle.moveUP(10);
//        final double yAxisPositionBeforeMovement = dynamicObstacle.getPosition().getY();
//        assertTrue(isMoving);
//        isMoving = dynamicObstacle.moveUP(2);
//        assertTrue(isMoving);
//        assertEquals(yAxisPositionBeforeMovement + (2 * dynamicObstacle.getSpeedVector().getSpeed())
//                , dynamicObstacle.getPosition().getY(), APPROXIMATION);
//    }
//    
//    
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
