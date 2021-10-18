package it.unibo.pensilina14.bullet.ballet.model.characters;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.model.entities.AbstractDynamicComponent;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ITEM_ID;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponImpl;

import java.util.Optional;
import java.util.Random;

public class Enemy extends AbstractDynamicComponent implements Characters{

    private double health;
    private Optional<Double> mana; //TODO: remove?
    private String name;

    private Weapon weapon;

    private EntityList.Characters.Enemy enemyType;

    private final Random rand = new Random();
    private final static double MAX = 100.0;

    public Enemy(String name, double health, Optional<Double> mana, Dimension2D dimension, SpeedVector2D vector, Environment environment, double mass){

        super(dimension, environment, mass, vector);

        this.name = name;
        this.health = health;
        this.mana = mana;

    }

    public Enemy(EntityList.Characters.Enemy enemyType, Dimension2D dimension, SpeedVector2D vector, Environment environment, double mass){
        super(dimension, environment, mass, vector);
        this.enemyType = enemyType;
        setEnemyType();
    }

    public Enemy(Dimension2D dimension, SpeedVector2D vector, Environment environment, double mass){
        super(dimension, environment, mass, vector);

        setRandomEnemy();

    }

    private void setRandomEnemy() {
        Random rand = new Random();
        final int max = EntityList.Characters.Enemy.values().length;
        final int min = 0;

        //TODO: enemyType remains empty, to be fixed..
        /*final int randomEnemy = rand.nextInt(((max - min) + 1 ) + min);
        for (EntityList.Characters.Enemy e : EntityList.Characters.Enemy.values()){
            if (e.ordinal() == randomEnemy) {
                this.enemyType = e;
            }
        }*/
        this.enemyType = EntityList.Characters.Enemy.ENEMY1;

        setEnemyType();
    }

    private void setEnemyType(){
        double minHealth;
        double minMana;
        switch(this.enemyType){
            case ENEMY1:
                minHealth = 80.0; // 70.0
                minMana = 40.0;
                this.name = "Enemy1";
                this.health = (this.rand.nextDouble() * (MAX - minHealth)) + minHealth;
                this.mana = Optional.of((this.rand.nextDouble() * (MAX - minMana)) + minMana);
                //this.weapon = new WeaponImpl("AK-47", dimension, vector, environment, mass, id, effect);
                break;
            case ENEMY2:
                minHealth = 60.0;
                minMana = 55.0;
                this.name = "Enemy2";
                this.health = (this.rand.nextDouble() * (MAX - minHealth)) + minHealth;
                this.mana = Optional.of((this.rand.nextDouble() * (MAX - minMana)) + minMana);
                //this.weapon = new WeaponImpl("M4A1");
                break;
            case ENEMY3:
                minHealth = 40.0;
                minMana = 70.0;
                this.name = "Enemy3";
                this.health = (this.rand.nextDouble() * (MAX - minHealth)) + minHealth;
                this.mana = Optional.of((this.rand.nextDouble() * (MAX - minMana)) + minMana);
                //this.weapon = new WeaponImpl("Bazooka");
                break;
        }
    }

    private void AI(){
        // TODO: AI of Enemy
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
    public boolean jump() { //TODO: add a parameter
        return super.moveUP(10.0);
    }

    @Override
    public boolean crouch() { //TODO: add a parameter
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
            this.mana = Optional.of( this.mana.get() - decreaseValue);
        }
    }

    @Override
    public void increaseMana(double increaseValue) {
        if(this.mana.isPresent()){
            this.mana = Optional.of( this.mana.get() + increaseValue);
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
}
