package it.unibo.pensilina14.bullet.ballet.graphics.map;

import java.util.*;

public class BackgroundMap {

    public enum Maps { //TODO: mettere questo enum a parte?
        HALLOWEEN("res/assets/maps/Backgrounds/spooky_background.jpg"),
        JUNGLE("res/assets/maps/Backgrounds/jungle_background.jpg"),
        JUNGLE2("res/assets/maps/Backgrounds/jungle_background2.jpg"),
        FOREST("res/assets/maps/Backgrounds/forest_background.png"),
        FOREST2("res/assets/maps/Backgrounds/forest_background2.jpg"),
        COUNTRYSIDE("res/assets/maps/Backgrounds/countryside_background.jpg"),
        SWAMP("res/assets/maps/Backgrounds/swamp_background.jpg"),
        SWAMP2("res/assets/maps/Backgrounds/swamp_background2.jpg"),
        CAVE("res/assets/maps/Backgrounds/cave_background.png"),
        CAVE2("res/assets/maps/Backgrounds/cave_background2.jpg"),
        CAVE3("res/assets/maps/Backgrounds/cave_background3.jpg"),
        LAVA("res/assets/maps/Backgrounds/lava_background.png"),
        DESERT("res/assets/maps/Backgrounds/desert_background.jpg"),
        DESERT2("res/assets/maps/Backgrounds/desert_background2.jpg"),
        DESERT3("res/assets/maps/Backgrounds/desert_background3.jpg"),
        DESERT4("res/assets/maps/Backgrounds/desert_background4.png"),
        ICE("res/assets/maps/Backgrounds/ice_background.jpg"),
        ICE2("res/assets/maps/Backgrounds/ice_background2.png"),
        FUTURISTIC("res/assets/maps/Backgrounds/futuristic_background.jpg"),
        //FUTURISTIC3("res/assets/maps/Backgrounds/futuristic_city_background.gif"), //TODO: mi da la nausea, da rimuovere
        SCIFI("res/assets/maps/Backgrounds/scifi_background.jpg"),
        PLANET("res/assets/maps/Backgrounds/scifi_martian_background.jpg"),
        PLANET2("res/assets/maps/Backgrounds/scifi_alien_planet_background.jpg"),
        SPACESHIP("res/assets/maps/Backgrounds/spaceship_interior_background.jpg"),
        SPACE("res/assets/maps/Backgrounds/space_background6.png"),
        //SPACE2("res/assets/maps/Backgrounds/space_pixel_art_background.jpg"), //TODO: ci potrebbe stare
        //SPACE3("res/assets/maps/Backgrounds/space_pixel_art_background2.gif"), //TODO: ci potrebbe quasi stare
        CITY("res/assets/maps/Backgrounds/city_background2.png"),
        CITY2("res/assets/maps/Backgrounds/city_background4.jpg");

        String path;

        Maps(String path){
            this.path = path;
        }

        public String getPath(){
            return this.path;
        }

    }

    private Maps map;

    public BackgroundMap(){
        this.map = mapChooser();
        //this.map = Maps.SPACESHIP; //TODO: questo solo per testare una specifica mappa. (commentare this.map = mapChooser())

        //this.platformType = Platform.Platforms.DESERT_PLATFORM4; //TODO: questo solo per testare una specifica platform (commentare initMap())

        CoinSprite coinSprite = new CoinSprite();
        this.coinType = coinSprite.coinChooser();

        initMap();

    }

    public BackgroundMap(Maps map){
        this.map = map;

        setMap(this.map);
    }

    private final static int MAP_WIDTH = 1280;
    private final static int MAP_HEIGHT = 720;

    private final static Maps DEFAULT_MAP = Maps.HALLOWEEN;

    private PlatformSprite.Platforms platformType;
    private CoinSprite.Coins coinType;

    private void initMap() {
        switch(this.map){
            case CAVE:
                this.platformType = PlatformSprite.Platforms.CAVE_PLATFORM;
                break;
            case CAVE2:
                this.platformType = PlatformSprite.Platforms.CAVE_PLATFORM2;
                break;
            case CAVE3:
                this.platformType = PlatformSprite.Platforms.CAVE_PLATFORM3;
                break;
            case HALLOWEEN:
                this.platformType = PlatformSprite.Platforms.HALLOWEEN_PLATFORM;
                break;
            case LAVA:
                this.platformType = PlatformSprite.Platforms.LAVA_PLATFORM;
                break;
            case JUNGLE:
            case JUNGLE2:
                this.platformType = PlatformSprite.Platforms.JUNGLE_PLATFORM;
                break;
            case FOREST:
            case FOREST2:
            case COUNTRYSIDE:
                this.platformType = PlatformSprite.Platforms.FOREST_PLATFORM;
                break;
            case FUTURISTIC:
                this.platformType = PlatformSprite.Platforms.FUTURISTIC_PLATFORM;
                break;
            case SCIFI:
                this.platformType = PlatformSprite.Platforms.SCIFI_PLATFORM;
                break;
            case PLANET:
            case PLANET2:
                this.platformType = PlatformSprite.Platforms.SCIFI_PLATFORM3;
                break;
            case SPACE:
                this.platformType = PlatformSprite.Platforms.SPACE_PLATFORM;
                break;
            case SPACESHIP:
                this.platformType = PlatformSprite.Platforms.SCIFI_PLATFORM2;
                break;
            case ICE:
            case ICE2:
                this.platformType = PlatformSprite.Platforms.ICE_PLATFORM;
                break;
            case SWAMP:
            case SWAMP2:
                this.platformType = PlatformSprite.Platforms.SWAMP_PLATFORM;
                break;
            case DESERT2:
                this.platformType = PlatformSprite.Platforms.DESERT_PLATFORM2;
                break;
            case DESERT3:
                this.platformType = PlatformSprite.Platforms.DESERT_PLATFORM3;
                break;
            case DESERT4:
                this.platformType = PlatformSprite.Platforms.DESERT_PLATFORM4;
                break;
            case CITY:
            case CITY2:
                this.platformType = PlatformSprite.Platforms.CRATE_PLATFORM;
                break;
            case DESERT:
            default:
                this.platformType = PlatformSprite.Platforms.DESERT_PLATFORM;
                break;
        }
    }

    public Maps mapChooser(){
        final Random rand = new Random();
        final int max = Maps.values().length;
        final int min = 0;
        final int randomMap = rand.nextInt(((max - min)) + min);
        for(Maps m : Maps.values()){
            if(m.ordinal() == randomMap){
                return m;
            }
        }
        return BackgroundMap.DEFAULT_MAP;
    }

    public Maps getMap(){
        return this.map;
    }

    public void setMap(Maps map) { //TODO: renome in mapSetter
        this.map = map;

        initMap();
    }

    public int getMapWidth(){
        return BackgroundMap.MAP_WIDTH;
    }

    public int getMapHeight(){
        return BackgroundMap.MAP_HEIGHT;
    }

    public PlatformSprite.Platforms getPlatformType(){
        return this.platformType;
    }

    public CoinSprite.Coins getCoinType(){
        return this.coinType;
    }

}
