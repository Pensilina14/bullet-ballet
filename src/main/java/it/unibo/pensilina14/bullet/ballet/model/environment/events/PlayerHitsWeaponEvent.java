package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;
import it.unibo.pensilina14.bullet.ballet.model.weapon.WeaponImpl;

public class PlayerHitsWeaponEvent implements GameEvent {
	
	private final Player player;
	private final WeaponImpl weapon;
	
	public PlayerHitsWeaponEvent(final Player player, final WeaponImpl weapon) {
		this.player = player;
		this.weapon = weapon;
	}
	
	public final Player getPlayer() {
		return this.player;
	}
	
	public final WeaponImpl getWeapon() {
		return this.weapon;
	}
}
