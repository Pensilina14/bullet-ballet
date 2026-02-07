package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.save.Save;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LevelLoader {

  private final String[] level;
  private final double levelHeight;
  private final double levelWidth;

  private static int nextLevelIndex;

  private static boolean randomNextLevel;

  private static int lastRandomLevelIndex = -1;

  private static final Random RAND = new Random();

  /**
   * Requests that the next created {@link LevelLoader} instance picks a random level.
   * This is a one-shot flag: after the next load it is automatically reset.
   */
  public static void requestRandomNextLevel() {
    randomNextLevel = true;
  }

  public LevelLoader() {
    final int levelIndex;
    if (randomNextLevel) {
      randomNextLevel = false;
      levelIndex = getRandomLevelAvoidingImmediateRepeat();
      lastRandomLevelIndex = levelIndex;
    } else {
      levelIndex = nextLevelIndex;
      nextLevelIndex = (nextLevelIndex + 1) % Save.MAX_LEVELS;
    }

    String[] loaded = Save.loadLevel(levelIndex);
    if (loaded.length == 0) {
      loaded = Save.loadLevel(0);
    }

    this.level = loaded;
    this.levelWidth = this.level[0].length();
    this.levelHeight = this.level.length;
  }

  private static int getRandomLevelAvoidingImmediateRepeat() {
    if (Save.MAX_LEVELS <= 1) {
      return 0;
    }

    int candidate;
    int attempts = 0;
    do {
      candidate = LevelLoader.RAND.nextInt(Save.MAX_LEVELS);
      attempts++;
    } while (candidate == lastRandomLevelIndex && attempts < 5);

    return candidate;
  }

  public double getLevelWidth() {
    return this.levelWidth;
  }

  public double getLevelHeight() {
    return this.levelHeight;
  }

  public List<String> getLevel() {
    return Arrays.asList(this.level);
  }
}
