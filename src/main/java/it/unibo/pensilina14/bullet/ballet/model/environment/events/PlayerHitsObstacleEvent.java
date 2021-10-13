package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

public class PlayerHitsObstacleEvent implements GameEvent {
	
	private final Characters player;
	private final PhysicalObject obstacle;
	
	public PlayerHitsObstacleEvent(final Characters player, final PhysicalObject obstacle) {
		this.player = player;
		this.obstacle = obstacle;
	}

	/**
	 * @return the player
	 */
	public Characters getPlayer() {
		return player;
	}

	/**
	 * @return the obstacle
	 */
	public PhysicalObject getObstacle() {
		return obstacle;
	}
}
