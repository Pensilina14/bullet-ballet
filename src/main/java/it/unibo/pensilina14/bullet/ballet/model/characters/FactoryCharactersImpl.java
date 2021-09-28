package it.unibo.pensilina14.bullet.ballet.model.characters;

import it.unibo.pensilina14.bullet.ballet.common.*;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;

public class FactoryCharactersImpl implements FactoryCharacters{

    private static final int DEFAULT_DIM = 500;
    private static final int DEFAULT_MASS = 10;

    final Dimension2Dimpl dimension = new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM);
    final Environment environment = new GameEnvironment();
    final MutablePosition2Dimpl position = new MutablePosition2Dimpl(0, 0);
    final double speed = 5.0;
    final SpeedVector2D vector = new SpeedVector2DImpl(position, speed);

    @Override
    public Player createPlayer1() {
        return new Player(EntityList.Characters.Player.PLAYER1, dimension, vector, environment, DEFAULT_MASS);
    }

    @Override
    public Player createPlayer2() {
        return new Player(EntityList.Characters.Player.PLAYER2, dimension, vector, environment, DEFAULT_MASS);
    }

    @Override
    public Player createPlayer3() {
        return new Player(EntityList.Characters.Player.PLAYER3, dimension, vector, environment, DEFAULT_MASS);
    }

    @Override
    public Enemy createEnemy1() {
        return new Enemy(EntityList.Characters.Enemy.ENEMY1, dimension, vector, environment, DEFAULT_MASS);
    }

    @Override
    public Enemy createEnemy2() {
        return new Enemy(EntityList.Characters.Enemy.ENEMY2, dimension, vector, environment, DEFAULT_MASS);
    }

    @Override
    public Enemy createEnemy3() {
        return new Enemy(EntityList.Characters.Enemy.ENEMY3, dimension, vector, environment, DEFAULT_MASS);
    }
}
