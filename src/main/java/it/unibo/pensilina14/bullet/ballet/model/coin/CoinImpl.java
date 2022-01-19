package it.unibo.pensilina14.bullet.ballet.model.coin;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.model.entities.GameEntity;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Items;

import java.util.Optional;
import java.util.Random;

public class CoinImpl extends GameEntity implements Coin {

    //TODO: o coin con un unico valore o con valori randomici oppure valore in base alla tipologia.
    //TODO: però magari non ha senso avere un coin che valga in modo randomico, ha più senso che un coin valga 1

    private final static int COIN_VALUE = 1;
    private final static int MAX_COIN_VALUE = 5;

    public CoinImpl(final SpeedVector2D speedVector, final Environment gameEnvironment, final double mass, final Dimension2D dimension){ //TODO: Item itemID e l'effect come parametro?
        super(speedVector, gameEnvironment, mass, dimension);
    }

    @Override
    public int getCoinValue() {
        return CoinImpl.COIN_VALUE; // prima era return randomCoinValue();
    }

    private int randomCoinValue(){ //TODO: forse da rimuovere
        final Random rand = new Random();
        return rand.nextInt(CoinImpl.MAX_COIN_VALUE);
    }

    @Override
    public Items getItemId() {
        return null;
    }

    @Override
    public Effect getEffect() {
        return null;
    }

    @Override
    public void moveToRandomPosition() {

    }
}
