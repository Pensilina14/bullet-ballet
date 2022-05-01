package it.unibo.pensilina14.bullet.ballet.model.effects;

public class EffectsUtils {
  /**
   * Inner enum that provides all the possible deltas. A delta is a factor that determines and
   * defines health increase or decrease. Note that deltas are constants, the effects in the main
   * enum will be flexible to this point.
   */
  public enum Deltas {
    /** Light delta. */
    LIGHT(10),
    /** Moderate delta. */
    MODERATE(20),
    /** Heavy delta. */
    HEAVY(40);

    private final double value;

    Deltas(final double value) {
      this.value = value;
    }

    public final double getValue() {
      return this.value;
    }
  }

  /** Provides all the different types of steps. Each one has a different duration. */
  public enum Steps {
    /** Short step. */
    SHORT(1000),
    /** Medium step. */
    MEDIUM(3000),
    /** Long step. */
    LONG(5000);

    private final long value;

    Steps(final long value) {
      this.value = value;
    }

    public final long getValue() {
      return this.value;
    }
  }

  /**
   * Provides all the different durations possible. Each one has a value describing the duration.
   */
  public enum Durations {
    /** Short duration. */
    SHORT(5000),
    /** Medium duration. */
    MEDIUM(15_000),
    /** Long duration. */
    LONG(25_000);

    private final long value;

    Durations(final long value) {
      this.value = value;
    }

    public final long getValue() {
      return this.value;
    }
  }
}
