package it.unibo.pensilina14.bullet.ballet.model.effects;

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
			long elapsedTime = 0;
			long lastTime = System.currentTimeMillis();
			while (elapsedTime < msDuration) {
				final long current = System.currentTimeMillis();
				elapsedTime = elapsedTime + (current - lastTime);
				System.out.println("elapsed poisoning time: " + elapsedTime);
				if (elapsedTime % msStep == 0) {
					final double actualHealth = e.getHealth();
					e.setHealth(actualHealth - healthDecreaseFactor);
					try {
						Thread.sleep(1);
					} catch (final InterruptedException exc) {
						exc.printStackTrace();
					}
				}
				lastTime = current;
			}
		};
	}
}
