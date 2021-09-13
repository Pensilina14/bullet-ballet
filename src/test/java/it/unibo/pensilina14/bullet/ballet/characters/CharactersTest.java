package it.unibo.pensilina14.bullet.ballet.characters;

import it.unibo.pensilina14.bullet.ballet.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.environment.GameEnvironment;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.MutablePosition2Dimpl;
import org.junit.Test;

import java.util.Optional;

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

        assertTrue(player.getMana().get() == 100.0);

        player.decreaseMana(50.0);
        assertTrue(player.getMana().get() == 50.0);

        player.increaseMana(5.0);
        assertTrue(player.getMana().get() == 55.0);

        player.decreaseMana(55.0);
        assertFalse(player.manaLeft());

        // WEAPON

    }

    private static final int DEFAULT_DIM = 500;

    @Test
    public void testEnemy(){
        final String name = "Enemy";
        double health = 100.0;
        Optional<Double> mana = Optional.of(50.0);
        final int numberOfEnemies = 1;
        final double mass = 35.0;

        final Dimension2Dimpl dimension = new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM);
        final Environment environment = new GameEnvironment();
        final MutablePosition2Dimpl position = new MutablePosition2Dimpl(0, 0);

        final Enemy enemy = new Enemy(name, health, Optional.of(mana.orElse(0.0)), numberOfEnemies, dimension, position, environment, mass);

        // NAME

        assertEquals(name, enemy.getName());

        // HEALTH

        assertTrue(enemy.getHealth() == health);

        enemy.setHealth(0.0);
        assertFalse(enemy.isAlive());

        // MANA

        assertTrue(enemy.getMana().get().equals(mana.get()));

        enemy.increaseMana(15.0);
        assertTrue(enemy.getMana().get() == 65.0);

        enemy.decreaseMana(65.0);
        assertFalse(enemy.manaLeft());

        // WEAPON
    }
}
