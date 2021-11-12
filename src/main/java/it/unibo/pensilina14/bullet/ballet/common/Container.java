package it.unibo.pensilina14.bullet.ballet.common;

/**
 * Specifies a contract applicable to every class that needs to store informations
 * in a shelves-style mode.
 * This means that implementations of this interface must have a data structure for each shelf.
 */
public interface Container {
	/**
	 * Reveals if a container is empty or not.
	 * 
	 * @return True if container isn't empty. 
	 * 		   False otherwise.
	 */
	boolean isEmpty();
}
