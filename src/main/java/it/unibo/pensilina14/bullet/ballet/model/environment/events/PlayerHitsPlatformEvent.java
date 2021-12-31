package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;

public class PlayerHitsPlatformEvent implements GameEvent {
    private final Player player;
	private final Platform platform;
	
	public PlayerHitsPlatformEvent(final Player player, final Platform platform) {
		this.player = player;
		this.platform = platform;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * @return the obstacle
	 */
	public Platform getPlatform() {
		return this.platform;
	}
}
