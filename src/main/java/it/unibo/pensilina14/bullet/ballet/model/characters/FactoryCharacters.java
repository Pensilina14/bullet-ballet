package it.unibo.pensilina14.bullet.ballet.model.characters;

public interface FactoryCharacters {

    /**
     *
     * @param playerType : an enum with the name of the player that you want to create.
     * @return : a Player.
     */

    Player createPlayer(EntityList.Characters.Player playerType);

    /**
     *
     * @return : a random Player. It creates a random PlayerType.
     */

    Player createRandomPlayer();

    /**
     *
     * @param enemyType : an enum with the name of the enemy that you want to create.
     * @return : an Enemy.
     */

    Enemy createEnemy(EntityList.Characters.Enemy enemyType);

    /**
     *
     * @return : a random Enemy. It creates a random enemyType.
     */

    Enemy createRandomEnemy();
}
