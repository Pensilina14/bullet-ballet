package it.unibo.pensilina14.bullet.ballet.model.weapon;

public enum ITEM_ID {

    HEART("heart"),
    WEAPON("weapon"),
    POISON("poison");
    
    private final String itemID;
    
    ITEM_ID(final String itemID) {
        this.itemID = itemID; 
    }
    
    @Override
    public String toString() {
        return this.itemID;  
    }
    
}
