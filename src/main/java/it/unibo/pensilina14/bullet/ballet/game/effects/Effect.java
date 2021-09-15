package it.unibo.pensilina14.bullet.ballet.game.effects;

import it.unibo.pensilina14.bullet.ballet.characters.Characters;

/**
 * Defines a contract to which all of the
 * classes that do implement {@link Item} interface
 * must adhere, in order to give that Item an effect.
 * The effect is intended to be "effective" against
 * a specific {@link Character}.
 */
@FunctionalInterface
public interface Effect {
	/**
	 * Applies the effect to the target {@link Character}.
	 * 
	 * @param target is the {@link Characters} on which the effect will be applied.
	 */
    void applyEffect(Characters target);
}
