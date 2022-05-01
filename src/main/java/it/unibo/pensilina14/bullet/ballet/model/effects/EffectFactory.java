package it.unibo.pensilina14.bullet.ballet.model.effects;

/**
 * This interface is part of the Factory Method pattern used to create certain pre-defined types of
 * {@link Effect}.
 */
public interface EffectFactory {
  /**
   * Creates an effect capable of healing a given {@link Characters}.
   *
   * @param healthIncreaseFactor is the value by which {@link Characters}'s health is increased.
   * @return {@link Effect}
   */
  Effect createHealEffect(double healthIncreaseFactor);
  /**
   * Creates an effect capable of damaging a given {@link Characters}.
   *
   * @param healthDecreaseFactor is the value by which {@link Characters}'s health is decreased.
   * @return {@link Effect}
   */
  Effect createDamageEffect(double healthDecreaseFactor);
  /**
   * Creates an effect capable of poisoning a given {@link Characters}. Poisoning means that health
   * is decrease by a value multiple times in time.
   *
   * @param healthDecreaseFactor is the value by which {@link Characters}'s health is decreased each
   *     time.
   * @param msStep represents the step in time in between every damaging hit.
   * @param msDuration represents the duration of the effect.
   *     <p>Note that msStep and msDuration are in milliseconds.
   * @return {@link Effect}
   */
  Effect createPoisonEffect(double healthDecreaseFactor, long msStep, long msDuration);
  /**
   * Creates an effect capable of reloading the player's weapon.
   *
   * @return {@link Effect}
   */
  Effect createRechargeEffect();
}
