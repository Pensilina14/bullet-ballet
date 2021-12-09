package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.Obstacle;

public class PlayerHitsObstacleEvent implements GameEvent {
	
	private final Player player;
	private final Obstacle obstacle;
	
	public PlayerHitsObstacleEvent(final Player player, final Obstacle obstacle) {
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
	public Obstacle getObstacle() {
		return obstacle;
	}
}
