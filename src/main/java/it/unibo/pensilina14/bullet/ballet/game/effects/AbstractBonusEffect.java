package it.unibo.pensilina14.bullet.ballet.game.effects;

public abstract class AbstractBonusEffect implements Effect {

	private final Character target;

	public AbstractBonusEffect(final Character target) {
		this.target = target;
	}

	public final Character getTarget() {
		return this.target;
	}

	@Override
	public abstract void applyEffect();
}
