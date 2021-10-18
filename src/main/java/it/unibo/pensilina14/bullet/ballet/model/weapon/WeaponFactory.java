package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface WeaponFactory {

	Weapon createGun(Environment gameEnv);
	
	Weapon createShotGun(Environment gameEnv);
	
	Weapon createAuto(Environment gameEnv);
}
