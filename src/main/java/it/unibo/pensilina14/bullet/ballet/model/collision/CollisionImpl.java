package it.unibo.pensilina14.bullet.ballet.model.collision;

import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

public class CollisionImpl implements Collision {

	private final PhysicalObject object;
	private final Optional<PhysicalObject> otherObject;
	
	public CollisionImpl(final PhysicalObject object) {
		this.object = object;
		this.otherObject = Optional.empty();
	}
	
	
	@Override
	public boolean isCollidingWith(final PhysicalObject otherObject) {
		return this.object.getPosition().getX() * this.object.getDimension().getWidth() / 2 > otherObject.getPosition().getX() &&
        		this.object.getPosition().getX() < otherObject.getPosition().getX() * otherObject.getDimension().getWidth() / 2 &&
        		this.object.getPosition().getY() * this.object.getDimension().getHeight() / 2 > otherObject.getPosition().getY() &&
        		this.object.getPosition().getY() < otherObject.getPosition().getY() * otherObject.getDimension().getWidth() / 2;
	}
	
	public PhysicalObject getObject() {
		return this.object;
	}
	
	public Optional<PhysicalObject> getExternalObject(){
		return this.otherObject;
	}

}
