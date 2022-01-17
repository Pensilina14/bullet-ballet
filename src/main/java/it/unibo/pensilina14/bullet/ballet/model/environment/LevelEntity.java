package it.unibo.pensilina14.bullet.ballet.model.environment;

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

public final class LevelEntity{ //TODO: fix the enum and use that
	/**
	 * 
	 */
    public static final char EMPTY = '0';
    /**
     * 
     */
    public static final char PLATFORM = '1';
    /**
     * 
     */
    public static final char COIN = '2';
    /**
     * 
     */
    public static final char OBSTACLE = '3';
    /**
     * 
     */
    public static final char GUN = 'G';
    /**
     * 
     */
    public static final char SHOTHUN = 'S';
    /**
     * 
     */
    public static final char AUTOGUN = 'A';
    /**
     * 
     */
    public static final char PLAYER = 'P';
    /**
     * 
     */
    public static final char HEART = '*';
    /**
     * 
     */
    public static final char POISON = 'x';
    /**
     * 
     */
    public static final char DAMAGE = 'd';
    /**
     * 
     */
    public static final char ENEMY = '!';
	
    private LevelEntity(){

    }


}
