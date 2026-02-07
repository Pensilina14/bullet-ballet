package it.unibo.pensilina14.bullet.ballet.menu.controller;

public enum Resolutions {
  UHD(3840, 2160),
  QHD(2560, 1440),
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

  /**
   * Tries to map a width/height pair to a supported resolution.
   * If an exact match is not found, picks the closest supported one.
   *
   * @param width the desired width
   * @param height the desired height
   * @return a supported {@link Resolutions}
   */
  public static Resolutions fromDimensions(final int width, final int height) {
    for (final var r : Resolutions.values()) {
      if (r.getWidth() == width && r.getHeight() == height) {
        return r;
      }
    }
    // Minimal heuristic: prefer FULLHD only if both dimensions are >= FULLHD.
    if (width >= Resolutions.UHD.getWidth() && height >= Resolutions.UHD.getHeight()) {
      return Resolutions.UHD;
    }
    if (width >= Resolutions.QHD.getWidth() && height >= Resolutions.QHD.getHeight()) {
      return Resolutions.QHD;
    }
    if (width >= Resolutions.FULLHD.getWidth() && height >= Resolutions.FULLHD.getHeight()) {
      return Resolutions.FULLHD;
    }
    return Resolutions.HD;
  }

  /**
   * Picks the largest supported resolution that fits in the provided bounds.
   *
   * @param maxWidth available width
   * @param maxHeight available height
   * @return the best fitting {@link Resolutions}
   */
  public static Resolutions bestFit(final double maxWidth, final double maxHeight) {
    for (final var r : Resolutions.values()) {
      if (r.getWidth() <= maxWidth && r.getHeight() <= maxHeight) {
        return r;
      }
    }
    return Resolutions.HD;
  }

  public static Resolutions getDefaultResolution() {
    return Resolutions.HD;
  }
}
