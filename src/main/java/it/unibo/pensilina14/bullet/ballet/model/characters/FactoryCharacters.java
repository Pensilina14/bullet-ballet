package it.unibo.pensilina14.bullet.ballet.model.characters;

import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;

public interface FactoryCharacters {

    /**
     *
     * @param playerType : an enum with the name of the player that you want to create.
     * @return : a Player.
     */

    Player createPlayer(EntityList.Characters.Player playerType, SpeedVector2D vector);

    /**
     *
     * @return : a random Player. It creates a random PlayerType.
     */

    Player createRandomPlayer(SpeedVector2D vector);

    /**
     *
     * @param enemyType : an enum with the name of the enemy that you want to create.
     * @return : an Enemy.
     */

    Enemy createEnemy(EntityList.Characters.Enemy enemyType, SpeedVector2D vector);

    /**
     *
     * @return : a random Enemy. It creates a random enemyType.
     */

    Enemy createRandomEnemy(SpeedVector2D vector);
}
