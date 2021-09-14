package it.unibo.pensilina14.bullet.ballet.game.effects;

/**
 * This abstract class serves as a template
 * for all the possible implementations of {@link Effect} 
 * interface that include NEGATIVE consequences after 
 * the method {@link Effect#applyEffect()}.
 */
public abstract class AbstractMalusEffect implements Effect {
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
	public AbstractMalusEffect(final Character target) {
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
	public void applyEffect() {
		// TODO Auto-generated method stub

	}

}
