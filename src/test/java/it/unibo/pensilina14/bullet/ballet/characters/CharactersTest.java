package it.unibo.pensilina14.bullet.ballet.characters;

import it.unibo.pensilina14.bullet.ballet.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.MutablePosition2D;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;

public class CharactersTest {

    @Test
    public void testPlayer(){
        final String name = "Player";
        final Player player = new Player(name);
    }

    @Test
    public void testEnemy(){
        final String name = "Enemy";
        double health = 100.0;
        double mana = 50.0;
        final int numberOfEnemies = 1;
        final double mass = 35.0;

        //final Dimension2D dimension = new Dimension2D();
        //final MutablePosition2D position = new MutablePosition2D();
        //final Environment environment = new Environment();

        //final Enemy enemy = new Enemy(name, health, mana, numberOfEnemies, dimension, position, environment, mass);
    }
}
