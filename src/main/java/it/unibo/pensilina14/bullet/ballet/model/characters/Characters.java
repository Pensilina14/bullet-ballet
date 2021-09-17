package it.unibo.pensilina14.bullet.ballet.model.characters;

import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;

public interface Characters {

    /**
     *
     * @return health of the character.
     */

    double getHealth();

    /**
     *
     * @return optional mana of the character.
     */

    Optional<Double> getMana();

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

    /**
     *
     * @param x movement in the x-axis.
     * @param y movement in the y-axis.
     * @return whether movement has occurred or not.
     */

    boolean move(int x, int y);

    /**
     *
     * @param y movement up in the y-axis.
     * @return whether movement up has occurred or not.
     */

    boolean moveUp(int y);

    /**
     *
     * @param y movement down in the y-axis.
     * @return whether movement down has occurred or not.
     */

    boolean moveDown(int y);

    /**
     *
     * @param x movement right in the x-axis.
     * @return whether movement right has occurred or not.
     */

    boolean moveRight(int x);

    /**
     *
     * @param x movement left in the x-axis.
     * @return whether movement left has occurred or not.
     */

    boolean moveLeft(int x);
}
