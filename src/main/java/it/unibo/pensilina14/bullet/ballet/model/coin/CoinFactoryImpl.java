package it.unibo.pensilina14.bullet.ballet.model.coin;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.effects.EffectFactory;
import it.unibo.pensilina14.bullet.ballet.model.effects.EffectFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effects;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Items;

public class CoinFactoryImpl implements CoinFactory{

    private static final double MASS = 2.5; //TODO: modify
    private static final int COIN_DIM = 30; //TODO: modify
    private final EffectFactory effectFactory = new EffectFactoryImpl();

    private final Dimension2Dimpl dimension = new Dimension2Dimpl(CoinFactoryImpl.COIN_DIM, CoinFactoryImpl.COIN_DIM);

    @Override
    public Coin createStandardCoin(final Environment gameEnvironment, final SpeedVector2D vector) {
        return new CoinImpl(vector, gameEnvironment, CoinFactoryImpl.MASS, this.dimension, Items.COIN, effectFactory.createDamageEffect(Effects.DAMAGE.getDelta().getValue())); //TODO: modify Items e effect
    }
}
