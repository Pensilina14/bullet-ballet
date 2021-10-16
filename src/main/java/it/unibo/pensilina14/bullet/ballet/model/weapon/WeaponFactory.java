package it.unibo.pensilina14.bullet.ballet.model.weapon;

public interface WeaponFactory {

	Weapon createGun();
	
	Weapon createShotGun();
	
	Weapon createAuto();
}
