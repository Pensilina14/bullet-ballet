package it.unibo.pensilina14.bullet.ballet.model.collision;

import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

public class CollisionImpl implements Collision {
	
	@Override
	public boolean isCollidingWith(final PhysicalObject firstObject, final PhysicalObject otherObject) {
		return firstObject.getPosition().get().getX() * firstObject.getDimension().get().getWidth() / 2 > otherObject.getPosition().get().getX() &&
				firstObject.getPosition().get().getX() < otherObject.getPosition().get().getX() * otherObject.getDimension().get().getWidth() / 2 &&
        		firstObject.getPosition().get().getY() * firstObject.getDimension().get().getHeight() / 2 > otherObject.getPosition().get().getY() &&
        		firstObject.getPosition().get().getY() < otherObject.getPosition().get().getY() * otherObject.getDimension().get().getWidth() / 2;
	}
}
