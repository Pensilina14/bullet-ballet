package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public interface ItemFactory {

    /**
     * 
     * @param environment
     * @param speedVector
     * @return an Item with a poisoning {@link Effects}
     */
    Item createPoisoningItem(GameState gameState, SpeedVector2D speedVector);
    /**
     * 
     * @param environment
     * @param speedVector
     * @return an Item with an healing {@link Effects}
     */
    Item createHealingItem(GameState environment, SpeedVector2D speedVector);
    /**
     * 
     * @param environment
     * @param speedVector
     * @return an Item with a damaging {@link Effects}
     */
    Item createDamagingItem(GameState environment, SpeedVector2D speedVector);
    
}
