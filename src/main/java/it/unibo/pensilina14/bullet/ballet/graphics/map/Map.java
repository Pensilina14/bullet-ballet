package it.unibo.pensilina14.bullet.ballet.graphics.map;

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
}
