package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

public class SpriteFactoryImpl implements SpriteFactory{
    
    public Sprite generateStaticObstacle() {
         final Sprite staticObstacleSprite = new Sprite(0, 0);
         staticObstacleSprite.setSpriteImage(Sprites.SOBSTACLE.toString());
         return staticObstacleSprite;
    }
    
    
    public Sprite generateDynamicObstacle() {
        final Sprite dynamicObstacle = new Sprite(0, 0);
        dynamicObstacle.setSpriteImage(Sprites.DOBSTACLE.toString());
        return dynamicObstacle;
    }
    
    public Sprite generatePoisonItem() {
        final Sprite poison = new Sprite(0, 0);
        poison.setSpriteImage(Sprites.POISON.toString());
        return poison;
    }
    
    public Sprite generateDamageItem() {
        final Sprite damage = new Sprite(0, 0);
        damage.setSpriteImage(Sprites.SKULL_COIN.toString());
        return damage;
    }
    
    public Sprite generateHeartItem() {
        final Sprite heart = new Sprite(0, 0);
        heart.setSpriteImage(Sprites.HEART.toString());
        return heart;
    }

}
