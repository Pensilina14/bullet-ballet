package it.unibo.pensilina14.bullet.ballet.graphics.map;

public class Map {

    public enum Maps {
        HALLOWEEN("res/src/assets/Backgrounds/"),
        JUNGLE("res/src/assets/Backgrounds/"),
        FOREST("res/src/assets/Backgrounds/"),
        CAVE("res/src/assets/Backgrounds/");

        String path;

        Maps(String path){
            this.path = path;
        }

        public String getPath(){
            return this.path;
        }
    }
}
