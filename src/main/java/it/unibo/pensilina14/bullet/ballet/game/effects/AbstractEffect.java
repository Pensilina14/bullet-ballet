package it.unibo.pensilina14.bullet.ballet.game.effects;

/**
 * This abstract class serves as a template
 * for several default implementations of {@link Effect} 
 * interface that include POSITIVE consequences after 
 * the method {@link Effect#applyEffect()}.
 */
public abstract class AbstractEffect implements Effect {
	/**
	 * Every effect has a target {@link Character}, referred to by this field.
	 */
	private final Character target;

	/**
	 * Constructor: asks for a target, which in this case is a {@link Character} 
	 * object and sets it as field {@link #target} value.
	 * 
	 * @param target
	 */
	public AbstractEffect(final Character target) {
		this.target = target;
	}

	/**
	 * Returns effect's target.
	 * 
	 * @return {@link #target} 
	 */
	public final Character getTarget() {
		return this.target;
	}

	/**
	 * This method must be implemented in inheriting class.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public abstract void applyEffect();
}
