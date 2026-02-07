package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;

public class GameState {

  private static final double WIDTH = 1882;
  private static final double HEIGHT = 11;
  private final String playerName;
  private int score;
  private final Environment env;
  private final LevelGenerator generator;

  public GameState() {
    this.score = 0;
    this.generator = new EnvironmentGenerator();
    this.env = new GameEnvironment(Environment.GravityConstants.GAMEPLAY.getValue(), HEIGHT, WIDTH);
    this.generator.setEnvironment(this.env);
    this.generator.generate();
    this.playerName = "unknown";
  }

  //	public GameState(final Environment env) {
  //		this.score = 0;
  //		this.generator = new EnvironmentGenerator();
  //		this.env = env;
  //		this.generator.setEnvironment(env);
  //		this.generator.generate();
  //	}

  public GameState(final String playerName) {
    this.playerName = playerName;
    this.score = 0;
    this.generator = new EnvironmentGenerator();
    this.env = new GameEnvironment(Environment.GravityConstants.GAMEPLAY.getValue(), HEIGHT, WIDTH);
    this.generator.setEnvironment(this.env);
    this.generator.generate();
  }

  public Environment getGameEnvironment() {
    return this.env;
  }

  public void setEventListener(final GameEventListener l) {
    this.env.setEventListener(l);
  }

  public LevelGenerator getEnvGenerator() {
    return this.generator;
  }

  public void incScore() {
    this.score++;
  }

  public int getScore() {
    return this.score;
  }

  public boolean isGameOver() {
    return this.env.getEntityManager().getPlayer().get().getHealth() == 0;
  }

  public void update() {
    this.env.updateState();
  }

  public String getPlayerName() {
    return this.playerName;
  }
}
