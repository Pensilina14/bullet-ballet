package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

public enum HudLabels {
  HEALTH("Health"),
  SCORE("Score"),
  AMMO("Ammo"),
  GAME_OVER("Game Over");

  private final String label;

  HudLabels(final String label) {
    this.label = label;
  }

  public String toString() {
    return this.label;
  }
}
