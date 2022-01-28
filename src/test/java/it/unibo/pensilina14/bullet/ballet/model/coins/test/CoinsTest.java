package it.unibo.pensilina14.bullet.ballet.model.coins.test;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.model.coin.Coin;
import it.unibo.pensilina14.bullet.ballet.model.coin.CoinFactory;
import it.unibo.pensilina14.bullet.ballet.model.coin.CoinFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameEnvironment;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoinsTest {

    private static final int POSITION = -5;
    private static final int SPEED = 1;

    private static final int DEFAULT_COIN_VALUE = 1;
    private final CoinFactory coinFactory = new CoinFactoryImpl();

    private final Environment gameEnv = new GameEnvironment();

    @Test
    public void coinsTest(){
        Coin coin = coinFactory.createStandardCoin(this.gameEnv, new SpeedVector2DImpl
                (new MutablePosition2Dimpl(POSITION, POSITION), SPEED));
        assertEquals(DEFAULT_COIN_VALUE, coin.getCoinValue());
    }
}
