package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;

public class EnemyHitsPlatformEvent implements GameEvent {
  private final Enemy enemy;
  private final Platform platform;

  public EnemyHitsPlatformEvent(final Enemy player, final Platform platform) {
    this.enemy = player;
    this.platform = platform;
  }

  public final Enemy getEnemy() {
    return this.enemy;
  }

  public final Platform getPlatform() {
    return this.platform;
  }
}
