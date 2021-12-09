package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface WeaponFactory {

	PhysicalObject createGun(Environment gameEnv, SpeedVector2D speedVector);
	
	PhysicalObject createShotGun(Environment gameEnv, SpeedVector2D speedVector);
	
	PhysicalObject createAuto(Environment gameEnv, SpeedVector2D speedVector);
}
