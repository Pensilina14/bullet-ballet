package it.unibo.pensilina14.bullet.ballet.graphics.test;

import it.unibo.pensilina14.bullet.ballet.graphics.map.Coin;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Platform;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.MainEnemy;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.MainPlayer;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MapTest {

    @Test
    public void platformTest() throws IOException { //TODO: lasciare solo mapTest
        //TODO: platform Test
        Platform platform = new Platform(Platform.Platforms.DESERT_PLATFORM, 0, 0);

        String path = "res/assets/maps/Tiles/desert_platform2.png";
    }

    @Test
    public void coinTest() throws IOException { //TODO: lasciare solo mapTest
        //TODO: coins test
        Coin coin = new Coin(Coin.Coins.GOLD_COIN, 0,0);

        coin.coinChooser();
    }

    @Test
    public void mainPlayerTest() throws IOException { //TODO: lasciare solo mapTest
        //TODO: mainPlayer test
        MainPlayer mainPlayer = new MainPlayer();
    }

    @Test
    public void mainEnemyTest() throws IOException { //TODO: lasciare solo mapTest
        //TODO: mainEnemy test
        MainEnemy mainEnemy = new MainEnemy(0,0);
    }

    @Test
    public void mapTest(){
        //TODO: map test
        Map map = new Map();
        map.setMap(Map.Maps.CAVE);

        //String caveMapPath = "res/assets/maps/Backgrounds/cave_background.png";

        assertEquals(Map.Maps.CAVE.getPath(), map.getMap().getPath());
        assertEquals(Platform.Platforms.CAVE_PLATFORM, map.getPlatformType());

        final int width = 1280;
        final int height = 720;

        assertEquals(width, map.getMapWidth());
        assertEquals(height, map.getMapHeight());
    }

    @Test
    public void mapSceneTest(){
        //TODO: mapScene test
    }
}
