package it.unibo.pensilina14.bullet.ballet.menu.controller;

public class Statistics {
    
    private final String playerName;
    private final double points;
    private final String date;
    
    public Statistics(final String playerName, final double points, final String date) {
        this.playerName = playerName;
        this.points = points;
        this.date = date;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public double getPoints() {
        return this.points;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "Statistics [playerName=" + this.playerName +
                ", points=" + this.points + ", date=" + this.date + "]";
    }
     
}
