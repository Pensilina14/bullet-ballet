package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.model.entities.AbstractDynamicComponent;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class StaticPickUpItem extends AbstractDynamicComponent implements Item{

    private final ITEM_ID id;
    private final Effect effect;
    
    public StaticPickUpItem(final Dimension2D dimension, final MutablePosition2D position
            , final Environment gameEnvironment, final double mass,
            ITEM_ID id, Effect effect) {
        super(dimension, position, gameEnvironment, mass);
        this.id = id;
        this.effect = effect;
    }

    @Override
    public ITEM_ID getItemId() {
        return this.id;
    }

    @Override
    public Effect getEffect() {
        return this.effect;
    }
    
    
    
    
}
