package it.unibo.pensilina14.bullet.ballet.core;

import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface Command {

    void execute(Environment env);
	
}
