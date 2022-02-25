package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleImpl;

public final class PlayerHitsObstacleEvent implements GameEvent {
	
	private final Player player;
	private final ObstacleImpl obstacle;
	
	public PlayerHitsObstacleEvent(final Player player, final ObstacleImpl obstacle) {
		this.player = player;
		this.obstacle = obstacle;
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
	public ObstacleImpl getObstacle() {
		return obstacle;
	}
}
