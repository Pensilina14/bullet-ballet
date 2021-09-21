package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.model.entities.AbstractDynamicComponent;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class DynamicPickupItem extends AbstractDynamicComponent implements Item{

    private final ITEM_ID id;
    private final Effect effect;
    
    public DynamicPickupItem(final Dimension2D dimension, final Environment gameEnvironment, 
            final double mass, final SpeedVector2D vector, final ITEM_ID id, 
            final Effect effect) {
        super(dimension, gameEnvironment, mass, vector);
        this.id = id;
        this.effect = effect;
    }

    @Override
    public Effect getEffect() {
        return this.effect;
    }

    @Override
    public ITEM_ID getItemId() {
        return this.id;
    }
    
    /*
     *TODO = refactor with java streams 
    */
    public void yAxisMovement() {
        while (this.moveUP(1)) {};
        while (this.moveDOWN(1)) {};
    }

    public void xAxisMovement() {
        while (this.moveRIGHT(1)) {};
        while (this.moveLEFT(1)) {};
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((effect == null) ? 0 : effect.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final DynamicPickupItem other = (DynamicPickupItem) obj;
        if (effect == null) {
            if (other.effect != null)
                return false;
        } else if (!effect.equals(other.effect))
            return false;
        if (id != other.id)
            return false;
        return true;
    }
    
}
