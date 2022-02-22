package it.unibo.pensilina14.bullet.ballet.graphics.test;

import org.junit.Test;

import it.unibo.pensilina14.bullet.ballet.graphics.map.BackgroundMap;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Coins;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Maps;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Platforms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapTest {

    @Test
    public void mapTest(){
        //TODO: map test
        final BackgroundMap map = new BackgroundMap();
        map.setMap(Maps.CAVE);

        assertEquals(Maps.CAVE.getPath(), map.getMap().getPath());
        assertEquals(Platforms.CAVE_PLATFORM, map.getPlatformType());

        final int width = 1280;
        final int height = 720;

        assertEquals(width, map.getMapWidth());
        assertEquals(height, map.getMapHeight());

        //Check whether map, platform and coin are in range of enum.
        // MAP TYPE CHECK
        boolean mapTypeChecker = false;
        for(final Maps m : Maps.values()){
            if (m == map.getMap()) {
                mapTypeChecker = true;
                break;
            }
        }

        assertTrue(mapTypeChecker);

        // PLATFORM TYPE CHECK

        boolean platformTypeChecker = false;
        for(final Platforms p : Platforms.values()){
            if (p == map.getPlatformType()) {
                platformTypeChecker = true;
                break;
            }
        }

        assertTrue(platformTypeChecker);


        // COIN TYPE CHECK

        boolean coinTypeChecker = false;
        for(final Coins c : Coins.values()){
            if (c == map.getCoinType()) {
                coinTypeChecker = true;
                break;
            }
        }

        assertTrue(coinTypeChecker);
    }

    @Test
    public void mapSceneTest(){
        //TODO: mapScene test
    }
}
