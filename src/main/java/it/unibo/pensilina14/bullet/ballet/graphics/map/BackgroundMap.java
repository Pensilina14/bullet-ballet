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
        this.coinType = Images.Coins.coinChooser();
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
                break;
            case CAVE2:
                this.platformType = Images.Platforms.CAVE_PLATFORM2;
                break;
            case CAVE3:
                this.platformType = Images.Platforms.CAVE_PLATFORM3;
                break;
            case HALLOWEEN:
                this.platformType = Images.Platforms.HALLOWEEN_PLATFORM;
                break;
            case LAVA:
                this.platformType = Images.Platforms.LAVA_PLATFORM;
                break;
            case JUNGLE:
            case JUNGLE2:
                this.platformType = Images.Platforms.JUNGLE_PLATFORM;
                break;
            case FOREST:
            case FOREST2:
            case COUNTRYSIDE:
                this.platformType = Images.Platforms.FOREST_PLATFORM;
                break;
            case FUTURISTIC:
                this.platformType = Images.Platforms.FUTURISTIC_PLATFORM;
                break;
            case SCIFI:
                this.platformType = Images.Platforms.SCIFI_PLATFORM;
                break;
            case PLANET:
            case PLANET2:
                this.platformType = Images.Platforms.SCIFI_PLATFORM3;
                break;
            case SPACE:
                this.platformType = Images.Platforms.SPACE_PLATFORM;
                break;
            case SPACESHIP:
                this.platformType = Images.Platforms.SCIFI_PLATFORM2;
                break;
            case ICE:
            case ICE2:
                this.platformType = Images.Platforms.ICE_PLATFORM;
                break;
            case SWAMP:
            case SWAMP2:
                this.platformType = Images.Platforms.SWAMP_PLATFORM;
                break;
            case DESERT2:
                this.platformType = Images.Platforms.DESERT_PLATFORM2;
                break;
            case DESERT3:
                this.platformType = Images.Platforms.DESERT_PLATFORM3;
                break;
            case DESERT4:
                this.platformType = Images.Platforms.DESERT_PLATFORM4;
                break;
            case CITY:
            case CITY2:
                this.platformType = Images.Platforms.CRATE_PLATFORM;
                break;
            case DESERT:
            default:
                this.platformType = Images.Platforms.DESERT_PLATFORM;
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
