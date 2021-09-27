package it.unibo.pensilina14.bullet.ballet.menu.controller;

public enum FRAME {

    HOMEPAGE("/homepage.fxml"),
    SETTINGS("/settings.fxml");
    
    private final String fileName;
    
    FRAME(final String fileName) {
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
