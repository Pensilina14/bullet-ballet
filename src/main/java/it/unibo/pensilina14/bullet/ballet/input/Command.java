package it.unibo.pensilina14.bullet.ballet.input;

import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;

public interface Command {

    void execute(GameState env);
	
}
