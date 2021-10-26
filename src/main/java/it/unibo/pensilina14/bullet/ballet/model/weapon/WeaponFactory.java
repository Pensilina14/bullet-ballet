package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface WeaponFactory {

	Weapon createGun(SpeedVector2D speedVector, Environment gameEnv);
	
	Weapon createShotGun(SpeedVector2D speedVector, Environment gameEnv);
	
	Weapon createAuto(SpeedVector2D speedVector, Environment gameEnv);
}
