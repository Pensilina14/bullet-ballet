package it.unibo.pensilina14.bullet.ballet.input;

import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;

public class Space implements Command {

	public Space() {}
	
	@Override
	public void execute(final GameState env) {
		final Weapon weapon = env.getGameEnvironment().getEntityManager().getPlayer().get().getWeapon();
		weapon.decreaseAmmo();		
	}

}
