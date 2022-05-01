package it.unibo.pensilina14.bullet.ballet.model.effects;

import it.unibo.pensilina14.bullet.ballet.model.effects.EffectsUtils.Deltas;

/** Describes {@link Effect} and relative delta relationship. */
public enum Effects implements EffectInfo {
  /** This {@link Effect} features an increase in health of a certain {@link Characters}. */
  HEALTHY("healthy", Deltas.LIGHT),
  /** This {@link Effect} features a decrease in health of a certain {@link Characters}. */
  DAMAGE("damage", Deltas.LIGHT);

  private final String name;
  /*
   * Not final to give flexibility to each Effect.
   */
  private Deltas delta;

  /**
   * Simple constructor capable of instancing an element of this enum.
   *
   * @param name
   * @param delta
   */
  Effects(final String name, final Deltas delta) {
    this.name = name;
    this.delta = delta;
  }

  /** {@inheritDoc} */
  public String getName() {
    return name;
  }

  /** {@inheritDoc} */
  public Deltas getDelta() {
    return delta;
  }

  /** {@inheritDoc} */
  public void setDelta(final Deltas delta) {
    this.delta = delta;
  }
}
