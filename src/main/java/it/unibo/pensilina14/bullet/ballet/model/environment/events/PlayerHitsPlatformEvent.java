package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.collision.CollisionSides;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;

public final class PlayerHitsPlatformEvent implements GameEvent {
    private final Player player;
	private final Platform platform;
	private final CollisionSides platformSideOfCollision;
	
	public PlayerHitsPlatformEvent(final Player player, final Platform platform, final CollisionSides side) {
		this.player = player;
		this.platform = platform;
		this.platformSideOfCollision = side;
	}

	/**
	 * @return the player.
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * @return the obstacle.
	 */
	public Platform getPlatform() {
		return this.platform;
	}
	
	/**
	 * 
	 * @return the side.
	 */
	public CollisionSides getCollisionSide() {
		return this.platformSideOfCollision;
	}
}
