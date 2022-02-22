package it.unibo.pensilina14.bullet.ballet.graphics.map;

import it.unibo.pensilina14.bullet.ballet.graphics.sprite.Images;

public class BackgroundMap implements GameMap {

    private static final int MAP_WIDTH = 1280;
    private static final int MAP_HEIGHT = 720;

    private Maps map;
    private Images.Platforms platformType;
    private Images.Coins coinType;

    public BackgroundMap() {
        this.map = Maps.mapChooser();
        this.coinType = Images.Coins.getDefaultCoin();
        initMap();
    }

    public BackgroundMap(final Maps map) {
        this.map = map;
        setMap(this.map);
    }

    private void initMap() {
        switch (this.map) {
            case CAVE:
                this.platformType = Images.Platforms.CAVE_PLATFORM;
                this.coinType = Images.Coins.GREY_COIN;
                break;
            case CAVE2:
                this.platformType = Images.Platforms.CAVE_PLATFORM2;
                this.coinType = Images.Coins.GREY_COIN;
                break;
            case CAVE3:
                this.platformType = Images.Platforms.CAVE_PLATFORM3;
                this.coinType = Images.Coins.GREY_COIN;
                break;
            case HALLOWEEN:
                this.platformType = Images.Platforms.HALLOWEEN_PLATFORM;
                this.coinType = Images.Coins.YELLOW_COIN;
                break;
            case LAVA:
                this.platformType = Images.Platforms.LAVA_PLATFORM;
                this.coinType = Images.Coins.RED_COIN;
                break;
            case JUNGLE:
            case JUNGLE2:
                this.platformType = Images.Platforms.JUNGLE_PLATFORM;
                this.coinType = Images.Coins.EMERALD_COIN;
                break;
            case FOREST:
            case FOREST2:
            case COUNTRYSIDE:
                this.platformType = Images.Platforms.FOREST_PLATFORM;
                this.coinType = Images.Coins.SILVER_COIN;
                break;
            case FUTURISTIC:
                this.platformType = Images.Platforms.FUTURISTIC_PLATFORM;
                this.coinType = Images.Coins.LIGHT_BLUE_COIN;
                break;
            case SCIFI:
                this.platformType = Images.Platforms.SCIFI_PLATFORM;
                this.coinType = Images.Coins.LIGHT_BLUE_COIN;
                break;
            case PLANET:
            case PLANET2:
                this.platformType = Images.Platforms.SCIFI_PLATFORM3;
                this.coinType = Images.Coins.GOLD_COIN;
                break;
            case SPACE:
                this.platformType = Images.Platforms.SPACE_PLATFORM;
                this.coinType = Images.Coins.SILVER_COIN;
                break;
            case SPACESHIP:
                this.platformType = Images.Platforms.SCIFI_PLATFORM2;
                this.coinType = Images.Coins.SILVER_COIN;
                break;
            case ICE:
            case ICE2:
                this.platformType = Images.Platforms.ICE_PLATFORM;
                this.coinType = Images.Coins.GOLD_COIN;
                break;
            case SWAMP:
            case SWAMP2:
                this.platformType = Images.Platforms.SWAMP_PLATFORM;
                this.coinType = Images.Coins.EMERALD_COIN;
                break;
            case DESERT2:
                this.platformType = Images.Platforms.DESERT_PLATFORM2;
                this.coinType = Images.Coins.RUBY_COIN;
                break;
            case DESERT3:
                this.platformType = Images.Platforms.DESERT_PLATFORM3;
                this.coinType = Images.Coins.RUBY_COIN;
                break;
            case DESERT4:
                this.platformType = Images.Platforms.DESERT_PLATFORM4;
                this.coinType = Images.Coins.RUBY_COIN;
                break;
            case CITY:
            case CITY2:
                this.platformType = Images.Platforms.CRATE_PLATFORM;
                this.coinType = Images.Coins.LIGHT_BLUE_COIN;
                break;
            case DESERT:
            default:
                this.platformType = Images.Platforms.DESERT_PLATFORM;
                this.coinType = Images.Coins.YELLOW_COIN;
                break;
        }
    }

    @Override
	public final Maps getMap() {
        return this.map;
    }

    @Override
	public final void setMap(final Maps map) {
        this.map = map;

        initMap();
    }

    @Override
	public final int getMapWidth() {
        return BackgroundMap.MAP_WIDTH;
    }

    @Override
	public final int getMapHeight() {
        return BackgroundMap.MAP_HEIGHT;
    }

    @Override
	public final Images.Platforms getPlatformType() {
        return this.platformType;
    }

    @Override
	public final Images.Coins getCoinType() {
        return this.coinType;
    }
}
