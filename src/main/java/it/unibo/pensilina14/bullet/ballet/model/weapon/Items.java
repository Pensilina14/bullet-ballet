package it.unibo.pensilina14.bullet.ballet.model.weapon;

public enum Items {

    HEART("heart"),
    WEAPON("weapon"),
    DAMAGE("damage"),
    POISON("poison"),
    COIN("coin"),
    CHARGER("charger");
    
    private final String itemID;
    
    Items(final String itemID) {
        this.itemID = itemID; 
    }
    
    @Override
    public String toString() {
        return this.itemID;  
    }
    
}
