package it.unibo.pensilina14.bullet.ballet.graphics.map;

import java.util.*;

public class Map {

    public enum Maps {
        HALLOWEEN("res/assets/maps/Backgrounds/spooky_background.jpg"),
        JUNGLE("res/assets/maps/Backgrounds/jungle_background.jpg"),
        JUNGLE2("res/assets/maps/Backgrounds/jungle_background2.jpg"),
        JUNGLE3("res/assets/maps/Backgrounds/jungle_background3.png"),
        //FOREST("res/assets/maps/Backgrounds/"),
        CAVE("res/assets/maps/Backgrounds/cave_background.png"),
        CAVE2("res/assets/maps/Backgrounds/cave_background2.jpg"),
        CAVE3("res/assets/maps/Backgrounds/cave_background3.jpg"),
        LAVA("res/assets/maps/Backgrounds/lava_background.png"),
        DESERT("res/assets/maps/Backgrounds/desert_background.jpg"),
        DESERT2("res/assets/maps/Backgrounds/desert_background2.jpg"),
        FUTURISTIC("res/assets/maps/Backgrounds/futuristic_background.jpg");

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
        this.map = Map.DEFAULT_MAP; //TODO: al posto della default map, mettere mapChooser()

        this.platformType = Platform.Platforms.DESERT_PLATFORM; //TODO: per ora default, ma poi dovrà essere casuale.

    }

    public Map(Maps map){
        this.map = map;

        setMap(this.map);
    }

    private final static int MAP_WIDTH = 1280;
    private final static int MAP_HEIGHT = 720;

    private final static Maps DEFAULT_MAP = Maps.HALLOWEEN;

    private final Random rand = new Random();

    private Platform.Platforms platformType;

    private void initMap(Maps map) { //TODO: non serve passarci il parametro, perchè posso direttamente prenderlo dal campo.
        switch(this.map){
            case DESERT:
                //this.platform = new Platform(Platform.Platforms.DESERT_PLATFORM);
                //this.platform = new Platform2(Platform2.Platforms.DESERT_PLATFORM);
                this.platformType = Platform.Platforms.DESERT_PLATFORM; //TODO: mi basta scegliere la piattaforma, non serve crearla.
                break;
        }
    }

    public String mapChooser(){ //TODO: far restituire una Maps al posto di una stringa.
        final int max = Maps.values().length;
        final int min = 0;
        final int randomMap = rand.nextInt(((max - min) + 1 ) + min);
        for(Maps m : Maps.values()){
            if(m.ordinal() == randomMap){
                return m.getPath();
            }
        }
        return Map.DEFAULT_MAP.getPath();
    }

    public Maps getMap(){
        return this.map;
    }

    public void setMap(Maps map) { //TODO: nome mapSetter
        this.map = map;

        //TODO: aggiungere la tipologia di platform

        initMap(map);
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

}
