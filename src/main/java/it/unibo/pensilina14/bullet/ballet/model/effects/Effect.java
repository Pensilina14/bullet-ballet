package it.unibo.pensilina14.bullet.ballet.model.effects;

import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;

/**
 * Defines a contract to which all of the classes that do implement {@link Item} interface must
 * adhere, in order to give that Item an effect. The effect is intended to be "effective" against a
 * specific {@link Characters}.
 */
@FunctionalInterface
public interface Effect {
  /**
   * Applies the effect to the target {@link Characters}.
   *
   * @param target is the {@link Characters} on which the effect will be applied.
   */
  void applyEffect(Characters target);
}
