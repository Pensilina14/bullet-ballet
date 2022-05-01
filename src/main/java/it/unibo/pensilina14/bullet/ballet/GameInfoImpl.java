package it.unibo.pensilina14.bullet.ballet;

import it.unibo.pensilina14.bullet.ballet.menu.controller.Difficulties;
import it.unibo.pensilina14.bullet.ballet.menu.controller.Resolutions;

public class GameInfoImpl implements GameInfo {

  private Resolutions resolution;
  private Difficulties difficulty;

  public GameInfoImpl(final Resolutions resolution, final Difficulties difficulty) {
    this.resolution = resolution;
    this.difficulty = difficulty;
  }

  @Override
  public Resolutions getCurrentResolution() {
    return this.resolution;
  }

  @Override
  public Difficulties getCurrentDifficulty() {
    return this.difficulty;
  }

  @Override
  public void setResolution(final Resolutions resolution) {
    this.resolution = resolution;
  }

  @Override
  public void setDifficulty(final Difficulties difficulty) {
    this.difficulty = difficulty;
  }
}
