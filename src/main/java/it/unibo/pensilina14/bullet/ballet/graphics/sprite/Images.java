package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

public enum Images {

    POISONING_ITEM("poison", "res/assets/sprites/items/poison.png"),
    HEALING_ITEM("heart","res/assets/sprites/items/heart.png"),
    DAMAGING_ITEM("damage", "res/assets/sprites/items/snail.png"),
    STATIC_OBSTACLE("static obstacle", "res/assets/sprites/obstacles/static_obstacle.png"),
    DYNAMIC_OBSTACLE("dynamic obstacle", "res/assets/sprites/obstacles/dynamicObstacle.png");
    
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
