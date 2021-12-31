package it.unibo.pensilina14.bullet.ballet.input;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public class Down implements Command {

	/**
	 * Factor every time Down command is executed
	 * the player moves by.
	 */
	public static final double MOVEMENT_DELTA = 3;
	private final double movement;
	
	public Down() {
		this.movement = MOVEMENT_DELTA;
	}
	
	public Down(final double movement) {
		this.movement = movement;
	}
	
	@Override
	public void execute(final GameState env) {
		final Player player = env.getGameEnvironment().getPlayer().get();
		player.moveDown(this.movement);
	}

}
