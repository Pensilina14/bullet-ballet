package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

public interface SpriteFactory {

    Sprite generateStaticObstacle();
    Sprite generateDynamicObstacle();
    Sprite generatePoisonItem();
    Sprite generateDamageItem();
    Sprite generateHeartItem();
    
}
