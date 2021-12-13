package it.unibo.pensilina14.bullet.ballet.input;

import it.unibo.pensilina14.bullet.ballet.menu.controller.Frames;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoader;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoaderImpl;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public class Esc implements Command {

	@Override
	public void execute(GameState env) {
		// TODO Auto-generated method stub
		// env.interrupt();
		final PageLoader loader = new PageLoaderImpl();
		//loader.goToSelectedPageOnInput(Frames.PAUSEMENU, null);

	}

}
