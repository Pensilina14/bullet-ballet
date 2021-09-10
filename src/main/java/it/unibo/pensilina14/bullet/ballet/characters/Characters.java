package it.unibo.pensilina14.bullet.ballet.characters;

public interface Characters {

    /**
     *
     * @return health of the character.
     */

    float getHealth();

    /**
     *
     * @return mana of the character.
     */

    float getMana();

    /**
     *
     * @return whether character is alive or not.
     */

    boolean isAlive(); // isDead
}
