package it.unibo.pensilina14.bullet.ballet.save;

public enum Extensions { //TODO: forse non necessario questo enum?

    TXT(".txt"),
    DAT(".dat"),
    JSON(".json");

    private final String extension;

    Extensions(final String extension){
        this.extension = extension;
    }

    public String getExtension(){
        return this.extension;
    }

}
