package it.unibo.pensilina14.bullet.ballet.model.collision;

import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

public final class Collision {
	
	private Collision() { }
	
	/**
	 * 
	 * @param whoHits
	 * @param whoGetsHit
	 * @return the side where the collision thing happened wrapped in an {@link Optional}.
	 * If the two objects do not collide an {@link Optional#empty()} is returned.
	 */
	public static Optional<CollisionSides> getCollisionSide(final PhysicalObject whoHits, final PhysicalObject whoGetsHit) {
		if (whoHits.getPosition().get().getY() <= whoGetsHit.getPosition().get().getY() - (whoGetsHit.getDimension().get().getHeight() / 2)) {
            return Optional.of(CollisionSides.SOUTH);
		} else if (whoHits.getPosition().get().getY() >= whoGetsHit.getPosition().get().getY() + (whoGetsHit.getDimension().get().getHeight() / 2)) {
            return Optional.of(CollisionSides.NORTH);
		} else if (whoHits.getPosition().get().getX() < whoGetsHit.getPosition().get().getX()) {
            return Optional.of(CollisionSides.WEST);
		} else if (whoHits.getPosition().get().getX() > whoGetsHit.getPosition().get().getX()) {
            return Optional.of(CollisionSides.EAST);
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * @param firstObject 
	 * @param otherObject
	 *  @return true if the first object collides with the second.
	 */ 
	public static boolean areColliding(final PhysicalObject firstObject, final PhysicalObject otherObject) {
		return Collision.checkAxisX(firstObject, otherObject) && Collision.checkAxisY(firstObject, otherObject);
	}
	
	private static boolean checkAxisX(final PhysicalObject firstObject, final PhysicalObject otherObject) {
		return firstObject.getPosition().get().getX() 
				< otherObject.getPosition().get().getX() + otherObject.getDimension().get().getWidth() 
				&& firstObject.getPosition().get().getX() + firstObject.getDimension().get().getWidth() 
				> otherObject.getPosition().get().getX();
	}
	
	private static boolean checkAxisY(final PhysicalObject firstObject, final PhysicalObject otherObject) {
		return firstObject.getPosition().get().getY() 
				< otherObject.getPosition().get().getY() + otherObject.getDimension().get().getHeight() 
				&& firstObject.getPosition().get().getY() + firstObject.getDimension().get().getHeight() 
				> otherObject.getPosition().get().getY(); 
	}
}
