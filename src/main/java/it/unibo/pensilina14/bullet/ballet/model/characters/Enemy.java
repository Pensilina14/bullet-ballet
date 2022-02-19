package it.unibo.pensilina14.bullet.ballet.model.characters;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;

import java.util.Optional;
import java.util.Random;

public class Enemy extends GameEntity implements Characters{

    private double health;
    private Optional<Double> mana;
    private String name;

    private Weapon weapon;

    private EntityList.Characters.Enemy enemyType;

    private final Random rand = new Random();
    private final static double MAX = 100.0;
    private boolean landed;

    private static final double MAX_RANGE = 7.0; //TODO: oppure tenere un solo range per tutti. //TODO: forse incrementarlo.

    private final double enemyRange = getRandomRange();

    public Enemy(String name, double health, Optional<Double> mana, Dimension2D dimension, SpeedVector2D vector, Environment environment, double mass){

        super(vector, environment, mass, dimension);

        this.name = name;
        this.health = health;
        this.mana = mana;

    }

    public Enemy(EntityList.Characters.Enemy enemyType, Dimension2D dimension, SpeedVector2D vector, Environment environment, double mass){
        super(vector, environment, mass, dimension);
        this.enemyType = enemyType;
        setEnemyType();
    }

    public Enemy(Dimension2D dimension, SpeedVector2D vector, Environment environment, double mass){
        super(vector, environment, mass, dimension);

        setRandomEnemy();
        setEnemyType();
    }

    private void setRandomEnemy() {
        Random rand = new Random();
        final int max = EntityList.Characters.Enemy.values().length;
        final int min = 0;

        final int randomEnemy = rand.nextInt(((max - min)) + min);
        for (EntityList.Characters.Enemy e : EntityList.Characters.Enemy.values()){
            if (e.ordinal() == randomEnemy) {
                this.enemyType = e;
            }
        }

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
                //this.weapon = new WeaponImpl("AK-47", dimension, vector, environment, mass, id, effect); //TODO: add weapon, WeaponFactoryImpl
                break;
            case ENEMY2:
                minHealth = 60.0;
                minMana = 55.0;
                this.name = "Enemy2";
                this.health = (this.rand.nextDouble() * (MAX - minHealth)) + minHealth;
                this.mana = Optional.of((this.rand.nextDouble() * (MAX - minMana)) + minMana);
                //this.weapon = new WeaponImpl("M4A1"); //TODO: add weapon
                break;
            case ENEMY3:
                minHealth = 40.0;
                minMana = 70.0;
                this.name = "Enemy3";
                this.health = (this.rand.nextDouble() * (MAX - minHealth)) + minHealth;
                this.mana = Optional.of((this.rand.nextDouble() * (MAX - minMana)) + minMana);
                //this.weapon = new WeaponImpl("Bazooka"); //TODO: add weapon
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

    public EntityList.Characters.Enemy getEnemyType() {
        return this.enemyType;
    }
    
    @Override
    public void updateState() {
    	super.updateState();
    	if (!this.isAlive()) {
			this.getGameEnvironment().get()
			.deleteObjByPosition(new ImmutablePosition2Dimpl(this.getPosition().get()));
    	}
    }
    
    /*
     * Following code could be universalized for every game entity.
     */
    public boolean hasLanded() {
    	return this.landed;
    }
    
    public void land() {
    	this.landed = true;
    }
    
    public void resetLanding() {
    	this.landed = false;
    }

    private double getRandomRange(){
        return rand.nextDouble() * Enemy.MAX_RANGE;
    }

    //TODO: forse passare l'index dell'enemy come parametro non serve.
    public boolean isPlayerInRange(final int enemyIndex){
        final double xPlayer = this.getGameEnvironment().get().getEntityManager().getPlayer().get().getPosition().get().getX();
        final double yPlayer = this.getGameEnvironment().get().getEntityManager().getPlayer().get().getPosition().get().getX();

        final double xEnemy = this.getGameEnvironment().get().getEntityManager().getEnemies().get().get(enemyIndex).getPosition().get().getX();
        final double yEnemy = this.getGameEnvironment().get().getEntityManager().getEnemies().get().get(enemyIndex).getPosition().get().getY();

        //final double range = getRandomRange(); //TODO: il range o fisso per tutti gli enemy o così com'è adesso ovvero casuale.

        System.out.println("Player X: " + xPlayer); //TODO: remove
        System.out.println("Player Y: " + yPlayer); //TODO: remove

        System.out.println("Enemy X: " + xEnemy); //TODO: remove
        System.out.println("Enemy Y: " + yEnemy); //TODO: remove

        final double distance = Math.sqrt((xPlayer - xEnemy) + (yPlayer - yEnemy)); //TODO: la radice quadrata si può anche togliere, è lenta e non serve.

        //TODO: LERP

        System.out.println("Enemy Range: " + this.enemyRange); //TODO: remove
        System.out.println("Distanza player ed Enemy: " + distance); //TODO: remove

        return distance <= this.enemyRange;
    }

    public double getRange(){
        return this.enemyRange;
    }

    public double getMaxRange(){
        return Enemy.MAX_RANGE;
    }

}
