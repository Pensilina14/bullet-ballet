package it.unibo.pensilina14.bullet.ballet.model.environment.events;

import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;

public class CharacterHitsPickupObjEvent implements GameEvent {
	
	private final Characters character;
	private final Item pickupObj;
	
	public CharacterHitsPickupObjEvent(final Characters character, final Item pickable) {
		this.character = character;
		this.pickupObj = pickable;
	}
	
	public final Characters getCharacter() {
		return this.character;
	}
	
	public final Item getPickupObj() {
		return this.pickupObj;
	}
}
