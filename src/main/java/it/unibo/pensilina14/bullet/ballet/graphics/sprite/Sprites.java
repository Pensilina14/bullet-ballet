package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

public enum Sprites {
  ENEMY("\"res/assets/sprites/characters/enemies/EnemyCharacterTest.png\""),
  HEART("\"res/assets/sprites/items/heart.png\""),
  POISON("\"res/assets/sprites/items/poison.png\""),
  SKULL_COIN("\"res/assets/sprites/items/skull_coin.png\""),
  DOBSTACLE("\"res/assets/sprites/obstacles/dynamicObstacle.png\""),
  SOBSTACLE("\"res/assets/sprites/obstacles/static_obstacle.png\"");

  private final String fileName;

  Sprites(final String fileName) {
    this.fileName = fileName;
  }

  @Override
  public String toString() {
    return fileName;
  }
}
