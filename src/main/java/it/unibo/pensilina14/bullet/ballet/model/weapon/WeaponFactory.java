package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface WeaponFactory {

	PhysicalObject createGun(Environment gameEnv);
	
	PhysicalObject createShotGun(Environment gameEnv);
	
	PhysicalObject createAuto(Environment gameEnv);
}
