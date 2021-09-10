package it.unibo.pensilina14.bullet.ballet.characters;

public interface Characters {

    /**
     *
     * @return health of the character.
     */

    double getHealth();

    /**
     *
     * @return mana of the character.
     */

    double getMana();

    /**
     *
     * @return whether character is alive or not.
     */

    boolean isAlive(); // isDead

    /**
     *
     * @param setHealth: set new health of the character
     */
    void setHealth(float setHealth);

    /**
     *
     * @return wheter has jumped
     */

    boolean jump();

    /**
     *
     * @return wheter is crouching
     */

    boolean crouch();

    /**
     * returns the Weapon that the character is using.
     */

    void getWeapon(); // returns a Weapon

    /**
     * parameter: a Weapon
     * @return if weapon has been set
     */

    boolean setWeapon();

    /**
     *
     * @return name of the character
     */

    String getName();
}
