package it.unibo.pensilina14.bullet.ballet.input;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public class Up implements Command {

	/**
	 * Factor every time Up command is executed
	 * the player moves by.
	 */
	public static final double MOVEMENT_DELTA = 70;
	private final double movement;
	
	public Up() {
		this.movement = MOVEMENT_DELTA;
	}
	
	public Up(final double movement) {
		this.movement = movement;
	}
	
	@Override
	public final void execute(final GameState env) {
		final Player player = env.getGameEnvironment().getEntityManager().getPlayer().get();
		if (player.hasLanded()) {
			player.moveUp(this.movement);
		}
	}

}
