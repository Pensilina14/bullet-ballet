package it.unibo.pensilina14.bullet.ballet.input;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public class Down implements Command {

	/**
	 * Factor every time Down command is executed
	 * the player moves by.
	 */
	public static final double MOVEMENT_DELTA = 0.1;
	
	@Override
	public void execute(GameState env) {
		final Player player = env.getGameEnvironment().getPlayer().get();
		player.moveDOWN(Down.MOVEMENT_DELTA);
	}

}
