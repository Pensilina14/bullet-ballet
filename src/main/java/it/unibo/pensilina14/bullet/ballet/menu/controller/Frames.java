package it.unibo.pensilina14.bullet.ballet.menu.controller;

public enum Frames {

    HOMEPAGE("/homepage.fxml"),
    SETTINGS("/settings.fxml"),
    GAMESTATS("/gameStats.fxml");
    
    private final String fileName;
    
    Frames(final String fileName) {
        this.fileName = fileName;
    }

    /* 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return fileName;
    }
    
}
