package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

public enum Images {

    POISONING_ITEM("/res/assets/sprites/items/poison.png"),
    HEALING_ITEM("/res/assets/sprites/items/heart.png"),
    DAMAGING_ITEM("/res/assets/sprites/items/skull_coin.jpeg"),
    STATIC_OBSTACLE("/res/assets/sprites/obstacles/static_obstacle.png"),
    DYNAMIC_OBSTACLE("/res/assets/sprites/obstacles/dynamicObstacle.png");
    
    private final String fileName;
    
    Images(final String fileName) {
        this.fileName = fileName;
    }

    /* 
     * @see java.lang.Enum#toString()
     */
    
    @Override
    public String toString() {
        return this.fileName;
    }
    
}
