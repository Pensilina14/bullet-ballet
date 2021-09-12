package it.unibo.pensilina14.bullet.ballet.characters;

import it.unibo.pensilina14.bullet.ballet.weapon.Weapon;

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

    boolean isAlive();

    /**
     *
     * @param setHealth: set new health of the character
     */
    void setHealth(double setHealth);

    /**
     *
     * @return whether the character has jumped
     */

    boolean jump();

    /**
     *
     * @return whether is crouching
     */

    boolean crouch();

    /**
     * @return the Weapon that the character is using.
     */

    Weapon getWeapon(); // returns a Weapon

    /**
     * @param weapon: set the new character weapon.
     *
     */

    void setWeapon(Weapon weapon);

    /**
     *
     * @return name of the character
     */

    String getName();

    /**
     *
     * @return whether the character has any mana left.
     */

    boolean manaLeft();

    /**
     *
     * @param decreaseValue: decreases mana.
     */

    void decreaseMana(double decreaseValue);

    /**
     *
     * @param increaseValue: increases mana.
     */

    void increaseMana(double increaseValue);
}
