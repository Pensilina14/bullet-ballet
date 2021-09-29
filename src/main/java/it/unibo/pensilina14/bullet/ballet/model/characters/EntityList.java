package it.unibo.pensilina14.bullet.ballet.model.characters;

public enum EntityList {

    ;
    public enum Characters {
        CHARACTER;

        public enum Player{
            PLAYER1, // TODO: mettere i nomi dei players al posto di PLAYER
            PLAYER2,
            PLAYER3
        }

        public enum Enemy{
            ENEMY1, // TODO: mettere i nomi degli enemies al posto di ENEMY
            ENEMY2,
            ENEMY3
        }
    }
    public enum Weapons{
    	GUN("Gun", 10, 4),
    	SHOTGUN("Shotgun", 5, 3),
    	AUTO("Auto", 15, 5);
    	
    	private final String description;
    	private final int limitBullets;
    	private final int limitChargers;
    	
    	Weapons(final String name, final int limBullets, final int limChargers){
    		this.description = name;
    		this.limitBullets = limBullets;
    		this.limitChargers = limChargers;
    	}
    	
    	public String getName() {
    		return this.description;
    	}
    	
    	public int getLimBullets() {
    		return this.limitBullets;
    	}
    	
    	public int getLimChargers() {
    		return this.limitChargers;
    	}
    	
    	@Override
    	public String toString() {
    		return this.description + "/t Bullets: " + this.limitBullets
    				+ "/t Chargers: " + this.limitChargers;
    	}
    }
    
    public enum BulletType {

    	CLASSICAL("Normal bullet", 10),
    	TOXIC("Toxic bullet", 10),
    	SOPORIFIC("Soporific bullet", 10);
	
    	private final String description;
    	private final double damage;
	
    	BulletType(final String name, final double damage) {
    		this.description = name;
    		this.damage = damage;
    	}
	
    	public double damage() {
    		return this.damage;
    	}
	
    	public String description() {
    		return this.description;
    	}
	
    	@Override
    	public String toString() {
    		return this.description + "/t damage: " + this.damage;
    	}
	
    }
}

