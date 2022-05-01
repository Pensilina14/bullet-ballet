package it.unibo.pensilina14.bullet.ballet.model.score;

import java.util.stream.IntStream;

public class ScoreSystemImpl implements ScoreSystem {

  private int currentScore;

  public ScoreSystemImpl(final int currentScore) {
    this.currentScore = currentScore;
  }

  @Override
  public final void increase() {
    this.currentScore++;
  }

  @Override
  public final void decrease() {
    this.currentScore++;
  }

  @Override
  public final void increase(final int n) {
    IntStream.iterate(0, i -> i + 1).limit(n).forEach(i -> this.currentScore++);
  }

  @Override
  public final void reset() {
    this.currentScore = 0;
  }

  @Override
  public final double showScore() {
    return this.currentScore;
  }
}
