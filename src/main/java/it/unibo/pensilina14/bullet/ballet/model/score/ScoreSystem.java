package it.unibo.pensilina14.bullet.ballet.model.score;

public interface ScoreSystem {

  enum ScoreBonus {
    KILL_ENEMY(100),
    DESTROY_OBSTACLE(150),
    COLLECT_COIN(1500);

    private final int bonus;

    ScoreBonus(final int bonus) {
      this.bonus = bonus;
    }

    public final int getBonus() {
      return this.bonus;
    }
  }
  /** Increases the score counter. */
  void increase();
  /**
   * Increments the score n times.
   *
   * @param n
   */
  void increase(int n);
  /** Decreases the score counter. */
  void decrease();
  /** Resets the score counter. */
  void reset();
  /**
   * @return current score
   */
  double showScore();
}
