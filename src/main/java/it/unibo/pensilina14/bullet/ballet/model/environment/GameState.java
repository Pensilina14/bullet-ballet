package it.unibo.pensilina14.bullet.ballet.model.environment;

import it.unibo.pensilina14.bullet.ballet.model.environment.events.GameEventListener;

public class GameState {
	
	private int score;
	private final Environment env;
	private final LevelGenerator generator;
	private final static double WIDTH = 1882;
	private final static double HEIGHT = 11;
	
	
	public GameState() {
		this.score = 0;
		this.generator = new EnvironmentGenerator();
		this.env = new GameEnvironment(HEIGHT, WIDTH);
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
}
