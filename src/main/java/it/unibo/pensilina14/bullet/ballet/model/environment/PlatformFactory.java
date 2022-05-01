package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;

/** Generates platforms. */
public interface PlatformFactory {
  /**
   * Generates a {@link Platform}.
   *
   * @param env the {@link Environment} in which the platform is placed in.
   * @param speedVector is a vector representation that has speed {@link SpeedVector2D}.
   * @return a {@link Platform} instance.
   */
  Platform createPlatform(Environment env, SpeedVector2D speedVector);
}
