package it.unibo.pensilina14.bullet.ballet.model.characters;

import java.util.Optional;
import java.util.Random;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.model.entities.AbstractDynamicComponent;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponImpl;

public class Player extends AbstractDynamicComponent implements Characters{

    private double health;
    private Optional<Double> mana;

    private String name;

    private Weapon weapon;

    private EntityList.Characters.Player playerType;

    private final Random rand = new Random();
    private final static double MAX = 100.0;

    public Player(String name, Dimension2D dimension, SpeedVector2D vector, Environment environment, double mass){
        super(dimension, environment, mass, vector);

        this.name = name;
        this.health = 100.0;
        this.mana = Optional.of(100.0);
    }

    public Player(String name, double health,Optional<Double> mana, Dimension2D dimension, SpeedVector2D vector, Environment environment, double mass){
        super(dimension, environment, mass, vector);

        this.name = name;
        this.health = health;
        this.mana = mana;
    }

    public Player(EntityList.Characters.Player playerType, Dimension2D dimension, SpeedVector2D vector, Environment environment, double mass){
        super(dimension, environment, mass, vector);

        this.playerType = playerType;
        setPlayerType();
    }

    public Player(Dimension2D dimension, SpeedVector2D vector, Environment environment, double mass){
        super(dimension, environment, mass, vector);

        setRandomPlayer();
        setPlayerType();

    }

    private void setRandomPlayer() {
        Random rand = new Random();
        final int max = EntityList.Characters.Player.values().length;
        final int min = 0;

        final int randomPlayer = rand.nextInt(((max - min)) + min);
        for(EntityList.Characters.Player p : EntityList.Characters.Player.values()){
            if(p.ordinal() == randomPlayer){
                this.playerType = p;
            }
        }

    }

    private void setPlayerType(){
        double minHealth;
        double minMana;
        switch(this.playerType){
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
                //this.weapon = new WeaponImpl("Knife");
                break;
            case PLAYER3:
                minHealth = 50.0;
                minMana = 85.0;
                this.name = "Player3";
                this.health = (this.rand.nextDouble() * (MAX - minHealth)) + minHealth;
                this.mana = Optional.of((this.rand.nextDouble() * (MAX - minMana)) + minMana);
                //this.weapon = new WeaponImpl("AK-47");
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
    public boolean jump() { //TODO: add parameter
        return super.moveUP(10.0);
    }

    @Override
    public boolean crouch() { //TODO: add parameter
        return super.moveDOWN(10.0);
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

    @Override
    public void increaseHealth(double increaseHealth) {
        this.health += increaseHealth;
    }

    @Override
    public void decreaseHealth(double decreaseHealth) {
        this.health -= decreaseHealth;
    }

    public EntityList.Characters.Player getPlayerType() {
        return this.playerType;
    }
}
