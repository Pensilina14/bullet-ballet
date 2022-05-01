package it.unibo.pensilina14.bullet.ballet.model.characters;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class FactoryCharactersImpl implements FactoryCharacters {

  private static final int DEFAULT_DIM = 40;
  private static final int DEFAULT_MASS = 10;

  private final Dimension2Dimpl dimension = new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM);

  @Override
  public Player createPlayer(
      final EntityList.Characters.Player playerType,
      final SpeedVector2D vector,
      final Environment environment) {
    return new Player(
        playerType, this.dimension, vector, environment, FactoryCharactersImpl.DEFAULT_MASS);
  }

  @Override
  public Player createRandomPlayer(final SpeedVector2D vector, final Environment environment) {
    return new Player(this.dimension, vector, environment, FactoryCharactersImpl.DEFAULT_MASS);
  }

  @Override
  public Enemy createEnemy(
      final EntityList.Characters.Enemy enemyType,
      final SpeedVector2D vector,
      final Environment environment) {
    return new Enemy(
        enemyType, this.dimension, vector, environment, FactoryCharactersImpl.DEFAULT_MASS);
  }

  @Override
  public Enemy createRandomEnemy(final SpeedVector2D vector, final Environment environment) {
    return new Enemy(this.dimension, vector, environment, FactoryCharactersImpl.DEFAULT_MASS);
  }
}
