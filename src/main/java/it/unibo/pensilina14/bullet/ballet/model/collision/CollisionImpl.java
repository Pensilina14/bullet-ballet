package it.unibo.pensilina14.bullet.ballet.model.collision;

import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

public class CollisionImpl implements Collision {
	
	/*
	 * @Override
	public boolean isCollidingWith(final PhysicalObject firstObject, final PhysicalObject otherObject) {
		return firstObject.getPosition().get().getX() * firstObject.getDimension().get().getWidth() / 2 > otherObject.getPosition().get().getX() &&
				firstObject.getPosition().get().getX() < otherObject.getPosition().get().getX() * otherObject.getDimension().get().getWidth() / 2 &&
        		firstObject.getPosition().get().getY() * firstObject.getDimension().get().getHeight() / 2 > otherObject.getPosition().get().getY() &&
        		firstObject.getPosition().get().getY() < otherObject.getPosition().get().getY() * otherObject.getDimension().get().getWidth() / 2;
	}
	*/
	
	@Override 
	public boolean areColliding(final PhysicalObject firstObject, final PhysicalObject otherObject) {
		return this.checkLeft(firstObject, otherObject) && this.checkUp(firstObject, otherObject);
//		if (this.checkLeft(firstObject, otherObject)) {
//			return this.checkUp(firstObject, otherObject) || this.checkDown(firstObject, otherObject);
//		} else if(this.checkRight(firstObject, otherObject)) {
//			return this.checkUp(firstObject, otherObject) || this.checkDown(firstObject, otherObject);
//		} else {
//			return false;
//		}
	}
	
	private boolean checkLeft(final PhysicalObject firstObject, final PhysicalObject otherObject) {
		return firstObject.getPosition().get().getX() 
				< otherObject.getPosition().get().getX() + otherObject.getDimension().get().getWidth() 
				&& firstObject.getPosition().get().getX() + firstObject.getDimension().get().getWidth() 
				> otherObject.getPosition().get().getX() + otherObject.getDimension().get().getWidth();
	}
	
	private boolean checkRight(final PhysicalObject firstObject, final PhysicalObject otherObject) {
		return firstObject.getPosition().get().getX() + firstObject.getDimension().get().getWidth() 
				> otherObject.getPosition().get().getX()
				&& firstObject.getPosition().get().getX() < otherObject.getPosition().get().getX();
	}
	
	private boolean checkUp(final PhysicalObject firstObject, final PhysicalObject otherObject) {
		return firstObject.getPosition().get().getY() 
				< otherObject.getPosition().get().getY() + otherObject.getDimension().get().getHeight() 
				&& firstObject.getPosition().get().getY() + firstObject.getDimension().get().getHeight() 
				> otherObject.getPosition().get().getY(); //+ otherObject.getDimension().get().getHeight();
	}
	
	private boolean checkDown(final PhysicalObject firstObject, final PhysicalObject otherObject) {
		return firstObject.getPosition().get().getY() + firstObject.getDimension().get().getHeight() 
				>= otherObject.getPosition().get().getY()
				&& firstObject.getPosition().get().getY() <= otherObject.getPosition().get().getY();
	}
}
