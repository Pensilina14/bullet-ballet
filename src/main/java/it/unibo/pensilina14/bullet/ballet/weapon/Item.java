package it.unibo.pensilina14.bullet.ballet.weapon;

import it.unibo.pensilina14.bullet.ballet.game.effects.Effect;

public interface Item {
	/*
	* @return item's effect when it collides with another physical object
	*/
	Effect getEffect();
}
