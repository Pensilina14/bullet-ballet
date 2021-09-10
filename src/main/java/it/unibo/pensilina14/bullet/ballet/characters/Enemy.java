package it.unibo.pensilina14.bullet.ballet.characters;

public class Enemy implements Characters{

    private double health;
    private double mana;
    private final int numberOfEnemies;
    private final String name;

    public Enemy(String name, float health, float mana, int numberOfEnemies){
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
