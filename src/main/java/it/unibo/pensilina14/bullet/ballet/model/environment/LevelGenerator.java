package it.unibo.pensilina14.bullet.ballet.model.environment;

/**
 * Manages a contract to which will adhere
 * every class that needs to generate something.
 * An example could be the {@link EnvironmentGenerator}.
 */
public interface Generator {
	/**
	 * This method is implied in generating things.
	 */
	void generate();
}
