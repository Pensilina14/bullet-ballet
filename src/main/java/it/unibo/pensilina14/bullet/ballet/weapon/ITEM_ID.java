package it.unibo.pensilina14.bullet.ballet.weapon;

public enum ITEM_ID {

    HEART("heart"),
    WEAPON("weapon"),
    POISON("poison");
    
    private final String item_id;
    
    ITEM_ID(final String item_id) {
        this.item_id = item_id; 
    }
    
    @Override
    public String toString() {
        return this.item_id;  
    }
    
}
