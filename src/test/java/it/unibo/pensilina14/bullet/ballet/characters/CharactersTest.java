package it.unibo.pensilina14.bullet.ballet.characters;

import it.unibo.pensilina14.bullet.ballet.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.MutablePosition2D;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharactersTest {

    @Test
    public void testPlayer(){
        final String name = "Player";
        final Player player = new Player(name);

        // NAME

        assertEquals(name, player.getName());

        // HEALTH

        assertTrue(player.getHealth() == 100.0);

        player.setHealth(50.0);
        assertTrue(player.getHealth() == 50.0);
        assertTrue(player.isAlive());

        player.setHealth(-5.55);
        assertFalse(player.isAlive());

        // MANA

        assertTrue(player.getMana() == 100.0);

        player.decreaseMana(50.0);
        assertTrue(player.getMana() == 50.0);

        player.increaseMana(5.0);
        assertTrue(player.getMana() == 55.0);

        player.decreaseMana(55.0);
        assertFalse(player.manaLeft());

    }

    @Test
    public void testEnemy(){
        final String name = "Enemy";
        double health = 100.0;
        double mana = 50.0;
        final int numberOfEnemies = 1;
        final double mass = 35.0;

        final Dimension2D dimension = null;
        final MutablePosition2D position = null;
        final Environment environment = null;

        final Enemy enemy = new Enemy(name, health, mana, numberOfEnemies, dimension, position, environment, mass);

        // NAME

        assertEquals(name, enemy.getName());

        // HEALTH

        assertTrue(enemy.getHealth() == health);

        enemy.setHealth(0.0);
        assertFalse(enemy.isAlive());

        // MANA

        assertTrue(enemy.getMana() == mana);

        enemy.increaseMana(15.0);
        assertTrue(enemy.getMana() == 65.0);

        enemy.decreaseMana(65.0);
        assertFalse(enemy.isAlive());
    }
}
