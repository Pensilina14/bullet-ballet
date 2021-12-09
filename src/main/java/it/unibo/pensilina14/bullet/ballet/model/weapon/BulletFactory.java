package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface BulletFactory {

	/*
	 * @return a normal bullet with no effects.
	 */
	Bullet createClassicBullet(Environment gameEnvironment, SpeedVector2D vector);
	
	/*
	 * @return a bullet with poison effect.
	 */
	Bullet createPoisonBullet(Environment gameEnvironment, SpeedVector2D vector);
}
