package it.unibo.pensilina14.bullet.ballet.graphics.map;

import java.util.*;

public class Map {

    public enum Maps { //TODO: mettere questo enum a parte.
        HALLOWEEN("res/assets/maps/Backgrounds/spooky_background.jpg"),
        JUNGLE("res/assets/maps/Backgrounds/jungle_background.jpg"),
        JUNGLE2("res/assets/maps/Backgrounds/jungle_background2.jpg"),
        FOREST("res/assets/maps/Backgrounds/forest_background.png"),
        SWAMP("res/assets/maps/Backgrounds/swamp_background.jpg"),
        CAVE("res/assets/maps/Backgrounds/cave_background.png"),
        CAVE2("res/assets/maps/Backgrounds/cave_background2.jpg"),
        CAVE3("res/assets/maps/Backgrounds/cave_background3.jpg"),
        LAVA("res/assets/maps/Backgrounds/lava_background.png"),
        DESERT("res/assets/maps/Backgrounds/desert_background.jpg"),
        DESERT2("res/assets/maps/Backgrounds/desert_background2.jpg"),
        DESERT3("res/assets/maps/Backgrounds/desert_background3.jpg"),
        DESERT4("res/assets/maps/Backgrounds/desert_background4.png"),
        FUTURISTIC("res/assets/maps/Backgrounds/futuristic_background.jpg"),
        ICE("res/assets/maps/Backgrounds/ice_background.jpg"),
        ICE2("res/assets/maps/Backgrounds/ice_background2.png");

        String path;

        Maps(String path){
            this.path = path;
        }

        public String getPath(){
            return this.path;
        }

    }

    private Maps map;

    public Map(){
        this.map = mapChooser(); //TODO: o scelgo la piattaforma a caso, oppure chiamo initMap ed in base alla mappa setto la piattaforma.

        //this.platformType = Platform.Platforms.DESERT_PLATFORM4; //TODO: questo solo per testare una specifica platform

        Coin coin = new Coin();
        this.coinType = coin.coinChooser();

        initMap();

    }

    public Map(Maps map){
        this.map = map;

        setMap(this.map);
    }

    private final static int MAP_WIDTH = 1280;
    private final static int MAP_HEIGHT = 720;

    private final static Maps DEFAULT_MAP = Maps.HALLOWEEN;

    private Platform.Platforms platformType;
    private Coin.Coins coinType;

    private void initMap() {
        switch(this.map){
            case CAVE:
                this.platformType = Platform.Platforms.CAVE_PLATFORM;
                break;
            case CAVE2:
                this.platformType = Platform.Platforms.CAVE_PLATFORM2;
                break;
            case CAVE3:
                this.platformType = Platform.Platforms.CAVE_PLATFORM3;
                break;
            case HALLOWEEN:
                this.platformType = Platform.Platforms.HALLOWEEN_PLATFORM;
                break;
            case LAVA:
                this.platformType = Platform.Platforms.LAVA_PLATFORM;
                break;
            case JUNGLE:
            case JUNGLE2:
                this.platformType = Platform.Platforms.JUNGLE_PLATFORM;
                break;
            case FOREST:
                this.platformType = Platform.Platforms.FOREST_PLATFORM;
                break;
            case FUTURISTIC:
                this.platformType = Platform.Platforms.FUTURISTIC_PLATFORM;
                break;
            case ICE:
            case ICE2:
                this.platformType = Platform.Platforms.ICE_PLATFORM;
                break;
            case SWAMP:
                this.platformType = Platform.Platforms.SWAMP_PLATFORM;
                break;
            case DESERT2:
                this.platformType = Platform.Platforms.DESERT_PLATFORM2;
                break;
            case DESERT3:
                this.platformType = Platform.Platforms.DESERT_PLATFORM3;
                break;
            case DESERT4:
                this.platformType = Platform.Platforms.DESERT_PLATFORM4;
                break;
            case DESERT:
            default:
                this.platformType = Platform.Platforms.DESERT_PLATFORM;
                break;
        }
    }

    public Maps mapChooser(){ //TODO: far restituire una Maps al posto di una stringa.
        final Random rand = new Random();
        final int max = Maps.values().length;
        final int min = 0;
        final int randomMap = rand.nextInt(((max - min)) + min);
        for(Maps m : Maps.values()){
            if(m.ordinal() == randomMap){
                return m;
            }
        }
        return Map.DEFAULT_MAP;
    }

    public Maps getMap(){
        return this.map;
    }

    public void setMap(Maps map) { //TODO: renome in mapSetter
        this.map = map;

        initMap();
    }

    public int getMapWidth(){
        return Map.MAP_WIDTH;
    }

    public int getMapHeight(){
        return Map.MAP_HEIGHT;
    }

    public Platform.Platforms getPlatformType(){
        return this.platformType;
    }

    public Coin.Coins getCoinType(){
        return this.coinType;
    }

}
