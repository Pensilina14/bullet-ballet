package it.unibo.pensilina14.bullet.ballet.model.characters.test;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.characters.FactoryCharactersImpl;
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
    final double speed = 5.0;
    final SpeedVector2D vector = new SpeedVector2DImpl(position, speed);

    @Test
    public void testPlayer(){
        final String name = "Player";
        final Player player = new Player(name, dimension, vector, environment, DEFAULT_MASS);

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
        //TODO: weapon test

    }

    @Test
    public void testPlayerTypes(){
        EntityList.Characters.Player playerType = EntityList.Characters.Player.PLAYER1;
        final Player player1 = new Player(playerType, dimension, vector, environment, DEFAULT_MASS);

        // HEALTH & MANA

        assertTrue(player1.getHealth() >= 80.0 && player1.getHealth() <= 100.0);
        assertTrue(player1.getMana().get() >= 50.0 && player1.getMana().get() <= 100.0);

        // WEAPON
        //TODO: weapon test

        //assertEquals("AK-47", player1.getWeapon().getName());
        //assertEquals(100, player1.getWeapon().getTotalAmmo());
    }



    @Test
    public void testEnemy(){
        final String name = "Enemy";
        double health = 100.0;
        Optional<Double> mana = Optional.of(50.0);
        final double mass = 35.0;

        final Enemy enemy = new Enemy(name, health, Optional.of(mana.orElse(0.0)), dimension, vector, environment, mass);

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
        //TODO: weapon test
    }

    @Test
    public void testEnemyTypes(){

        final Enemy enemy1 = new Enemy(EntityList.Characters.Enemy.ENEMY1, dimension, vector, environment, DEFAULT_MASS);

        assertTrue(enemy1.getHealth() >= 80.0 && enemy1.getHealth() <= 100.0);
        assertTrue(enemy1.getMana().get() >= 40.0 && enemy1.getMana().get() <= 100.0);

        assertSame("Enemy1", enemy1.getName());

        //assertEquals("AK-47",enemy1.getWeapon().getName()); //TODO: weapon test
    }

    @Test
    public void factoryCharactersImplTest(){
        final FactoryCharactersImpl factoryCharacters = new FactoryCharactersImpl();
        Player player = factoryCharacters.createRandomPlayer(vector, environment);
        Enemy enemy = factoryCharacters.createRandomEnemy(vector, environment);

        // Non conosco i valori della vita e resto a priori, perchè sono casuali.

        // HEALTH
        double playerHealth = player.getHealth();
        double enemyHealth = enemy.getHealth();
        player.increaseHealth(20.0);
        enemy.increaseHealth(15.0);

        assertEquals(player.getHealth(), (playerHealth + 20.0), 0.0);
        assertEquals(enemy.getHealth(), (enemyHealth + 15.0), 0.0);

        playerHealth = player.getHealth();
        enemyHealth = enemy.getHealth();

        player.decreaseHealth(10.0);
        enemy.decreaseHealth(5.0);

        assertEquals(player.getHealth(), (playerHealth - 10.0), 0.0);
        assertEquals(enemy.getHealth(), (enemyHealth - 5.0), 0.0);

        // Checking whether player/enemy Type is in range.

        boolean playerTypeChecker = false;
        for(EntityList.Characters.Player p : EntityList.Characters.Player.values()){
            if(p == player.getPlayerType()){
                assertSame(p, player.getPlayerType());
                //playerTypeChecker++;
                playerTypeChecker = true;
            }
        }
        //assertTrue(playerTypeChecker > 0);
        assertTrue(playerTypeChecker);

        boolean enemyTypeChecker = false;
        for(EntityList.Characters.Enemy e : EntityList.Characters.Enemy.values()){
            if(e == enemy.getEnemyType()){
                enemyTypeChecker = true;
            }
        }

        assertTrue(enemyTypeChecker);

    }
}
