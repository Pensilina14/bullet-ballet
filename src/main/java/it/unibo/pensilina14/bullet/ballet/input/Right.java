package it.unibo.pensilina14.bullet.ballet.input;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public class Right implements Command {

	/**
	 * Factor every time Right command is executed
	 * the player moves by.
	 */
	public static final int MOVEMENT_DELTA = 5;
	
	@Override
	public final void execute(final GameState env) {
		final Player player = env.getGameEnvironment().getPlayer().get();
		player.moveRIGHT(Right.MOVEMENT_DELTA);
	}

}
