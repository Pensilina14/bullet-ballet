package it.unibo.pensilina14.bullet.ballet.common;

/**
 * Specifies a contract applicable to every class that needs to store informations
 * in a shelves-style mode.
 * This means that implementations of this interface must have a data structure for each shelf.
 * 
 * @param <X> is the type of the container elements.
 */

public interface Container<X> {
	/**
	 * Reveals if a container is empty or not.
	 * 
	 * @return True if container isn't empty. 
	 * 		   False otherwise.
	 */
	boolean isEmpty();
}
