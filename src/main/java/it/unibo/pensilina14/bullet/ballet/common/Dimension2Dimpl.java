package it.unibo.pensilina14.bullet.ballet.common;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class Dimension2Dimpl implements Dimension2D {
    
    private final double height;
    private final double width;
      
    public Dimension2Dimpl(final double height, final double width) {
      this.height = height;
      this.width = width;
    }

    @Override
    public ImmutablePair<Double, Double> getSize() {
      return ImmutablePair.of(this.height, this.width);
    }

    @Override
    public double getHeight() {
      return this.height;
    }

    @Override
    public double getWidth() {
      return this.width;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(height);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(width);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dimension2Dimpl other = (Dimension2Dimpl) obj;
        if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
            return false;
        if (Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width))
            return false;
        return true;
    }
   
}
