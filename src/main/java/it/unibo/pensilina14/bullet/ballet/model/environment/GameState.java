package it.unibo.pensilina14.bullet.ballet.model.environment;

public class GameState {
	
	private int score;
	private final Environment env;
	
	public GameState() {
		this.score = 0;
		this.env = new GameEnvironment();
		//TODO: setup GameEnvironment
	}
	
	public Environment getGameEnvironment() {
		return this.env;
	}
	
	public void incScore() {
		this.score++;
	}
	
	public void decScore() {
		this.score--;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void update(final int dt) {
		this.env.updateState(dt);
	}
}
