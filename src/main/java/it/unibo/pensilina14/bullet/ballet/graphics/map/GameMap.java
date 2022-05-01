package it.unibo.pensilina14.bullet.ballet.graphics.map;

import it.unibo.pensilina14.bullet.ballet.graphics.sprite.Images;

public interface GameMap {

  Maps getMap();

  void setMap(Maps map);

  int getMapWidth();

  int getMapHeight();

  Images.Platforms getPlatformType();

  Images.Coins getCoinType();
}
