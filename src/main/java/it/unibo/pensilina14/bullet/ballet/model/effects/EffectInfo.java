package it.unibo.pensilina14.bullet.ballet.model.effects;

import it.unibo.pensilina14.bullet.ballet.model.effects.EffectsUtils.Deltas;

/**
 * Interface capable of describing the contract each effect will adhere to. Permits the
 * retrieval/setting(if possible) of all the {@link Effects}.
 */
public interface EffectInfo {
  /**
   * @return the name of the {@link Effect}
   */
  String getName();
  /**
   * @return the delta, the factor by which health is increased/decreased.
   */
  Deltas getDelta();
  /**
   * Sets the delta of the {@link Effect} to a given value. This allow the elements of this enum to
   * be more flexible, in order to not create lots of elements and playing with the given ones. We
   * could say that delta is subjective.
   *
   * @param delta
   */
  void setDelta(Deltas delta);
}
