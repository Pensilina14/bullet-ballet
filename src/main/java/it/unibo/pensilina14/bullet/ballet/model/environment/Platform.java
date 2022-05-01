package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;

public class Platform extends GameEntity {

  public Platform(
      final SpeedVector2D speedVector,
      final Environment gameEnvironment,
      final double mass,
      final Dimension2D dimension) {
    super(speedVector, gameEnvironment, mass, dimension);
  }
}
