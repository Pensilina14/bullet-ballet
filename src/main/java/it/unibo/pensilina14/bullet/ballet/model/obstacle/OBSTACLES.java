package it.unibo.pensilina14.bullet.ballet.model.obstacle;

public enum OBSTACLES {

    MASS(5.0),
    WIDTH(40.0),
    HEIGHT(40.0);

    private final double value;
    
    OBSTACLES(final double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

}
