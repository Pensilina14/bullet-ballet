package it.unibo.pensilina14.bullet.ballet.model.effects;

import it.unibo.pensilina14.bullet.ballet.model.effects.EffectsUtils.Durations;
import it.unibo.pensilina14.bullet.ballet.model.effects.EffectsUtils.Steps;

/**
 * A Special Effect differentiates by a simple Effect thanks to time, in fact they have periodical
 * consequences. Their effect is perpetuated in time so it needs two elements: the step in
 * milliseconds between each perpetuation and the duration in milliseconds of the whole effect.
 */
public interface SpecialEffectInfo extends EffectInfo {
  /**
   * msStep getter.
   *
   * @return an element of {@link Steps} representing the step in milliseconds.
   */
  Steps getMsStep();
  /**
   * msDuration getter.
   *
   * @return an element of {@link Durations} representing the duration in milliseconds.
   */
  Durations getMsDuration();
}
