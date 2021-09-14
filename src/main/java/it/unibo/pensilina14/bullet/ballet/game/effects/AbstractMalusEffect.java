package it.unibo.pensilina14.bullet.ballet.game.effects;

public abstract class AbstractMalusEffect implements Effect {

	private final Character target;

	public AbstractMalusEffect(final Character target) {
		this.target = target;
	}

	public final Character getTarget() {
		return this.target;
	}
	
	@Override
	public void applyEffect() {
		// TODO Auto-generated method stub

	}

}
