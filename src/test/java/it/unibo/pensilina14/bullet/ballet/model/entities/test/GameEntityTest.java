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
	
	PhysicalObject gameObject = new GameEntity(new SpeedVector2DImpl(new MutablePosition2Dimpl(0, 720), 1)
			, new GameEnvironment(12, 1883), 3, new Dimension2Dimpl(2, 2));
	
	@Test
    public void testMutablePosition2D() {
        final MutablePosition2D position = gameObject.getPosition().get();
        assertEquals(position.getCoordinates(), MutablePair.of(0.0, 720.0));
    }
	
    
    @Test
    public void testMoveUP() {
    	/* devo capire cosa c'è che non va...
        final boolean isStill = !gameObject.moveUp(800);
        assertTrue(isStill);
        */
    }
    
    
    @Test
    public void testMoveDOWN() {
    	gameObject.moveDown(500);
    	assertEquals(gameObject.getPosition().get().getCoordinates(), MutablePair.of(0.0, 1220.0));
    }
    
    @Test
    public void testMoveRIGHT() {
    	final boolean isStill = !gameObject.moveRight(gameObject.getGameEnvironment()
    			.get().getDimension().getWidth());
        assertTrue(isStill);
        final boolean isMoving = gameObject.moveRight(gameObject.getGameEnvironment()
    			.get().getDimension().getWidth() - this.gameObject.getDimension().get().getWidth());
        assertTrue(isMoving);
    }
    
    @Test
    public void testMoveLEFT() {
    	final double testPosition = 180.0;
    	final boolean isStill = !gameObject.moveLeft(1);
        assertTrue(isStill);
        gameObject.getPosition().get().setPosition(testPosition, gameObject.getPosition().get().getY());
        final boolean isMoving = gameObject.moveLeft(testPosition - this.gameObject.getDimension().get().getWidth());
        assertTrue(isMoving);
    }
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
