package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;

public interface Item {
    
        /**
         * 
         * @return ITEM_ID
         */
        ITEM_ID getItemId();
	/**
	* @return item's effect when it collides with another physical object
	*/
	Effect getEffect();
}
