package it.unibo.pensilina14.bullet.ballet.model.effects.test;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.Test;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ITEM_ID;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
import it.unibo.pensilina14.bullet.ballet.model.weapon.StaticPickUpItem;

public class EffectTest {
	/*
	 * TESTS CONTANTS
	 */
	private static final double HEALTH = 100.0;
	private static final double MANA = 150.0;
	private static final double CHARACTER_MASS = 85.0;
	private static final double ITEM_MASS = 3.5;
	private static final double DAMAGE = 10.0;
	private static final double MANA_DAMAGE = 1.0;
	private static final long ITERATIONS = 10;
	/*
	 * Note:
	 * 	This test method does NOT take in consideration collisions
	 *  and environmental aspects such as dimension and position.
	 */
	@Test
	public void testApplySimpleEffect() {
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
		assertEquals(enemy.getHealth(), (HEALTH - DAMAGE * 2) + DAMAGE, 0);
	}
	
	@Test
	public void testApplyMultipleEffect() {
		/*
		 * DECLARATION
		 */
		final Environment env = new GameEnvironment();
		final Characters enemy = new Enemy("testEnemy00", HEALTH, Optional.ofNullable(MANA), 
			    new Dimension2Dimpl(25, 5), new MutablePosition2Dimpl(10, 30), env, CHARACTER_MASS);
		final Item poisonItem = new StaticPickUpItem(new Dimension2Dimpl(25, 5),
				new MutablePosition2Dimpl(150, 50), env, ITEM_MASS, ITEM_ID.POISON, e -> {
					e.setHealth(e.getHealth() - DAMAGE);
					IntStream.iterate(0, x -> x + 1).limit(ITERATIONS)
						.forEach(x -> e.decreaseMana(MANA_DAMAGE));
				});
		final Item heartItem = new StaticPickUpItem(new Dimension2Dimpl(30, 30),
				new MutablePosition2Dimpl(150, 300), env, ITEM_MASS, ITEM_ID.HEART, e -> {
					e.setHealth(e.getHealth() + DAMAGE);
					IntStream.iterate(0, x -> x + 1).limit(ITERATIONS)
						.forEach(x -> e.increaseMana(MANA_DAMAGE));
				});
		/*
		 * ELABORATION
		 */
		poisonItem.getEffect().applyEffect(enemy);
		poisonItem.getEffect().applyEffect(enemy);
		heartItem.getEffect().applyEffect(enemy);
		final double testHealth1 = enemy.getHealth();
		final double testMana1 = enemy.getMana().get();

		heartItem.getEffect().applyEffect(enemy);
		poisonItem.getEffect().applyEffect(enemy);
		poisonItem.getEffect().applyEffect(enemy);
		poisonItem.getEffect().applyEffect(enemy);
		final double testHealth2 = enemy.getHealth();
		final double testMana2 = enemy.getMana().get();

		heartItem.getEffect().applyEffect(enemy);
		heartItem.getEffect().applyEffect(enemy);
		heartItem.getEffect().applyEffect(enemy);
		final double testHealth3 = enemy.getHealth();
		final double testMana3 = enemy.getMana().get();
		/*
		 * 	ASSERTIONS
		 */
		assertEquals(HEALTH - (DAMAGE * 2) + DAMAGE, testHealth1, 0);
		assertEquals(MANA - this.totalManaDamage(2) + this.totalManaDamage(1), testMana1, 0);

		assertEquals(testHealth1 + DAMAGE - (DAMAGE * 3), testHealth2, 0);
		assertEquals(testMana1 + this.totalManaDamage(1) - this.totalManaDamage(3), testMana2, 0);

		assertEquals(testHealth2 + DAMAGE * 3, testHealth3, 0);
		assertEquals(testMana2 + this.totalManaDamage(3), testMana3, 0);
	}
	
	private double totalManaDamage(final int n) {
		return MANA_DAMAGE * ITERATIONS * n;
	}
}
