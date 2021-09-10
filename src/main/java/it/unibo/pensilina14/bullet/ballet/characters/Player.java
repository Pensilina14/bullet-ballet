package it.unibo.pensilina14.bullet.ballet.characters;

public class Player implements Characters{

    private float health = 100.0f;
    private float mana = 100.0f;

    @Override
    public float getHealth() {
        return this.health;
    }

    @Override
    public float getMana() {
        return this.mana;
    }

    @Override
    public boolean isAlive() {
        return this.health <= 0.0f;
    }
}
