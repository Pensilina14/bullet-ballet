package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.effects.EffectFactory;
import it.unibo.pensilina14.bullet.ballet.model.effects.EffectFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effects;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class WeaponFactoryImpl implements WeaponFactory {

	private final EffectFactory effectFactory = new EffectFactoryImpl();
	private static final int DIMENSION = 5;
	private static final int MASS = 5;
	private static final double SPEED = 1.0;
	 private final MutablePosition2Dimpl position = new MutablePosition2Dimpl(0, 0);
	//private final SpeedVector2D speedVector = new SpeedVector2DImpl(position, SPEED);
	
	@Override
	public Weapon createGun(final SpeedVector2D speedVector, final Environment gameEnv) {
		return new WeaponImpl(EntityList.Weapons.GUN, new Dimension2Dimpl(DIMENSION, DIMENSION), gameEnv, MASS, speedVector, Items.WEAPON,
				effectFactory.createDamageEffect(Effects.DAMAGE.getDelta().getValue()));
	}

	@Override
	public Weapon createShotGun(final SpeedVector2D speedVector, final Environment gameEnv) {
		return new WeaponImpl(EntityList.Weapons.SHOTGUN, new Dimension2Dimpl(DIMENSION, DIMENSION), gameEnv, MASS, speedVector, Items.WEAPON,
				effectFactory.createDamageEffect(Effects.DAMAGE.getDelta().getValue()));
	}

	@Override
	public Weapon createAuto(final SpeedVector2D speedVector, final Environment gameEnv) {
		return new WeaponImpl(EntityList.Weapons.AUTO, new Dimension2Dimpl(DIMENSION, DIMENSION), gameEnv, MASS, speedVector, Items.WEAPON,
				effectFactory.createDamageEffect(Effects.DAMAGE.getDelta().getValue()));
	}

}
