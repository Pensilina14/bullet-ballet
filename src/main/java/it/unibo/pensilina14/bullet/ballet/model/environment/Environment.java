package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.EntityManager;
import it.unibo.pensilina14.bullet.ballet.common.ImmutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;

/**
 * This interface wraps all the virtual game world and permits interaction
 * between objects inside a virtual map.
 */
public interface Environment {
	/**
	 * @return environment's gravity.
	 */
	double getGravity();
	
	/**
	 * @return game environment dimension: height and width.
	 */
	Dimension2D getDimension();

    /**
	 * Returns an {@link EntityManager}.
	 * 
	 * @return entity manager of the implementing class.
	 */
	EntityManager getEntityManager();

	/**
	 * @param targetPos of the object to be deleted.
	 * 
	 * The {@link PhysicalObject} to be deleted is first searched and then removed.
	 * 
	 * @return true if a {@link PhysicalObject} has been deleted.
	 * 		   false otherwise. 
	 * 		   Could be false if there was no object at position..
	 */
	boolean deleteObjByPosition(ImmutablePosition2D targetPos);
	
	/**
	 * This must recall every {@link AbstractDynamicComponent}'s {@link AbstractDynamicComponent#updateState} method,
	 * in order to update the whole environment. 
	 */
	void updateState();
	
	/**
	 * Sets the event listener for the environment, so it
	 * can notify events to the listener aka the controller.
	 * 
	 * @param listener to be set
	 */
	void setEventListener(GameEventListener listener);
	
	/**
	 * Provides important constants for gravity representation.
	 * EARTH and MOON's provided.
	 */
	enum GravityConstants {
		TEST(1.0),
		EARTH(9.81),
		MOON(6.673);

		private final double value;

		GravityConstants(final double value) {
			this.value = value;
		}

		public double getValue() {
			return this.value;
		}
	}

}
