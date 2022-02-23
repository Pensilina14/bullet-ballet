package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface WeaponFactory {

	Weapon createGun(Environment gameEnv, SpeedVector2D speedVector);
	
	Weapon createShotGun(Environment gameEnv, SpeedVector2D speedVector);
	
	Weapon createAuto(Environment gameEnv, SpeedVector2D speedVector);
}
