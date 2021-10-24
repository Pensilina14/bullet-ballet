package it.unibo.pensilina14.bullet.ballet.model.characters;

import it.unibo.pensilina14.bullet.ballet.common.*;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class FactoryCharactersImpl implements FactoryCharacters{

    private static final int DEFAULT_DIM = 500;
    private static final int DEFAULT_MASS = 10;

    private final Dimension2Dimpl dimension = new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM);

    @Override
    public Player createPlayer(EntityList.Characters.Player playerType, SpeedVector2D vector, Environment environment) {
        return new Player(playerType, this.dimension, vector, environment, FactoryCharactersImpl.DEFAULT_MASS);
    }

    @Override
    public Player createRandomPlayer(SpeedVector2D vector, Environment environment) {
        return new Player(this.dimension, vector, environment, FactoryCharactersImpl.DEFAULT_MASS);
    }

    @Override
    public Enemy createEnemy(EntityList.Characters.Enemy enemyType, SpeedVector2D vector, Environment environment) {
        return new Enemy(enemyType, this.dimension, vector, environment, FactoryCharactersImpl.DEFAULT_MASS);
    }

    @Override
    public Enemy createRandomEnemy(SpeedVector2D vector, Environment environment) {
        return new Enemy(this.dimension, vector, environment, FactoryCharactersImpl.DEFAULT_MASS);
    }

}
