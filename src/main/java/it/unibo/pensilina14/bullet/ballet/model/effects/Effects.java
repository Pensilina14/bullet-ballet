package it.unibo.pensilina14.bullet.ballet.model.effects;
/**
 * Describes {@link Effect} and relative delta relationship.
 */
public enum Effects {
	/**
	 * This {@link Effect} features an increase in health of a certain {@link Characters}.
	 */
	HEALTHY("healthy", Deltas.LIGHT),
	/**
	 * This {@link Effect} features a decrease in health of a certain {@link Characters}.
	 */
	DAMAGE("damage", Deltas.LIGHT),
	/**
	 * This {@link Effect} features a periodical decrease in health of a certain {@link Characters}.
	 */
	POISON("poison", Deltas.LIGHT);
	
	private final String name;
	/*
	 * Not final to give flexibility to each Effect.
	 */
	private Deltas delta;
	
	/**
	 * Simple constructor capable of instancing an element of this enum.
	 * 
	 * @param name
	 * @param delta
	 */
	Effects(final String name, final Deltas delta) {
		this.name = name;
		this.delta = delta;
	}
	
	/**
	 * @return the name of the {@link Effect}
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the delta, the factor by which health is increased/decreased.
	 */
	public Deltas getDelta() {
		return delta;
	}
	
	/**
	 * Sets the delta of the {@link Effect} to a given value.
	 * This allow the elements of this enum to be more flexible, in order to
	 * not create lots of elements and playing with the given ones.
	 * We could say that delta is subjective.
	 * 
	 * @param delta
	 */
	public void setDelta(final Deltas delta) {
		this.delta = delta;
	}
	
	/**
	 * Inner enum that provides all the possible deltas.
	 * A delta is a factor that determines and defines health increase or decrease.
	 * Note that deltas are constants, the effects in the main enum will be flexible 
	 * to this point.
	 */
	private enum Deltas {

		LIGHT(10),
		MODERATE(20), 
		HEAVY(40);

		private final double value;

		Deltas(final double value) {
			this.value = value;
		}

		public final double getValue() {
			return this.value;
		}
	}
}
