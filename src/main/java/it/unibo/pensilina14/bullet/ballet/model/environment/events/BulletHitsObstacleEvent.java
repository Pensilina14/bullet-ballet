package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.obstacle.Obstacle;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;

public final class BulletHitsObstacleEvent implements GameEvent {

  private final Bullet bullet;
  private final Obstacle obstacle;

  public BulletHitsObstacleEvent(final Bullet bullet, final Obstacle obstacle) {
    this.bullet = bullet;
    this.obstacle = obstacle;
  }

  public Bullet getBullet() {
    return bullet;
  }

  public Obstacle getObstacle() {
    return obstacle;
  }
}
