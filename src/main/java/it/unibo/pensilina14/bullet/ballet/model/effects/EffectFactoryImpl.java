package it.unibo.pensilina14.bullet.ballet.model.effects;

/**
 * Implementation of an {@link Effect} factory.
 *
 * <p>{@inheritDoc}
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
  public Effect createPoisonEffect(
      final double healthDecreaseFactor, final long msStep, final long msDuration) {
    return e -> {
      new Thread() {
        @Override
        public void run() {
          long steppedTime = 0;
          while (steppedTime < msDuration) {
            e.setHealth(e.getHealth() - healthDecreaseFactor);
            try {
              Thread.sleep(msStep);
            } catch (final InterruptedException exc) {
              exc.printStackTrace();
            }
            steppedTime += msStep;
          }
        }
      }.start();
    };
  }

  @Override
  public Effect createRechargeEffect() {
    return e -> {
      if (e.getWeapon().isPresent()) {
        e.getWeapon().get().recharge();
      }
    };
  }
}
