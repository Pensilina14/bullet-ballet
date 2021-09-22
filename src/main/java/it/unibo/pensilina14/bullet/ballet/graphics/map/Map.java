package it.unibo.pensilina14.bullet.ballet.graphics.map;

import com.badlogic.gdx.Game;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.Scene;

public class Map extends Game {

    public enum Maps {
        HALLOWEEN,
        JUNGLE,
        FOREST,
        CAVE
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
        setScreen(new Scene());
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

}
