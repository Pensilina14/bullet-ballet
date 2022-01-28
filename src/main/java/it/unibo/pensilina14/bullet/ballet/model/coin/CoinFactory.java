package it.unibo.pensilina14.bullet.ballet.model.coin;

import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2D;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public interface CoinFactory {

    /**
     *
     * @param gameEnvironment : the game Environment.
     * @param vector : the speed vector.
     * @return : a new Coin.
     */
    Coin createStandardCoin(final Environment gameEnvironment, final SpeedVector2D vector);
}
