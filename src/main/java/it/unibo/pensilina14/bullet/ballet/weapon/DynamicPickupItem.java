package it.unibo.pensilina14.bullet.ballet.weapon;

import it.unibo.pensilina14.bullet.ballet.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.game.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.game.entities.AbstractDynamicComponent;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.misc.utilities2D.MutablePosition2D;

public class DynamicPickupItem extends AbstractDynamicComponent implements Item{

    private final ITEM_ID id;
    private final Effect effect;
    
    public DynamicPickupItem(Dimension2D dimension, MutablePosition2D position
            , Environment gameEnvironment, double mass,
            Effect effect, ITEM_ID id) {
        super(dimension, position, gameEnvironment, mass);
        this.effect = effect;
        this.id = id;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((effect == null) ? 0 : effect.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        DynamicPickupItem other = (DynamicPickupItem) obj;
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
