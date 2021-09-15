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
import it.unibo.pensilina14.bullet.ballet.weapon.PickupItem;

public class EffectTest {

	@Test
	public void testApplyEffect() {
		final Environment env = new GameEnvironment();
		final Characters enemy = new Enemy("testEnemy00", 100.0, Optional.ofNullable(150.0), 
				0, new Dimension2Dimpl(25, 5), new MutablePosition2Dimpl(10, 30), env, 85.0);
		final PickupItem item = new PickupItem(new Dimension2Dimpl(25, 5),
				new MutablePosition2Dimpl(150, 50), env, 3.5, e -> enemy.setHealth(enemy.getHealth() - 10.0), ITEM_ID.POISON);
		
		item.getEffect().applyEffect(enemy);
		
		assertEquals(enemy.getHealth(), 90.0, 0.01);
	}

}
