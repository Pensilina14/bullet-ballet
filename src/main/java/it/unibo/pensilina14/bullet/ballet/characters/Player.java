package it.unibo.pensilina14.bullet.ballet.characters;

import it.unibo.pensilina14.bullet.ballet.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.weapon.WeaponImpl;

public class Player implements Characters{

    private double health = 100.0;
    private double mana = 100.0;

    private final String name;

    private Weapon weapon;

    public Player(String name){
        this.name = name;
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
