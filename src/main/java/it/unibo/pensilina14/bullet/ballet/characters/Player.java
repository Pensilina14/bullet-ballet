package it.unibo.pensilina14.bullet.ballet.characters;

import it.unibo.pensilina14.bullet.ballet.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.weapon.WeaponImpl;

import java.util.Optional;
import java.util.Random;

public class Player implements Characters{

    private double health;
    private Optional<Double> mana;

    private String name;

    private Weapon weapon;

    private EntityList.Player playerType;

    private final Random rand = new Random();
    private final static double MAX = 100.0;
    //private final static double MIN = 0.0;

    public Player(String name){
        this.name = name;
        this.health = 100.0;
        this.mana = Optional.of(100.0);
    }

    public Player(String name, double health,Optional<Double> mana){
        this.name = name;
        this.health = health;
        this.mana = mana;
    }

    public Player(EntityList.Player playerType){
        this.playerType = playerType;
        setPlayerType(playerType);
    }

    private void setPlayerType(EntityList.Player playerType){
        double minHealth;
        double minMana;
        switch(playerType){
            case PLAYER1:
                minHealth = 80.0;
                minMana = 50.0;
                this.name = "Player1";
                this.health = (this.rand.nextDouble() * (MAX - minHealth)) + minHealth;
                this.mana = Optional.of((this.rand.nextDouble() * (MAX - minMana)) + minMana);
                break;
            case PLAYER2:
                minHealth = 65.0;
                minMana = 70.0;
                this.name = "Player2";
                this.health = (this.rand.nextDouble() * (MAX - minHealth)) + minHealth;
                this.mana = Optional.of((this.rand.nextDouble() * (MAX - minMana)) + minMana);
                this.weapon = new WeaponImpl("Knife");
                break;
            case PLAYER3:
                minHealth = 50.0;
                minMana = 85.0;
                this.name = "Player3";
                this.health = (this.rand.nextDouble() * (MAX - minHealth)) + minHealth;
                this.mana = Optional.of((this.rand.nextDouble() * (MAX - minMana)) + minMana);
                this.weapon = new WeaponImpl("AK-47");
                break;
        }
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public Optional<Double> getMana() {
        return this.mana;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0.0;
    }

    @Override
    public void setHealth(double setHealth) {
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

    @Override
    public boolean manaLeft() {
        return this.mana.filter( i -> i > 0.0).isPresent();
    }

    @Override
    public void decreaseMana(double decreaseValue) {
        if(this.mana.isPresent()){
            this.mana = Optional.of(this.mana.get() - decreaseValue);
        }
    }

    @Override
    public void increaseMana(double increaseValue) {
        if(this.mana.isPresent()){
            this.mana = Optional.of(this.mana.get() + increaseValue);
        }
    }
}
