package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

public class EnemyHitsObstacleEvent implements GameEvent {
	
	private final Characters enemy;
	private final PhysicalObject obstacle;
	
	public EnemyHitsObstacleEvent(final Characters enemy, final PhysicalObject obstacle) {
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
	public PhysicalObject getObstacle() {
		return obstacle;
	}
	
	
}
