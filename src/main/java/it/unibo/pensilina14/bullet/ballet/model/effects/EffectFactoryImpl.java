package it.unibo.pensilina14.bullet.ballet.model.effects;

import org.apache.commons.lang3.time.StopWatch;

/**
 * Implementation of an {@link Effect} factory.
 * 
 * {@inheritDoc}
 */
public final class EffectFactoryImpl implements EffectFactory {

	@Override
	public Effect createHealEffect(final double healthIncreaseFactor) {
		return e -> {
			final double actualHealth = e.getHealth();
			e.setHealth(actualHealth + healthIncreaseFactor);
		};
	}

	@Override
	public Effect createDamageEffect(final double healthDecreaseFactor) {
		return e -> {
			final double actualHealth = e.getHealth();
			e.setHealth(actualHealth - healthDecreaseFactor);
		};
	}

	@Override
	public Effect createPoisonEffect(final double healthDecreaseFactor, final long msStep, final long msDuration) {
		return e -> {
			final StopWatch timer = StopWatch.createStarted();
			long elapsedTime = 0;
			while (elapsedTime < msDuration) {
				timer.split();
				elapsedTime = elapsedTime + timer.getSplitTime();
				System.out.println("elapsed poisoning time: " + elapsedTime);
				if (elapsedTime % msStep == 0) {
					final double actualHealth = e.getHealth();
					e.setHealth(actualHealth - healthDecreaseFactor);
				}
			}
			timer.stop();
		};
	}
}
