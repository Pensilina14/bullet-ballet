package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

public class PlayerHitsObstacleEvent implements GameEvent {
	
	private final Player player;
	private final PhysicalObject obstacle;
	
	public PlayerHitsObstacleEvent(final Player player, final PhysicalObject obstacle) {
		this.player = player;
		this.obstacle = obstacle;
		// this.player.decreaseHealth(obstacle.getConflictDamage);
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @return the obstacle
	 */
	public PhysicalObject getObstacle() {
		return obstacle;
	}
}
