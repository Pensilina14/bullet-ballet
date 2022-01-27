package it.unibo.pensilina14.bullet.ballet.graphics.map;

import java.util.Random;

public enum Coins {
    GOLD_COIN("res/assets/sprites/coins/MonedaD.png"),
    SILVER_COIN("res/assets/sprites/coins/MonedaP.png"),
    RED_COIN("res/assets/sprites/coins/MonedaR.png"),
    EMERALD_COIN("res/assets/sprites/coins/spr_coin_strip4.png"),
    LIGHT_BLUE_COIN("res/assets/sprites/coins/spr_coin_azu.png"),
    YELLOW_COIN("res/assets/sprites/coins/spr_coin_ama.png"),
    GREY_COIN("res/assets/sprites/coins/spr_coin_gri.png"),
    RUBY_COIN("res/assets/sprites/coins/spr_coin_roj.png"); //TODO: rename it differently

    private final String path;

    Coins(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public static Coins coinChooser(){
        final int max = Coins.values().length;
        final Random rand = new Random();
        final int randomMap = rand.nextInt(max); // nextInt : 0 incluso, max escluso.
        for (final Coins c : Coins.values()) {
            if (c.ordinal() == randomMap) {
                return c;
            }
        }
        return Coins.GOLD_COIN;
    }

    public static String getRandomCoinPath(){
        final int max = Coins.values().length;
        final Random rand = new Random();
        final int randomMap = rand.nextInt(max); // nextInt : 0 incluso, max escluso.
        for (final Coins c : Coins.values()) {
            if (c.ordinal() == randomMap) {
                return c.getPath();
            }
        }
        return Coins.GOLD_COIN.getPath();
    }
}