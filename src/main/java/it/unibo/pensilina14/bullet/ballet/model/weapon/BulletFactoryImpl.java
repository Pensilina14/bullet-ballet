package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.effects.EffectFactory;
import it.unibo.pensilina14.bullet.ballet.model.effects.EffectFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effects;
import it.unibo.pensilina14.bullet.ballet.model.effects.SpecialEffects;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class BulletFactoryImpl implements BulletFactory {

	private static final double MASS = 2.5;
    private static final int BULLET_DIM = 20;
    private final EffectFactory effectFact = new EffectFactoryImpl();
    /*
	@Override
	public Bullet createClassicBullet(final Environment gameEnvironment, final SpeedVector2D vector) {
		return new BulletImpl(EntityList.BulletType.CLASSICAL, new Dimension2Dimpl(BULLET_DIM, BULLET_DIM)
				, gameEnvironment, MASS, vector, Items.DAMAGE
				, effectFact.createHealEffect(Effects.DAMAGE.getDelta().getValue()));
	}

	@Override
	public Bullet createPoisonBullet(final Environment gameEnvironment, final SpeedVector2D vector) {
		return new BulletImpl(EntityList.BulletType.TOXIC, new Dimension2Dimpl(BULLET_DIM, BULLET_DIM)
				, gameEnvironment, MASS, vector, Items.POISON
				, effectFact.createPoisonEffect(SpecialEffects.POISON.getDelta().getValue(),
                SpecialEffects.POISON.getMsStep().getValue(),
                SpecialEffects.POISON.getMsDuration().getValue()));
	}
	*/
	@Override
	public Bullet createClassicBullet(final Environment gameEnvironment, final SpeedVector2D vector) {
		return new BulletImpl(vector, gameEnvironment, MASS, new Dimension2Dimpl(BULLET_DIM, BULLET_DIM)
				, Items.DAMAGE,  effectFact.createHealEffect(Effects.DAMAGE.getDelta().getValue())
				, EntityList.BulletType.CLASSICAL);
	}
	@Override
	public Bullet createPoisonBullet(final Environment gameEnvironment, final SpeedVector2D vector) {
		return new BulletImpl(vector, gameEnvironment, MASS, new Dimension2Dimpl(BULLET_DIM, BULLET_DIM)
				, Items.DAMAGE,  effectFact.createPoisonEffect(SpecialEffects.POISON.getDelta().getValue(),
					SpecialEffects.POISON.getMsStep().getValue(),
					SpecialEffects.POISON.getMsDuration().getValue())
				, EntityList.BulletType.CLASSICAL);
	}

}
