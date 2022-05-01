package it.unibo.pensilina14.bullet.ballet.menu.controller;

public enum Resolutions {
  FULLHD(1920, 1080),
  HD(1280, 720);

  private final int width;
  private final int height;

  Resolutions(final int width, final int height) {
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  @Override
  public String toString() {
    return "[ " + this.width + " ], [ " + this.height + " ]";
  }

  public static Resolutions getDefaultResolution() {
    return Resolutions.HD;
  }
}
