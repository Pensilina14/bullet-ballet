package it.unibo.pensilina14.bullet.ballet.model.score;

public interface ScoreSystem {

	/**
	 * Increases the score counter
	 */
	void increase();
	/**
	 * Decreases the score counter
	 */
	void decrease();
	/**
	 * Resets the score counter
	 */
	void reset();
	/**
	 * 
	 * @return current score
	 */
	double showScore();
	
}
