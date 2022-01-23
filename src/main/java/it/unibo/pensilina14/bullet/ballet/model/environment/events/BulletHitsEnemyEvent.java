package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;
import it.unibo.pensilina14.bullet.ballet.model.weapon.BulletImpl;

public class BulletHitsEnemyEvent implements GameEvent {
	
	private final Bullet bullet;
	private final Enemy enemy;
	
	public BulletHitsEnemyEvent(final Bullet bullet, final Enemy enemy) {
		this.bullet = (BulletImpl) bullet;
		this.enemy = enemy;
	}
	
	public Bullet getBullet() {
		return this.bullet;
	}
	
	public Enemy getEnemy() {
		return this.enemy;
	}
}