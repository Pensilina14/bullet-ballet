package it.unibo.pensilina14.bullet.ballet.model.effects;

import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;

public class EffectFactoryImpl implements EffectFactory {

	@Override
	public Effect createHealEffect(final Characters target, final double healthIncreaseFactor) {
		final double actualHealth = target.getHealth();
		return e -> {
			target.setHealth(actualHealth + healthIncreaseFactor);
		};
	}

	@Override
	public Effect createPoisonEffect(final Characters target, final double healthDecreaseFactor) {
		final double actualHealth = target.getHealth();
		return e -> {
			target.setHealth(actualHealth - healthDecreaseFactor); 
		};
	}

}
