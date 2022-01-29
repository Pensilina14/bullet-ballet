package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;

public class GameOverEvent implements GameEvent{
	
	private final Player player;

	public GameOverEvent(final Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
	
}
