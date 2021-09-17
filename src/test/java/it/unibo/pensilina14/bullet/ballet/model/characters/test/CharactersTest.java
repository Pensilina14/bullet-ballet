package it.unibo.pensilina14.bullet.ballet.model.characters.test;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.characters.Player;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class CharactersTest {

    private static final int DEFAULT_DIM = 500;
    private static final int DEFAULT_MASS = 10;

    final Dimension2Dimpl dimension = new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM);
    final Environment environment = new GameEnvironment();
    final MutablePosition2Dimpl position = new MutablePosition2Dimpl(0, 0);

    @Test
    public void testPlayer(){
        final String name = "Player";
        final Player player = new Player(name, dimension, position, environment, DEFAULT_MASS);

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

    @Test
    public void testPlayerTypes(){
        EntityList.Characters.Player playerType = EntityList.Characters.Player.PLAYER1;
        final Player player1 = new Player(playerType, dimension, position, environment, DEFAULT_MASS);

        // HEALTH & MANA

        assertTrue(player1.getHealth() >= 80.0 && player1.getHealth() <= 100.0);
        assertTrue(player1.getMana().get() >= 50.0 && player1.getMana().get() <= 100.0);

        // WEAPON

        //assertEquals("AK-47", player1.getWeapon().getName());
        //assertEquals(100, player1.getWeapon().getTotalAmmo());
    }



    @Test
    public void testEnemy(){
        final String name = "Enemy";
        double health = 100.0;
        Optional<Double> mana = Optional.of(50.0);
        //final int numberOfEnemies = 1;
        final double mass = 35.0;

        final Enemy enemy = new Enemy(name, health, Optional.of(mana.orElse(0.0)), dimension, position, environment, mass);

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

    @Test
    public void testEnemyTypes(){
        final Dimension2Dimpl dimension = new Dimension2Dimpl(DEFAULT_DIM, DEFAULT_DIM);
        final Environment environment = new GameEnvironment();
        final MutablePosition2Dimpl position = new MutablePosition2Dimpl(0, 0);

        final Enemy enemy1 = new Enemy(EntityList.Characters.Enemy.ENEMY1, dimension, position, environment, DEFAULT_MASS);

        assertTrue(enemy1.getHealth() >= 80.0 && enemy1.getHealth() <= 100.0);
        assertTrue(enemy1.getMana().get() >= 40.0 && enemy1.getMana().get() <= 100.0);

        assertTrue(enemy1.getName() == "Enemy1");

        assertEquals("AK-47",enemy1.getWeapon().getName());
    }
}
