package it.unibo.pensilina14.bullet.ballet.graphics.map;

import java.util.Random;

public enum Maps {
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

    Maps(final String path){
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }

    public static Maps mapChooser(){
        final int max = Maps.values().length;
        final Random rand = new Random();
        for(final Maps m : Maps.values()){
            if(m.ordinal() == rand.nextInt(max)){
                return m;
            }
        }
        return Maps.HALLOWEEN;
    }

    public String getRandomMapPath(){
        final int max = Maps.values().length;
        final Random rand = new Random();
        for(var m : Maps.values()){
            if(m.ordinal() == rand.nextInt(max)){
                return m.getPath();
            }
        }
        return Maps.HALLOWEEN.getPath();
    }

}
