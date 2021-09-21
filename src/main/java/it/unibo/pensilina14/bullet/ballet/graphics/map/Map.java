package it.unibo.pensilina14.bullet.ballet.graphics.map;

import com.badlogic.gdx.Game;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.Scene;

/*import org.mapeditor.io.TMXMapReader;
import org.mapeditor.core.Map;
import org.mapeditor.core.MapLayer;
import org.mapeditor.core.TileLayer;
import org.mapeditor.core.Tile;*/

public class Map extends Game {

    public enum Maps {
        HALLOWEEN,
        JUNGLE,
        FOREST,
        CAVE
    }

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
