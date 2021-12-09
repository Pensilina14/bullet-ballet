package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.Obstacle;

public class EnemyHitsObstacleEvent implements GameEvent {
	
	private final Characters enemy;
	private final Obstacle obstacle;
	
	public EnemyHitsObstacleEvent(final Characters enemy, final Obstacle obstacle) {
		super();
		this.enemy = enemy;
		this.obstacle = obstacle;
	}

	/**
	 * @return the enemy
	 */
	public Characters getEnemy() {
		return enemy;
	}

	/**
	 * @return the obstacle
	 */
	public Obstacle getObstacle() {
		return obstacle;
	}
	
	
}
