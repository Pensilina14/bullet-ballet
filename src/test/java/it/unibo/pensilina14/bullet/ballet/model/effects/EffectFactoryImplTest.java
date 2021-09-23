package it.unibo.pensilina14.bullet.ballet.model.effects;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.Characters;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.ITEM_ID;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
import it.unibo.pensilina14.bullet.ballet.model.weapon.StaticPickUpItem;

public class EffectFactoryImplTest {

	private final EffectFactory factory = new EffectFactoryImpl();
	private static final double FACTOR = 10.0;
	private static final double HEALTH = 100.0;
	private static final double MANA = 150.0;
	private static final int DEFAULT_DIM = 20;
	private static final double SPEED = 50.0;
	private static final double MASS = 15.0;
	private static final long STEP = 1000;
	private static final long DURATION = 5000;
	
	@Test
	public void testHealEffect() {
		/*
		 * INITIALIZATION
		 */
		final Environment env = new GameEnvironment();
		final Effect heal = factory.createHealEffect(FACTOR);
		final Characters enemy = new Enemy("testEnemy00", HEALTH - FACTOR, Optional.of(MANA),
				new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM), new SpeedVector2DImpl(
						new MutablePosition2Dimpl(0, 0), SPEED), env, MASS);
		final Item healthItem = new StaticPickUpItem(new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM), new MutablePosition2Dimpl(0, 0), 
				env, ITEM_ID.HEART, heal);
		/*
		 * ELABORATION
		 */
		final double healthBefore = enemy.getHealth();
		healthItem.getEffect().applyEffect(enemy);
		final double healthAfter = enemy.getHealth();
		/*
		 * ASSERTIONS
		 */
		assertEquals(HEALTH - FACTOR, healthBefore, 0.0);
		assertEquals(HEALTH, healthAfter, 0.0);
	}
	
	@Test
	public void testDamageEffect() {
		/*
		 * INITIALIZATION
		 */
		final Environment env = new GameEnvironment();
		final Effect damage = factory.createDamageEffect(FACTOR);
		final Characters enemy = new Enemy("testEnemy01", HEALTH, Optional.of(MANA),
				new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM), new SpeedVector2DImpl(
						new MutablePosition2Dimpl(0, 0), SPEED), env, MASS);
		final Item damageItem = new StaticPickUpItem(new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM), new MutablePosition2Dimpl(0, 0),
				env, ITEM_ID.POISON, damage);
		/*
		 * ELABORATION
		 */
		final double healthBefore = enemy.getHealth();
		damageItem.getEffect().applyEffect(enemy);
		final double healthAfter = enemy.getHealth();
		/*
		 * ASSERTIONS
		 */
		assertEquals(HEALTH, healthBefore, 0.0);
		assertEquals(HEALTH - FACTOR, healthAfter, 0.0);
	}
	
	@Test
	public void testPoisonEffect() {
		/*
		 * INITIALIZATION
		 */
		final Environment env = new GameEnvironment();
		final Effect poison = factory.createPoisonEffect(FACTOR, STEP, DURATION);
		final Characters enemy = new Enemy("testEnemy02", HEALTH, Optional.of(MANA),
				new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM), new SpeedVector2DImpl(
						new MutablePosition2Dimpl(0, 0), SPEED), env, MASS);
		final Item poisonItem = new StaticPickUpItem(new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM), new MutablePosition2Dimpl(0, 0),
				env, ITEM_ID.POISON, poison);
		/*
		 * ELABORATION
		 */
		final double healthBefore = enemy.getHealth();
		poisonItem.getEffect().applyEffect(enemy);
		final double healthAfter = enemy.getHealth();
		/*
		 * ASSERTIONS
		 */
		assertEquals(HEALTH, healthBefore, 0.0);
		assertEquals(HEALTH - (FACTOR * ((double) (DURATION / STEP))), healthAfter, 0.0);
	}

}
