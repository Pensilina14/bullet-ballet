package it.unibo.pensilina14.bullet.ballet.model.effects;
/**
 * 
 *
 */
public enum Effects {
	/**
	 * 
	 */
	HEALTHY("healthy", Deltas.LIGHT),
	/**
	 * 
	 */
	DAMAGE("damage", Deltas.LIGHT),
	/**
	 * 
	 */
	POISON("poison", Deltas.LIGHT);
	
	private final String name;
	/*
	 * Not final to give flexibility to each Effect
	 */
	private Deltas delta;
	
	Effects(final String name, final Deltas delta) {
		this.name = name;
		this.delta = delta;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the delta
	 */
	public Deltas getDelta() {
		return delta;
	}
	
	public void setDelta(final Deltas delta) {
		this.delta = delta;
	}

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
