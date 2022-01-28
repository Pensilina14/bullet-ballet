package it.unibo.pensilina14.bullet.ballet.graphics.map;

import java.util.*;

public class BackgroundMap {

    private Maps map;
    private final static int MAP_WIDTH = 1280;
    private final static int MAP_HEIGHT = 720;
    private static final Random RAND = new Random();
    private final static Maps DEFAULT_MAP = Maps.HALLOWEEN;

    private Platforms platformType;
    private Coins coinType;
    
    public BackgroundMap(){
    	
        this.map = Maps.mapChooser();
        //this.map = Maps.SPACESHIP; //TODO: questo solo per testare una specifica mappa. (commentare this.map = mapChooser())

        //this.platformType = Platform.Platforms.DESERT_PLATFORM4; //TODO: questo solo per testare una specifica platform (commentare initMap())

        final CoinSprite coinSprite = new CoinSprite();
        this.coinType = Coins.coinChooser();

        initMap();

    }

    public BackgroundMap(final Maps map){
        this.map = map;

        setMap(this.map);
    }

    private void initMap() {
        switch(this.map){
            case CAVE:
                this.platformType = Platforms.CAVE_PLATFORM;
                break;
            case CAVE2:
                this.platformType = Platforms.CAVE_PLATFORM2;
                break;
            case CAVE3:
                this.platformType = Platforms.CAVE_PLATFORM3;
                break;
            case HALLOWEEN:
                this.platformType = Platforms.HALLOWEEN_PLATFORM;
                break;
            case LAVA:
                this.platformType = Platforms.LAVA_PLATFORM;
                break;
            case JUNGLE:
            case JUNGLE2:
                this.platformType = Platforms.JUNGLE_PLATFORM;
                break;
            case FOREST:
            case FOREST2:
            case COUNTRYSIDE:
                this.platformType = Platforms.FOREST_PLATFORM;
                break;
            case FUTURISTIC:
                this.platformType = Platforms.FUTURISTIC_PLATFORM;
                break;
            case SCIFI:
                this.platformType = Platforms.SCIFI_PLATFORM;
                break;
            case PLANET:
            case PLANET2:
                this.platformType = Platforms.SCIFI_PLATFORM3;
                break;
            case SPACE:
                this.platformType = Platforms.SPACE_PLATFORM;
                break;
            case SPACESHIP:
                this.platformType = Platforms.SCIFI_PLATFORM2;
                break;
            case ICE:
            case ICE2:
                this.platformType = Platforms.ICE_PLATFORM;
                break;
            case SWAMP:
            case SWAMP2:
                this.platformType = Platforms.SWAMP_PLATFORM;
                break;
            case DESERT2:
                this.platformType = Platforms.DESERT_PLATFORM2;
                break;
            case DESERT3:
                this.platformType = Platforms.DESERT_PLATFORM3;
                break;
            case DESERT4:
                this.platformType = Platforms.DESERT_PLATFORM4;
                break;
            case CITY:
            case CITY2:
                this.platformType = Platforms.CRATE_PLATFORM;
                break;
            case DESERT:
            default:
                this.platformType = Platforms.DESERT_PLATFORM;
                break;
        }
    }

    public Maps getMap(){
        return this.map;
    }

    public void setMap(final Maps map) { //TODO: renome in mapSetter
        this.map = map;

        initMap();
    }

    public int getMapWidth(){
        return BackgroundMap.MAP_WIDTH;
    }

    public int getMapHeight(){
        return BackgroundMap.MAP_HEIGHT;
    }

    public Platforms getPlatformType(){
        return this.platformType;
    }

    public Coins getCoinType(){
        return this.coinType;
    }

}
