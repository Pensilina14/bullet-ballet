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

    void setHealth(float setHealth);

    boolean jump();

    boolean crouch();

    void getWeapon(); // returns a Weapon

    boolean setWeapon();
}
