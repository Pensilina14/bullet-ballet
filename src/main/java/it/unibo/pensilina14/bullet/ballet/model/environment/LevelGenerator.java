package it.unibo.pensilina14.bullet.ballet.model.environment;

/**
 * Manages a contract to which will adhere
 * every class that needs to generate levels and work with them.
 * An example could be the {@link EnvironmentGenerator}.
 */
public interface LevelGenerator {
	/**
	 * This method is implied in generating things.
	 */
	void generate();
	/**
	 * This method sets the {@link Environment} in which
	 * the world will be generated.
	 * 
	 * @param env {@link Environment} to be set.
	 */
	void setEnvironment(Environment env);
	/**
	 * @return generated level's width.
	 */
	double getLevelWidth();
	/**
	 * @return generated level's height.
	 */
	double getLevelHeight();
	/**
	 * 
	 * @return platform size.
	 */
	int getPlatformSize();
}
