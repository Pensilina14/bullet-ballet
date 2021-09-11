package it.unibo.pensilina14.bullet.ballet.characters;

import it.unibo.pensilina14.bullet.ballet.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.game.entities.AbstractDynamicComponent;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.weapon.WeaponImpl;

import javax.xml.crypto.dsig.DigestMethod;

public class Enemy extends AbstractDynamicComponent implements Characters{

    private double health;
    private double mana;
    private final int numberOfEnemies;
    private final String name;

    private Weapon weapon;

    public Enemy(String name, float health, float mana, int numberOfEnemies, Dimension2D dimension, MutablePosition2D position, Environment environment, double mass){

        super(dimension, position, environment, mass);

        this.name = name;
        this.health = health;
        this.mana = mana;
        this.numberOfEnemies = numberOfEnemies;
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public double getMana() {
        return this.mana;
    }

    @Override
    public boolean isAlive() {
        return this.health <= 0.0;
    }

    @Override
    public void setHealth(float setHealth) {
        this.health = setHealth;
    }

    @Override
    public boolean jump() {
        return false;
    }

    @Override
    public boolean crouch() {
        return false;
    }

    @Override
    public Weapon getWeapon() {
        return this.weapon;
    }

    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
