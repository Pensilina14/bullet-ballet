package it.unibo.pensilina14.bullet.ballet.model.environment.exceptions;

import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

/**
 * Checked Exception to be thrown when there are no dynamic
 * objects(they all extend {@link AbstractDynamicComponent}) in a given {@link Environment}.
 *
 */
public class NoDynamicObjectsException extends Exception {

	/**
	 * Eclipse generated serial version UID.
	 */
	private static final long serialVersionUID = -2676728451386631326L;
	
    /**
	 * {@link Environment} in which there are no dynamic objects.
	 */
	private final Environment env;
	
	/**
	 * 
	 * @param env is the {@link Environment} taken in consideration
	 * when the exception is thrown.
	 */
	public NoDynamicObjectsException(final Environment env) {
		super();
		this.env = env;
	}
	
	/**
	 * 
	 * @return {@link Environment} with no dynamic objects.
	 */
	public Environment getEnvironment() {
		return this.env;
	}

}
