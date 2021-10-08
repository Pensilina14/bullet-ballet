package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.effects.EffectFactory;
import it.unibo.pensilina14.bullet.ballet.model.effects.EffectFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effects;
import it.unibo.pensilina14.bullet.ballet.model.effects.SpecialEffects;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class ItemFactoryImpl implements ItemFactory{

    private static final double MASS = 2.5;
    private static final int DIMENSION = 5;
    private final EffectFactory effectFact = new EffectFactoryImpl();
    
    @Override
    public Item createPoisoningItem(final Environment environment, final SpeedVector2D speedVector) {
        return new DynamicPickupItem(new Dimension2Dimpl(DIMENSION, DIMENSION), 
                environment, MASS, speedVector, ITEM_ID.POISON,
                effectFact.createPoisonEffect(SpecialEffects.POISON.getDelta().getValue(),
                        SpecialEffects.POISON.getMsStep().getValue(),
                        SpecialEffects.POISON.getMsDuration().getValue()));
    }

    @Override
    public Item createHealingItem(final Environment environment, final SpeedVector2D speedVector) {
        return new DynamicPickupItem(new Dimension2Dimpl(DIMENSION, DIMENSION), 
                environment, MASS, speedVector, ITEM_ID.HEART,
                effectFact.createHealEffect(Effects.HEALTHY.getDelta().getValue()));
    }

    @Override
    public Item createDamagingItem(final Environment environment, final SpeedVector2D speedVector) {
        return new DynamicPickupItem(new Dimension2Dimpl(DIMENSION, DIMENSION), 
                environment, MASS, speedVector, ITEM_ID.HEART,
                effectFact.createHealEffect(Effects.DAMAGE.getDelta().getValue()));
    }

}
