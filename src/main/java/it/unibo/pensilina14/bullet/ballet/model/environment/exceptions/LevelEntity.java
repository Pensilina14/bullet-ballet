package it.unibo.pensilina14.bullet.ballet.model.environment.exceptions;

/*public enum LevelEntity { //TODO: rename?

    EMPTY('0'),
    PLATFORM('1'),
    COIN('2'),
    OBSTACLE('3'),
    WEAPON('4'),
    PLAYER('P'),
    ITEM('*'),
    ENEMY('!');

    final char s;

    LevelEntity(final char s){
        this.s = s;
    }

    public final char getSymbol(){ //TODO: rename getAsChar() o getCharacter()
        return this.s;
    }

}*/

public abstract class LevelEntity{ //TODO: fix the enum and use that.

    public static final char EMPTY = '0';
    public static final char PLATFORM = '1';
    public static final char COIN = '2';
    public static final char OBSTACLE = '3';
    public static final char WEAPON = '4';
    public static final char PLAYER = 'P';
    public static final char ITEM = '*';
    public static final char ENEMY = '!';
}
