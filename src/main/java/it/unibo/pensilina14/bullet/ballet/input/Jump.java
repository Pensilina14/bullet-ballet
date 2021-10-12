package it.unibo.pensilina14.bullet.ballet.input;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public class Jump implements Command {

	@Override
	public final void execute(final GameState env) {
		final Player player = env.getGameEnvironment().getPlayer().get();
		player.jump();
	}

}
