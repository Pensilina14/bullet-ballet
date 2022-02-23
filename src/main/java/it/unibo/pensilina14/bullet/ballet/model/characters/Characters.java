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
    void setHealth(final double setHealth);

    /**
     * @return the Weapon that the character is using.
     */

    Optional<Weapon> getWeapon(); // returns a Weapon

    /**
     * @param weapon: set the new character weapon.
     *
     */

    void setWeapon(final Weapon weapon);

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

    void decreaseMana(final double decreaseValue);

    /**
     *
     * @param increaseValue: increases mana.
     */

    void increaseMana(final double increaseValue);

    /**
     *
     * @param increaseHealth: increases health by increaseHealth amount.
     */

    void increaseHealth(final double increaseHealth);

    /**
     *
     * @param decreaseHealth: decreases health by decreaseHealth amount.
     */

    void decreaseHealth(final double decreaseHealth);

}
