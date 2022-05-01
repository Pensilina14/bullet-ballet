package it.unibo.pensilina14.bullet.ballet.graphics.test;

import it.unibo.pensilina14.bullet.ballet.graphics.map.BackgroundMap;
import it.unibo.pensilina14.bullet.ballet.graphics.map.GameMap;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Maps;
import it.unibo.pensilina14.bullet.ballet.graphics.sprite.Images;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapTest {

  @Test
  public void mapTest() {
    final GameMap map = new BackgroundMap();
    map.setMap(Maps.CAVE);

    assertEquals(Maps.CAVE.getPath(), map.getMap().getPath());
    assertEquals(Images.Platforms.CAVE_PLATFORM, map.getPlatformType());

    final int width = 1280;
    final int height = 720;

    assertEquals(width, map.getMapWidth());
    assertEquals(height, map.getMapHeight());

    // Check whether map, platform and coin are in range of enum.
    // MAP TYPE CHECK
    boolean mapTypeChecker = false;
    for (final Maps m : Maps.values()) {
      if (m == map.getMap()) {
        mapTypeChecker = true;
        break;
      }
    }

    assertTrue(mapTypeChecker);

    // PLATFORM TYPE CHECK

    boolean platformTypeChecker = false;
    for (final Images.Platforms p : Images.Platforms.values()) {
      if (p == map.getPlatformType()) {
        platformTypeChecker = true;
        break;
      }
    }

    assertTrue(platformTypeChecker);

    // COIN TYPE CHECK

    boolean coinTypeChecker = false;
    for (final Images.Coins c : Images.Coins.values()) {
      if (c == map.getCoinType()) {
        coinTypeChecker = true;
        break;
      }
    }

    assertTrue(coinTypeChecker);
  }
}
