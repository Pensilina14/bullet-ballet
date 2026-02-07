package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;

public final class BulletHitsPlayerEvent implements GameEvent {

  private final Bullet bullet;
  private final Player player;

  public BulletHitsPlayerEvent(final Bullet bullet, final Player player) {
    this.bullet = bullet;
    this.player = player;
  }

  public Bullet getBullet() {
    return this.bullet;
  }

  public Player getPlayer() {
    return this.player;
  }
}
