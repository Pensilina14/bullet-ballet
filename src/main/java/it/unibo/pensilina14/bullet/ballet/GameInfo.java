package it.unibo.pensilina14.bullet.ballet;

import it.unibo.pensilina14.bullet.ballet.menu.controller.Difficulties;
import it.unibo.pensilina14.bullet.ballet.menu.controller.Resolutions;

public interface GameInfo {

  Resolutions getCurrentResolution();

  Difficulties getCurrentDifficulty();

  void setResolution(Resolutions resolution);

  void setDifficulty(Difficulties difficulty);
}
