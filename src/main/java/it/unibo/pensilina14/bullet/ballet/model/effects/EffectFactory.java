package it.unibo.pensilina14.bullet.ballet.model.effects;

import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;

/**
 * This interface is part of the Factory Method pattern
 * used to create certain pre-defined types of {@link Effect}.
 */
public interface EffectFactory {
	/**
	 * Creates an effect capable of healing a given {@link Characters}.
	 * 
	 * @param target is the given {@link Characters} that will be affected by the newly created {@link Effect}.
	 * @param healthIncreaseFactor is the value by which {@link Characters}'s health is increased.
	 * 
	 * @return {@link Effect} 
	 */
	Effect createHealEffect(Characters target, double healthIncreaseFactor);
	/**
	 * Creates an effect capable of damaging a given {@link Characters}.
	 * 
	 * @param target is the given {@link Characters} that will be affected by the newly created {@link Effect}.
	 * @param healthDecreaseFactor is the value by which {@link Characters}'s health is decreased.
	 * 
	 * @return {@link Effect} 
	 */
	Effect createDamageEffect(Characters target, double healthDecreaseFactor);
	/**
	 * Creates an effect capable of poisoning a given {@link Characters}.
	 * Poisoning means that health is decrease by a value multiple times in time.
	 * 
	 * @param target is the given {@link Characters} that will be affected by the newly created {@link Effect}.
	 * @param healthDecreaseFactor is the value by which {@link Characters}'s health is decreased each time.
	 * @return {@link Effect}
	 */
	Effect createPoisonEffect(Characters target, double healthDecreaseFactor);
}
