package it.unibo.pensilina14.bullet.ballet.menu.controller;

public enum Difficulties {

	EASY("easy"),
	MEDIUM("medium"),
	HARD("hard");
	
	private final String difficulty;

	Difficulties(final String difficulty) {
		this.difficulty = difficulty;
	}
	
	/* 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return this.difficulty;
    }
	
}
