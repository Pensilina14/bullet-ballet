package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.weapon.PickupItem;

public class PlayerHitsItemEvent implements GameEvent {

  private final Player player;
  private final PickupItem item;

  public PlayerHitsItemEvent(final Player player, final PickupItem item) {
    this.player = player;
    this.item = item;
  }

  public final Player getPlayer() {
    return this.player;
  }

  public final PickupItem getItem() {
    return this.item;
  }
}
