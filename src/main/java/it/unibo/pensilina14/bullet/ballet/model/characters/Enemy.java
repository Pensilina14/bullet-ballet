package it.unibo.pensilina14.bullet.ballet.model.characters;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.entities.AbstractDynamicComponent;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponImpl;

import java.util.Optional;
import java.util.Random;

public class Enemy extends AbstractDynamicComponent implements Characters{

    private double health;
    private Optional<Double> mana;
    //private int numberOfEnemies;
    private String name;

    private Weapon weapon;

    private EntityList.Characters.Enemy enemyType;

    private final Random rand = new Random();
    private final static double MAX = 100.0;

    public Enemy(String name, double health, Optional<Double> mana, Dimension2D dimension, MutablePosition2D position, Environment environment, double mass){

        super(dimension, position, environment, mass, 0);

        this.name = name;
        this.health = health;
        this.mana = mana;
        //this.numberOfEnemies = numberOfEnemies;

    }

    public Enemy(EntityList.Characters.Enemy enemyType, Dimension2D dimension, MutablePosition2D position, Environment environment, double mass){
        super(dimension, position, environment, mass, 0);
        this.enemyType = enemyType;
        setEnemyType(this.enemyType);
    }

    private void setEnemyType(EntityList.Characters.Enemy enemyType){
        double minHealth;
        double minMana;
        switch(enemyType){
            case ENEMY1:
                minHealth = 80.0; // 70.0
                minMana = 40.0;
                this.name = "Enemy1";
                //this.numberOfEnemies = 0;
                this.health = (this.rand.nextDouble() * (MAX - minHealth)) + minHealth;
                this.mana = Optional.of((this.rand.nextDouble() * (MAX - minMana)) + minMana);
                this.weapon = new WeaponImpl("AK-47");
                break;
            case ENEMY2:
                minHealth = 60.0;
                minMana = 55.0;
                this.name = "Enemy2";
                this.health = (this.rand.nextDouble() * (MAX - minHealth)) + minHealth;
                this.mana = Optional.of((this.rand.nextDouble() * (MAX - minMana)) + minMana);
                this.weapon = new WeaponImpl("M4A1");
                break;
            case ENEMY3:
                minHealth = 40.0;
                minMana = 70.0;
                this.name = "Enemy3";
                this.health = (this.rand.nextDouble() * (MAX - minHealth)) + minHealth;
                this.mana = Optional.of((this.rand.nextDouble() * (MAX - minMana)) + minMana);
                this.weapon = new WeaponImpl("Bazooka");
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
    public boolean jump() {
        return false; // TODO: jump
    }

    @Override
    public boolean crouch() {
        return false; // TODO: crouch
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
    public boolean moveUp(int y) {
        return false; // TODO: moveUp
    }

    @Override
    public boolean moveDown(int y) {
        return false; // TODO: moveDown
    }

    @Override
    public boolean moveRight(int x) {
        return false; // TODO: moveRight
    }

    @Override
    public boolean moveLeft(int x) {
        return false; // TODO: moveLeft
    }

    @Override
    public boolean move(int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }
}
