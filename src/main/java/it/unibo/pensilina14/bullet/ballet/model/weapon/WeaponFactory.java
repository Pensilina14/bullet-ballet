package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface WeaponFactory {

	Weapon createGun(Environment gameEnv, SpeedVector2D speed);
	
	Weapon createShotGun(Environment gameEnv, SpeedVector2D speed);
	
	Weapon createAuto(Environment gameEnv, SpeedVector2D speed);
}
