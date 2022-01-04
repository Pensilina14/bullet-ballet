package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.effects.EffectFactory;
import it.unibo.pensilina14.bullet.ballet.model.effects.EffectFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effects;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class WeaponFactoryImpl implements WeaponFactory {
	
	private final EffectFactory effectFactory = new EffectFactoryImpl();
	private static final int DIMENSION = 35;
	private static final int MASS = 5;
		
	@Override
	public WeaponImpl createGun(final Environment gameEnv, final SpeedVector2D speedVector) {
		return new WeaponImpl(EntityList.Weapons.GUN, new Dimension2Dimpl(DIMENSION, DIMENSION), gameEnv, MASS, speedVector, Items.WEAPON,
				effectFactory.createDamageEffect(Effects.DAMAGE.getDelta().getValue()));
	}

	@Override
	public WeaponImpl createShotGun(final Environment gameEnv, final SpeedVector2D speedVector) {
		return new WeaponImpl(EntityList.Weapons.SHOTGUN, new Dimension2Dimpl(DIMENSION, DIMENSION), gameEnv, MASS, speedVector, Items.WEAPON,
				effectFactory.createDamageEffect(Effects.DAMAGE.getDelta().getValue()));
	}

	@Override
	public WeaponImpl createAuto(final Environment gameEnv, final SpeedVector2D speedVector) {
		return new WeaponImpl(EntityList.Weapons.AUTO, new Dimension2Dimpl(DIMENSION, DIMENSION), gameEnv, MASS, speedVector, Items.WEAPON,
				effectFactory.createDamageEffect(Effects.DAMAGE.getDelta().getValue()));
	}

}
