package it.unibo.pensilina14.bullet.ballet.core;

public interface Engine {
	/**
	 * Engine setup.
	 */
	void setup();
	/**
	 * Engine mainLoop that follows this execution processInput, updateGame, render.
	 */
	void mainLoop();
	/**
	 * Processes input commands.
	 */
	void processInput();
	/**
	 * Updates {@link Environment}.
	 */
	void updateGame();
	/**
	 * Renders sprites and map in a GUI.
	 */
	void render();
	/**
	 * Starts the engine timer.
	 */
	void start();
	/**
	 * Stops the engine timer.
	 */
	void stop();
}
