package it.unibo.pensilina14.bullet.ballet.game.effects;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;

import it.unibo.pensilina14.bullet.ballet.characters.Characters;
import it.unibo.pensilina14.bullet.ballet.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.weapon.ITEM_ID;
import it.unibo.pensilina14.bullet.ballet.weapon.Item;
import it.unibo.pensilina14.bullet.ballet.weapon.StaticPickUpItem;

public class EffectTest {
	/*
	 * TESTS CONTANTS
	 */
	private static final double HEALTH = 100.0;
	private static final double MANA = 150.0;
	private static final double CHARACTER_MASS = 85.0;
	private static final double ITEM_MASS = 3.5;
	private static final double DAMAGE = 10.0;
	/*
	 * Note:
	 * 	This test method does NOT take in consideration collisions a
	 *  and environmental aspects as dimension and position.
	 */
	@Test
	public void testApplyEffect() {
		/*
		 * DECLARATION
		 */
		final Environment env = new GameEnvironment();
		final Characters enemy = new Enemy("testEnemy00", HEALTH, Optional.ofNullable(MANA), 
			    new Dimension2Dimpl(25, 5), new MutablePosition2Dimpl(10, 30), env, CHARACTER_MASS);
		final Item poisonItem = new StaticPickUpItem(new Dimension2Dimpl(25, 5),
				new MutablePosition2Dimpl(150, 50), env, ITEM_MASS, ITEM_ID.POISON, e -> e.setHealth(e.getHealth() - DAMAGE));
		final Item heartItem = new StaticPickUpItem(new Dimension2Dimpl(30, 30),
				new MutablePosition2Dimpl(150, 300), env, ITEM_MASS, ITEM_ID.HEART, e -> e.setHealth(e.getHealth() + DAMAGE));
		/*
		 * ELABORATION
		 */
		poisonItem.getEffect().applyEffect(enemy);
		poisonItem.getEffect().applyEffect(enemy);
		heartItem.getEffect().applyEffect(enemy);
		/*
		 * ASSERTIONS
		 */
		assertEquals(enemy.getHealth(), 90.0, 0);
	}

}
