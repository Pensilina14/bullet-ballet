package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class PickupItem extends GameEntity implements Item {
	
	private final Items id;
	private final Effect effect;
	
	public PickupItem(final SpeedVector2D speedVector, final Environment gameEnvironment, final double mass, final Dimension2D dimension,
			final Items id, final Effect effect) {
		super(speedVector, gameEnvironment, mass, dimension);
		this.id = id;
		this.effect = effect;
	}

	@Override
	public Items getItemId() {
		return this.id;
	}

	@Override
	public Effect getEffect() {
		return this.effect;
	}

	@Override
	public void moveToRandomPosition() {
	}
	
	
	
}
