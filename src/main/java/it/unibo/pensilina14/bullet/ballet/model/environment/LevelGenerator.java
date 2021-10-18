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
	 * @return generated level's width.
	 */
	int getLevelWidth();
	/**
	 * 
	 * @return platform size.
	 */
	int getPlatformSize();
}
