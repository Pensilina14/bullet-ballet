package it.unibo.pensilina14.bullet.ballet.model.characters;

public interface FactoryCharacters {

    //TODO: Add a specific name for each type of Player and Enemy instead than numbers.

    Player createPlayer1();

    Player createPlayer2();

    Player createPlayer3();

    Enemy createEnemy1();

    Enemy createEnemy2();

    Enemy createEnemy3();
}
