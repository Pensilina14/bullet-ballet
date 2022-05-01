package it.unibo.pensilina14.bullet.ballet.save;

public enum GameFiles {
  PROJECT_DIRECTORY("/.bullet-ballet/"),
  USER_HOME_PROPERTY("user.home"),
  USER_HOME_DIRECTORY(System.getProperty(USER_HOME_PROPERTY.getString())),
  SAVE_PATH(
      USER_HOME_DIRECTORY.getString() + PROJECT_DIRECTORY.getString() + "data/saves/save_file.dat"),
  LEVEL_PATH("data/levels/"),
  SETTINGS_PATH(
      USER_HOME_DIRECTORY.getString()
          + PROJECT_DIRECTORY.getString()
          + "data/settings/settings.dat"),
  PLAYER_STRING("Player"),
  SCORE_STRING("Score"),
  DATE_STRING("Date"),
  RESOLUTION_WIDTH_STRING("Width"),
  RESOLUTION_HEIGHT_STRING("Height"),
  DIFFICULTY_STRING("Difficulty"),
  AUDIO_STRING("Audio"),
  LANGUAGE_STRING("Language");

  private final String string;

  GameFiles(final String string) {
    this.string = string;
  }

  public String getString() {
    return this.string;
  }
}
