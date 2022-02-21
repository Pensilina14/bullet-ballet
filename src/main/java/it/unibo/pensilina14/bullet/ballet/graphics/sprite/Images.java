package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import it.unibo.pensilina14.bullet.ballet.graphics.map.Coins;

public enum Images {

    BULLET("Normal", "res/assets/sprites/bullets/Bullet.png"),
    POISONING_ITEM("poison", "res/assets/sprites/items/poison.png"),
    HEALING_ITEM("heart","res/assets/sprites/items/heart.png"),
    DAMAGING_ITEM("damage", "res/assets/sprites/items/snail.png"),
    BUNNY("bunny", "res/assets/sprites/obstacles/Jump.png"),
    WUT("dynamic obstacle", "res/assets/sprites/obstacles/dynamicObstacle.png"),
	COIN("coin", Coins.getRandomCoinPath()),
	GUN("Gun", "res/assets/sprites/weapons/gun.png"),
	SHOTGUN("Shotgun", "res/assets/sprites/weapons/shotgun.png"),
	AUTO("Auto", "res/assets/sprites/weapons/auto.png");
    
    private final String fileName;
    private final String objectName;
    
    Images(final String objectName, final String fileName) {
        this.objectName = objectName;
    	this.fileName = fileName;
    }
    
    public String getObjectName() {
    	return this.objectName;
    }
    /* 
     * @see java.lang.Enum#toString()
     */
    
    public String getFileName() {
    	return this.fileName;
    }
    
    @Override
    public String toString() {
        return this.objectName + "\t" + this.fileName;
    }
}
