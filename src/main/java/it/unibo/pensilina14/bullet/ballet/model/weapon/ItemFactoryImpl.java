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
    private static final int POISONING_ITEM_DIM = 45;
    private static final int HEALING_ITEM_DIM = 45;
    private static final int DAMAGING_ITEM_DIM = 45;
    private final EffectFactory effectFact = new EffectFactoryImpl();

    @Override
	public final Item createPoisoningItem(final Environment environment, final SpeedVector2D speedVector) {
		return new PickupItem(speedVector, environment, MASS, new Dimension2Dimpl(POISONING_ITEM_DIM, POISONING_ITEM_DIM)
				, Items.POISON, effectFact.createPoisonEffect(SpecialEffects.POISON.getDelta().getValue()
				, SpecialEffects.POISON.getMsStep().getValue(), SpecialEffects.POISON.getMsDuration().getValue()));

	}
	@Override
	public final Item createHealingItem(final Environment environment, final SpeedVector2D speedVector) {
		return new PickupItem(speedVector, environment, MASS, new Dimension2Dimpl(HEALING_ITEM_DIM, HEALING_ITEM_DIM)
				, Items.HEART, effectFact.createHealEffect(Effects.HEALTHY.getDelta().getValue()));
	}
	@Override
	public final Item createDamagingItem(final Environment environment, final SpeedVector2D speedVector) {
		return new PickupItem(speedVector, environment, MASS, new Dimension2Dimpl(DAMAGING_ITEM_DIM, DAMAGING_ITEM_DIM)
				, Items.DAMAGE, effectFact.createDamageEffect(Effects.DAMAGE.getDelta().getValue()));
	}
	
	@Override
	public final Item createCoinItem(final Environment environment, final SpeedVector2D speedVector) {
		return new PickupItem(speedVector, environment, MASS, new Dimension2Dimpl(DAMAGING_ITEM_DIM, DAMAGING_ITEM_DIM)
				, Items.COIN, effectFact.createHealEffect(0));
	}
	
	@Override
	public final Item createChargerItem(final Environment environment, final SpeedVector2D speedVector) {
		return new PickupItem(speedVector, environment, MASS, new Dimension2Dimpl(DAMAGING_ITEM_DIM, DAMAGING_ITEM_DIM)
				, Items.CHARGER, effectFact.createRechargeEffect());
	}

	@Override
	public Item createFlagItem(final Environment environment, final SpeedVector2D speedVector) {
		return new PickupItem(speedVector, environment, MASS, new Dimension2Dimpl(DAMAGING_ITEM_DIM, DAMAGING_ITEM_DIM),
				Items.FLAG, effectFact.createDamageEffect(9999.0));
	}

}
