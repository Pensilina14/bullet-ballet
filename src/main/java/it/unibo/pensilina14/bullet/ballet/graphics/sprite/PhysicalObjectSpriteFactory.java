package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;

public interface PhysicalObjectSpriteFactory {

    PhysicalObjectSprite generateDynamicObstacleSprite(final double x, final double y) throws IOException;
    PhysicalObjectSprite generateStaticObstacleSprite(final double x, final double y) throws IOException;
    PhysicalObjectSprite generateHealingItemSprite(final double x, final double y) throws IOException;
    PhysicalObjectSprite generateDamagingItemSprite(final double x, final double y) throws IOException;
    PhysicalObjectSprite generatePoisoningItemSprite(final double x, final double y) throws IOException;
    
}
