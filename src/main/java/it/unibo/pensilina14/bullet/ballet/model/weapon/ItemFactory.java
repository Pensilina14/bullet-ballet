package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface ItemFactory {

    /**
     * 
     * @param environment
     * @param speedVector
     * @return an Item with a poisoning {@link Effects}
     */
    Item createPoisoningItem(Environment environment, SpeedVector2D speedVector);
    /**
     * 
     * @param environment
     * @param speedVector
     * @return an Item with an healing {@link Effects}
     */
    Item createHealingItem(Environment environment, SpeedVector2D speedVector);
    /**
     * 
     * @param environment
     * @param speedVector
     * @return an Item with a damaging {@link Effects}
     */
    Item createDamagingItem(Environment environment, SpeedVector2D speedVector);
    Item createCoinItem(Environment environment, SpeedVector2D speedVector);
	Item createChargerItem(Environment environment, SpeedVector2D speedVector);
    
}
