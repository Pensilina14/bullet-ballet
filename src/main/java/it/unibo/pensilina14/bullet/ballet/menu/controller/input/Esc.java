package it.unibo.pensilina14.bullet.ballet.input;

import it.unibo.pensilina14.bullet.ballet.menu.controller.Frames;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoader;
import it.unibo.pensilina14.bullet.ballet.menu.controller.PageLoaderImpl;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

import java.io.IOException;

public class Esc implements Command {

  private final PageLoader loader;

  public Esc() {
    this.loader = new PageLoaderImpl();
  }

  @Override
  public void execute(final GameState env) {
    try {
      loader.goToSelectedPageOnInput(Frames.PAUSEMENU);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
