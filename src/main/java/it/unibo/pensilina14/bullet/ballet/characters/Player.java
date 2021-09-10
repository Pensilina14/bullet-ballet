package it.unibo.pensilina14.bullet.ballet.characters;

public class Player implements Characters{

    private double health = 100.0;
    private double mana = 100.0;

    private final String name;

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
    public void getWeapon() {

    }

    @Override
    public boolean setWeapon() {
        return false;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
