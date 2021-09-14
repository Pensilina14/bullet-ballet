package it.unibo.pensilina14.bullet.ballet.characters;

import it.unibo.pensilina14.bullet.ballet.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.weapon.WeaponImpl;

import java.util.Optional;

public class Player implements Characters{

    private double health;
    private Optional<Double> mana;

    private String name;

    private Weapon weapon;

    private EntityList.Player playerType;

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
        switch(playerType){
            case PLAYER1:
                this.name = "Player1";
                this.health = 82.0;
                this.mana = Optional.of(50.0);
                this.weapon = new WeaponImpl("AK-47");
            case PLAYER2:
                // TODO: to implement
                break;
            case PLAYER3:
                // TODO: to implement
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
