package it.unibo.pensilina14.bullet.ballet.graphics.map;

import com.badlogic.gdx.Game;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.Scene;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.Scene2;

public class Map extends Game {

    private final static int WIDTH = 1280;
    private final static int HEIGHT = 720;

    public enum Maps {
        HALLOWEEN("res/src/assets/Backgrounds/"),
        JUNGLE("res/src/assets/Backgrounds/"),
        FOREST("res/src/assets/Backgrounds/"),
        CAVE("res/src/assets/Backgrounds/");

        String path;

        Maps(String path){
            this.path = path;
        }
    }

    private final Maps map;

    public Map(){
        this.map = Maps.HALLOWEEN;
    }

    /*public Map(Maps map){
        this.map = map;
    }*/

    @Override
    public void create() {
        //setScreen(new Scene());
        setScreen(new Scene2());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public int getWidth(){
        return Map.WIDTH;
    }

    public int getHeight(){
        return Map.HEIGHT;
    }

}
