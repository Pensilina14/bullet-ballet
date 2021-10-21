package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;

public interface Item extends PhysicalObject{
    
        /**
         * 
         * @return ITEM_ID
         */
        Items getItemId();
	/**
	* @return item's effect when it collides with another physical object
	*/
	Effect getEffect();
}
