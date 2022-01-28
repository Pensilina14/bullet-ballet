package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;

public class BulletHitsPlatformEvent implements GameEvent{

	private final Bullet bullet;
	private final Platform platform;
	
	public BulletHitsPlatformEvent(final Bullet bullet, final Platform platform) {
		this.bullet = bullet;
		this.platform = platform;
	}
	
	public Bullet getBullet() {
		return this.bullet;
	}
	
	public Platform getPlatform() {
		return this.platform;
	}
	
}
