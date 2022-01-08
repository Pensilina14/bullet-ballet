package it.unibo.pensilina14.bullet.ballet.model.collision;

import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

public class CollisionImpl implements Collision {
		
	@Override 
	public boolean areColliding(final PhysicalObject firstObject, final PhysicalObject otherObject) {
		return this.checkAxisX(firstObject, otherObject) && this.checkAxisY(firstObject, otherObject);
	}
	
	private boolean checkAxisX(final PhysicalObject firstObject, final PhysicalObject otherObject) {
		return firstObject.getPosition().get().getX() 
				< otherObject.getPosition().get().getX() + otherObject.getDimension().get().getWidth() 
				&& firstObject.getPosition().get().getX() + firstObject.getDimension().get().getWidth() 
				> otherObject.getPosition().get().getX();
	}
	
	private boolean checkAxisY(final PhysicalObject firstObject, final PhysicalObject otherObject) {
		return firstObject.getPosition().get().getY() 
				< otherObject.getPosition().get().getY() + otherObject.getDimension().get().getHeight() 
				&& firstObject.getPosition().get().getY() + firstObject.getDimension().get().getHeight() 
				> otherObject.getPosition().get().getY(); 
	}
}
