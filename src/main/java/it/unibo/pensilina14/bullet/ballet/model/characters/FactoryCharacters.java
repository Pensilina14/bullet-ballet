package it.unibo.pensilina14.bullet.ballet.model.characters;

import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface FactoryCharacters {

    /**
     *
     * @param playerType : an enum with the name of the player that you want to create.
     * @param vector : a vector which has speed and position.
     * @param environment : gameEnvironment.
     * @return : a Player.
     */

    Player createPlayer(final EntityList.Characters.Player playerType, final SpeedVector2D vector, final Environment environment);

    /**
     *
     * @return : a random Player. It creates a random PlayerType.
     * @param vector : a vector which has speed and position.
     * @param environment : gameEnvironment.
     */

    Player createRandomPlayer(final SpeedVector2D vector, final Environment environment);

    /**
     *
     * @param enemyType : an enum with the name of the enemy that you want to create.
     * @param vector : a vector which has speed and position.
     * @param environment : gameEnvironment.
     * @return : an Enemy.
     */

    Enemy createEnemy(final EntityList.Characters.Enemy enemyType, final SpeedVector2D vector, final Environment environment);

    /**
     *
     * @return : a random Enemy. It creates a random enemyType.
     * @param vector : a vector which has speed and position.
     * @param environment : gameEnvironment.
     */

    Enemy createRandomEnemy(final SpeedVector2D vector, final Environment environment);
}
