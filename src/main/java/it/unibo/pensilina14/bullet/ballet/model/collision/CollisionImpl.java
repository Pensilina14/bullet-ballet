package it.unibo.pensilina14.bullet.ballet.model.collision;

import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

public class CollisionImpl implements Collision {
	
	@Override
	public boolean isCollidingWith(final PhysicalObject firstObject, final PhysicalObject otherObject) {
		return firstObject.getPosition().getX() * firstObject.getDimension().getWidth() / 2 > otherObject.getPosition().getX() &&
        		firstObject.getPosition().getX() < otherObject.getPosition().getX() * otherObject.getDimension().getWidth() / 2 &&
        		firstObject.getPosition().getY() * firstObject.getDimension().getHeight() / 2 > otherObject.getPosition().getY() &&
        		firstObject.getPosition().getY() < otherObject.getPosition().getY() * otherObject.getDimension().getWidth() / 2;
	}
}
