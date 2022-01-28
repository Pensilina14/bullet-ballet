package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;

public interface PhysicalObjectSpriteFactory {

    PhysicalObjectSprite generateDynamicObstacleSprite(final MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generateStaticObstacleSprite(final MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generateHealingItemSprite(final MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generateDamagingItemSprite(final MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generatePoisoningItemSprite(final MutablePosition2D position) throws IOException;
    PhysicalObjectSprite generateCoinItemSprite(final MutablePosition2D position) throws IOException;
    
}
