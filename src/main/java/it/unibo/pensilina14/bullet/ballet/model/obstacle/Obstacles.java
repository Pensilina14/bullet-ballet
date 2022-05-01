package it.unibo.pensilina14.bullet.ballet.model.obstacle;

public enum Obstacles {
  MASS(5.0),
  WIDTH(50.0),
  HEIGHT(50.0),
  MOVE_UP_DELTA(10.0),
  MOVE_DOWN_DELTA(8.0),
  MAX_ITERATIONS_DELTA(100.0);

  private final double value;

  Obstacles(final double value) {
    this.value = value;
  }

  public double getValue() {
    return this.value;
  }
}
