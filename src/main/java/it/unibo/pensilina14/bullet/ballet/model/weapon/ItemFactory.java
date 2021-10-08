package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface ItemFactory {

    Item createPoisoningItem(Environment environment, SpeedVector2D speedVector);
    Item createHealingItem(Environment environment, SpeedVector2D speedVector);
    Item createDamagingItem(Environment environment, SpeedVector2D speedVector);
    
}
