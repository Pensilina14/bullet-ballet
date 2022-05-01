package it.unibo.pensilina14.bullet.ballet.input;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public class Left implements Command {

  /** Factor every time Left command is executed the player moves by. */
  public static final double MOVEMENT_DELTA = 3;

  @Override
  public void execute(final GameState env) {
    final Player player = env.getGameEnvironment().getEntityManager().getPlayer().get();
    player.moveLeft(MOVEMENT_DELTA);
  }
}
