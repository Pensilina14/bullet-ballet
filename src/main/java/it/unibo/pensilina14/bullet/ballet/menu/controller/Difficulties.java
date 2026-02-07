package it.unibo.pensilina14.bullet.ballet.menu.controller;

public enum Difficulties {
  EASY("easy"),
  MEDIUM("medium"),
  HARD("hard");

  private final String difficulty;

  Difficulties(final String difficulty) {
    this.difficulty = difficulty;
  }

  /*
   * @see java.lang.Enum#toString()
   */
  @Override
  public String toString() {
    return this.difficulty;
  }

  /**
   * Parses a difficulty string (as stored in settings) into an enum value.
   *
   * @param difficulty the string difficulty (e.g. "easy")
   * @return a {@link Difficulties} value, or the default if not recognized
   */
  public static Difficulties fromString(final String difficulty) {
    for (final var d : Difficulties.values()) {
      if (d.toString().equalsIgnoreCase(difficulty)) {
        return d;
      }
    }
    return getDefaultDifficulty();
  }

  public static Difficulties getDefaultDifficulty() {
    return Difficulties.MEDIUM;
  }
}
