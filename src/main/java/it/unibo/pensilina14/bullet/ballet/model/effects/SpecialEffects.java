package it.unibo.pensilina14.bullet.ballet.model.effects;

import it.unibo.pensilina14.bullet.ballet.model.effects.EffectsUtils.Deltas;
import it.unibo.pensilina14.bullet.ballet.model.effects.EffectsUtils.Durations;
import it.unibo.pensilina14.bullet.ballet.model.effects.EffectsUtils.Steps;

public enum SpecialEffects implements SpecialEffectInfo {
	/**
	 * This {@link Effect} features a periodical decrease in health of a certain {@link Characters}.
	 */
	POISON("poison", Deltas.LIGHT, Steps.SHORT, Durations.SHORT);
	
	/**
	 * Name identifies the {@link Effect}.
	 */
	private final String name;
	/**
	 * Not final to give flexibility to each Effect.
	 */
	private Deltas delta;
	/**
	 * SpecialEffects element specific field 
	 * that represents the step in milliseconds
	 * between each effect hit.
	 */
	private Steps msStep;
	/**
	 * SpecialEffects element specific field
	 * that describes the duration of the whole
	 * {@link Effect}.
	 */
	private Durations msDuration;

	SpecialEffects(final String name, final Deltas delta, final Steps step, final Durations duration) {
		this.name = name;
		this.delta = delta;
		this.msStep = step;
		this.msDuration = duration;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Deltas getDelta() {
		return this.delta;
	}

	@Override
	public void setDelta(final Deltas delta) {
		this.delta = delta;
	}

	@Override
	public Steps getMsStep() {
		return this.msStep;
	}

	@Override
	public Durations getMsDuration() {
		return this.msDuration;
	}
}
