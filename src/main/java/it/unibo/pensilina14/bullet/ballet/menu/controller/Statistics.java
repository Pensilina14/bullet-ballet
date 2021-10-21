package it.unibo.pensilina14.bullet.ballet.menu.controller;

public class Statistics {
    
    private final String playerName;
    private final int points;
    private final double time;
    
    public Statistics(final String playerName, final int points, final double time) {
        super();
        this.playerName = playerName;
        this.points = points;
        this.time = time;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public int getPoints() {
        return this.points;
    }

    public double getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "Statistics [playerName=" + this.playerName +
                ", points=" + this.points + ", time=" + this.time + "]";
    }
     
}
