package it.unibo.pensilina14.bullet.ballet.model.effects;

import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;

/**
 * Implementation of an {@link Effect} factory.
 * 
 * {@inheritDoc}
 */
public class EffectFactoryImpl implements EffectFactory {

	@Override
	public Effect createHealEffect(final Characters target, final double healthIncreaseFactor) {
		final double actualHealth = target.getHealth();
		return e -> {
			target.setHealth(actualHealth + healthIncreaseFactor);
		};
	}

	@Override
	public Effect createDamageEffect(final Characters target, final double healthDecreaseFactor) {
		final double actualHealth = target.getHealth();
		return e -> {
			target.setHealth(actualHealth - healthDecreaseFactor); 
		};
	}

	@Override
	public Effect createPoisonEffect(Characters target, double healthDecreaseFactor) {
		final double actualHealth = target.getHealth();
		return e -> {
			//TODO
		};
	}

}
