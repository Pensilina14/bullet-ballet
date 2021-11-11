package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactory;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactoryImpl;

public class PhysicalObjectSpriteFactoryImpl implements PhysicalObjectSpriteFactory{

    private static final double SPEED = 1.5;
    private final ObstacleFactory obstacleFact = new ObstacleFactoryImpl();
    private final ItemFactory itemFact = new ItemFactoryImpl();
    private final GameState gameState;

    public PhysicalObjectSpriteFactoryImpl(final GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public PhysicalObjectSprite generateDynamicObstacleSprite(final MutablePosition2D position) throws IOException {
        final PhysicalObject dynamicObstacle = obstacleFact
                .createDynamicObstacle(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(position, SPEED));
        return new PhysicalObjectSprite(Images.DYNAMIC_OBSTACLE, position, dynamicObstacle);
    }

    @Override
    public PhysicalObjectSprite generateStaticObstacleSprite(final MutablePosition2D position) throws IOException {
        final PhysicalObject staticObstacle = obstacleFact
                .createStaticObstacle(this.gameState.getGameEnvironment(), position);
        return new PhysicalObjectSprite(Images.STATIC_OBSTACLE, position, staticObstacle);
    }

    @Override
    public PhysicalObjectSprite generateHealingItemSprite(final MutablePosition2D position) throws IOException{
        final PhysicalObject item = itemFact
                .createHealingItem(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(position, SPEED));
        return new PhysicalObjectSprite(Images.HEALING_ITEM, position, item);
    }

    @Override
    public PhysicalObjectSprite generateDamagingItemSprite(final MutablePosition2D position) throws IOException{
        final PhysicalObject item = itemFact
                .createDamagingItem(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(position, SPEED));
        return new PhysicalObjectSprite(Images.DAMAGING_ITEM, position, item);
    }

    @Override
    public PhysicalObjectSprite generatePoisoningItemSprite(final MutablePosition2D position) throws IOException{
        final PhysicalObject item = itemFact
                .createPoisoningItem(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(position, SPEED));
        return new PhysicalObjectSprite(Images.POISONING_ITEM, position, item);
    }
}
