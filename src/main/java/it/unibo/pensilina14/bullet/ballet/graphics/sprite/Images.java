package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import it.unibo.pensilina14.bullet.ballet.graphics.map.Coins;

public enum Images {

    POISONING_ITEM("poison", "res/assets/sprites/items/poison.png"),
    HEALING_ITEM("heart","res/assets/sprites/items/heart.png"),
    DAMAGING_ITEM("damage", "res/assets/sprites/items/snail.png"),
    STATIC_OBSTACLE("static obstacle", "res/assets/sprites/obstacles/obstacle.png"),
    DYNAMIC_OBSTACLE("dynamic obstacle", "res/assets/sprites/obstacles/dynamicObstacle.png"),
	COIN("dynamic obstacle", Coins.getRandomCoinPath()); // non so se va bene così comunque per il momento provo.
    
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
