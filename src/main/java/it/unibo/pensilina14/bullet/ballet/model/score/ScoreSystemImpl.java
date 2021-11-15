package it.unibo.pensilina14.bullet.ballet.model.score;

public class ScoreSystemImpl implements ScoreSystem{

	private int currentScore;
	
	public ScoreSystemImpl(final int currentScore) {
		this.currentScore = currentScore;
	}

	@Override
	public void increase() {
		this.currentScore++;
	}

	@Override
	public void decrease() {
		this.currentScore++;
	}

	@Override
	public void reset() {
		this.currentScore = 0;
	}

	@Override
	public double showScore() {
		return this.currentScore;
	}

	
	
}
