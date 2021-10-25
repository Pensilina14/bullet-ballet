package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.io.IOException;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.MapScene;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactory;
import it.unibo.pensilina14.bullet.ballet.model.obstacle.ObstacleFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactory;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ItemFactoryImpl;

public class PhysicalObjectSpriteFactoryImpl implements PhysicalObjectSpriteFactory{

    private static final double SPEED = 1.5;
    private final ObstacleFactory obstacleFact = new ObstacleFactoryImpl();
    private final ItemFactory itemFact = new ItemFactoryImpl();
    private final MapScene mapScene;
    private final GameState gameState;

    public PhysicalObjectSpriteFactoryImpl(final MapScene scene, final GameState gameState) {
        this.mapScene = scene;
        this.gameState = gameState;
    }

    @Override
    public PhysicalObjectSprite generateDynamicObstacleSprite(final double x, final double y) throws IOException {
        final PhysicalObject dynamicObstacle = obstacleFact
                .createDynamicObstacle(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(new MutablePosition2Dimpl(x, y), SPEED));
        return new PhysicalObjectSprite(Images.DYNAMIC_OBSTACLE, x, y, dynamicObstacle);
    }

    @Override
    public PhysicalObjectSprite generateStaticObstacleSprite(final double x, final double y) throws IOException {
        final PhysicalObject staticObstacle = obstacleFact
                .createStaticObstacle(this.gameState.getGameEnvironment(), new MutablePosition2Dimpl(x, y));
        return new PhysicalObjectSprite(Images.STATIC_OBSTACLE, x, y, staticObstacle);
    }

    @Override
    public PhysicalObjectSprite generateHealingItemSprite(final double x, final double y) throws IOException{
        final PhysicalObject staticItem = itemFact
                .createHealingItem(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(new MutablePosition2Dimpl(x, y), SPEED));
        return new PhysicalObjectSprite(Images.HEALING_ITEM, x, y, staticItem);
    }

    @Override
    public PhysicalObjectSprite generateDamagingItemSprite(final double x, final double y) throws IOException{
        final PhysicalObject staticItem = itemFact
                .createDamagingItem(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(new MutablePosition2Dimpl(x, y), SPEED));
        return new PhysicalObjectSprite(Images.DAMAGING_ITEM, x, y, staticItem);
    }

    @Override
    public PhysicalObjectSprite generatePoisoningItemSprite(final double x, final double y) throws IOException{
        final PhysicalObject staticItem = itemFact
                .createPoisoningItem(this.gameState.getGameEnvironment(), new SpeedVector2DImpl(new MutablePosition2Dimpl(x, y), SPEED));
        return new PhysicalObjectSprite(Images.POISONING_ITEM, x, y, staticItem);
    }
}
