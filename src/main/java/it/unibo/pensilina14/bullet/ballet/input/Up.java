package it.unibo.pensilina14.bullet.ballet.input;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public class Up implements Command {

	/**
	 * Factor every time Up command is executed
	 * the player moves by.
	 */
	public static final double MOVEMENT_DELTA = 0.1;
	
	@Override
	public final void execute(final GameState env) {
		final Player player = env.getGameEnvironment().getPlayer().get();
		player.moveUP(Up.MOVEMENT_DELTA);
	}

}
