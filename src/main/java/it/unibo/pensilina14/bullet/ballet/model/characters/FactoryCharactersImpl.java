package it.unibo.pensilina14.bullet.ballet.model.characters;

import it.unibo.pensilina14.bullet.ballet.common.*;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public class FactoryCharactersImpl implements FactoryCharacters{

    private static final int DEFAULT_DIM = 500;
    private static final int DEFAULT_MASS = 10;

    private final Dimension2Dimpl dimension = new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM);
    //private final Environment environment;

    /*private final MutablePosition2Dimpl position = new MutablePosition2Dimpl(0, 0);
    private final double speed = 5.0;
    private final SpeedVector2D vector = new SpeedVector2DImpl(position, speed);*/

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
