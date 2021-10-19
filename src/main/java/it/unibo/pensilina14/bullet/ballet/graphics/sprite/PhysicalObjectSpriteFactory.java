package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;

public interface PhysicalObjectSpriteFactory {

    PhysicalObjectSprite generateDynamicObstacleSprite(final int x, final int y) throws IOException;
    PhysicalObjectSprite generateStaticObstacleSprite(final int x, final int y) throws IOException;
    PhysicalObjectSprite generateHealingItemSprite(final int x, final int y) throws IOException;
    PhysicalObjectSprite generateDamagingItemSprite(final int x, final int y) throws IOException;
    PhysicalObjectSprite generatePoisoningItemSprite(final int x, final int y) throws IOException;
    
}
