package it.unibo.pensilina14.bullet.ballet.model.characters;

import java.util.Optional;
import java.util.Random;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.logging.AppLogger;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.score.ScoreSystem;
import it.unibo.pensilina14.bullet.ballet.model.score.ScoreSystemImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;

public class Player extends GameEntity implements Characters{

    private double health;
    private Optional<Double> mana;

    private String name;


    private Optional<Weapon> weapon;
    
    private boolean landed;
    private boolean blockedX;

    private EntityList.Characters.Player playerType;

    private final ScoreSystem currentScore = new ScoreSystemImpl(0);
    
    private final Random rand = new Random();
    private final static double MAX = 100.0;

    public Player(final String name, final Dimension2D dimension, final SpeedVector2D vector
    		, final Environment environment, final double mass){
        super(vector, environment, mass, dimension);

        this.name = name;
        this.health = 100.0;
        this.mana = Optional.of(100.0);
        this.weapon = Optional.empty();
    }

    public Player(final String name, final double health,final Optional<Double> mana, final Dimension2D dimension
    		, final SpeedVector2D vector, final Environment environment, final double mass){
        super(vector, environment, mass, dimension);
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.weapon = Optional.empty();
    }

    public Player(final EntityList.Characters.Player playerType, final Dimension2D dimension
    		, final SpeedVector2D vector, final Environment environment, final double mass){
        super(vector, environment, mass, dimension);

        this.playerType = playerType;
        
        setPlayerType();
    }

    public Player(final Dimension2D dimension, final SpeedVector2D vector, final Environment environment, final double mass){
        super(vector, environment, mass, dimension);

        setRandomPlayer();
        setPlayerType();

    }

    private void setRandomPlayer() {
        final Random rand = new Random();
        final int max = EntityList.Characters.Player.values().length;

        final int randomPlayer = rand.nextInt(max);
        for(final EntityList.Characters.Player p : EntityList.Characters.Player.values()){
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
                this.weapon = Optional.empty();
                this.health = this.rand.nextDouble() * (MAX - minHealth) + minHealth;
                this.mana = Optional.of(this.rand.nextDouble() * (MAX - minMana) + minMana);
                break;
            case PLAYER2:
                minHealth = 65.0;
                minMana = 70.0;
                this.name = "Player2";
                this.weapon = Optional.empty();
                this.health = this.rand.nextDouble() * (MAX - minHealth)+ minHealth;
                this.mana = Optional.of(this.rand.nextDouble() * (MAX - minMana) + minMana);
                //this.weapon = new WeaponImpl("Knife"); //TODO: add weapon, WeaponFactoryImpl
                break;
            case PLAYER3:
                minHealth = 50.0;
                minMana = 85.0;
                this.name = "Player3";
                this.weapon = Optional.empty();
                this.health = this.rand.nextDouble() * (MAX - minHealth) + minHealth;
                this.mana = Optional.of(this.rand.nextDouble() * (MAX - minMana) + minMana);
                //this.weapon = new WeaponImpl("AK-47"); //TODO: add weapon, WeaponFactoryImpl
                break;
            default:
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
    public void setHealth(final double setHealth) {
        this.health = setHealth;
    }

    @Override
    public Weapon getWeapon() {
        return this.weapon.get();
    }

    @Override
    public void setWeapon(final Weapon weapon) {
        this.weapon = Optional.of(weapon);
    }
    
    public void removeWeapon() {
    	this.weapon = Optional.empty();
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
    public void decreaseMana(final double decreaseValue) {
        if(this.mana.isPresent()){
            this.mana = Optional.of(this.mana.get() - decreaseValue);
        }
    }

    @Override
    public void increaseMana(final double increaseValue) {
        if(this.mana.isPresent()){
            this.mana = Optional.of(this.mana.get() + increaseValue);
        }
    }

    @Override
    public void increaseHealth(final double increaseHealth) {
        this.health += increaseHealth;
    }

    @Override
    public void decreaseHealth(final double decreaseHealth) {
        this.health -= decreaseHealth;
    }

    public EntityList.Characters.Player getPlayerType() {
        return this.playerType;
    }
    
    public boolean hasWeapon() {
    	return this.weapon.isPresent();
    }
    
    /*@Override
    public void updateState() {
    	if(hasWeapon()) {
    		this.weapon.get().setPosition(this.getPosition().get());
    	}
    	
    }
    */
    @Override
    public void updateState() {
    	if (!this.blockedX) {
    		this.moveRight(0);
    	} else {
    		this.moveLeft(1);
    	}
    	if (!this.isAlive()) {
    		this.getGameEnvironment().get().deleteObjByPosition(new ImmutablePosition2Dimpl(this.getPosition().get()));
    	}
    	AppLogger.getAppLogger().info("HEALTH: " + String.valueOf(this.getHealth()));
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
    
    public ScoreSystem getCurrentScore() {
    	return this.currentScore;
    }
    
    public void blockX() {
    	this.blockedX = true;
    }
    
    public void unblockX() {
    	this.blockedX = false;
    }
    
    public boolean hasBlockedX() {
    	return this.blockedX;
    }
} 
