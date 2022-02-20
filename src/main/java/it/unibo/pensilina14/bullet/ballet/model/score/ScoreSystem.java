package it.unibo.pensilina14.bullet.ballet.model.score;

public interface ScoreSystem {

	/**
	 * Increases the score counter.
	 */
	void increase();
	/**
	 * Increments the score n times.
	 * @param n
	 */
	void increase(int n);
	/**
	 * Decreases the score counter.
	 */
	void decrease();
	/**
	 * Resets the score counter.
	 */
	void reset();
	/**
	 * 
	 * @return current score
	 */
	double showScore();
	
}
