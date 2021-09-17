package it.unibo.pensilina14.bullet.ballet.model.weapon;

import java.util.stream.IntStream;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.model.entities.AbstractDynamicComponent;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

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
    
}
