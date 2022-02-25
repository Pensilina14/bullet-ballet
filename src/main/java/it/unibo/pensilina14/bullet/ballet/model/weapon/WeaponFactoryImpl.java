package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class WeaponFactoryImpl implements WeaponFactory {
	
	private static final int DIMENSION = 50;
	private static final int MASS = 5;
		
	@Override
	public Weapon createGun(final Environment gameEnv, final SpeedVector2D speedVector) {
		return new WeaponImpl(EntityList.Weapons.GUN, new Dimension2Dimpl(DIMENSION, DIMENSION), gameEnv, MASS, speedVector);
	}

	@Override
	public Weapon createShotGun(final Environment gameEnv, final SpeedVector2D speedVector) {
		return new WeaponImpl(EntityList.Weapons.SHOTGUN, new Dimension2Dimpl(DIMENSION, DIMENSION), gameEnv, MASS, speedVector);
	}

	@Override
	public Weapon createAuto(final Environment gameEnv, final SpeedVector2D speedVector) {
		return new WeaponImpl(EntityList.Weapons.AUTO, new Dimension2Dimpl(DIMENSION, DIMENSION), gameEnv, MASS, speedVector);
	}

}
