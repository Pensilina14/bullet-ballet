package it.unibo.pensilina14.bullet.ballet.model.effects;

import java.util.Timer;
import java.util.TimerTask;

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

//	@Override
//	public Effect createPoisonEffect(final double healthDecreaseFactor, final long msStep, final long msDuration) {
//		return e -> {
//			new Thread() {
//				@Override
//				public void run() {
//					long steppedTime = 0;
//					while (steppedTime < msDuration) {
//						e.setHealth(e.getHealth() - healthDecreaseFactor);
//						try {
//							Thread.sleep(msStep);
//						} catch (final InterruptedException exc) {
//							exc.printStackTrace();
//						}
//						steppedTime += msStep;
//					}
//				}
//			}.start();
//		};
//	}
//	
	//TODO: Create PoisonAgent in order to launch a thread well designed
}
