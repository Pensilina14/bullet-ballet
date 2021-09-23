package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.prism.image.ViewPort;
import it.unibo.pensilina14.bullet.ballet.graphics.map.Map;

public class Scene implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    //private port = new FitViewport();

    private final Map mappa = new Map();

    /*public Scene(Map mappa){
        this.mappa = mappa;
    }*/

    public Scene(){

    }

    @Override
    public void show() {
        this.map = new TmxMapLoader().load("res/assets/maps/test2.tmx");

        this.renderer = new OrthogonalTiledMapRenderer(this.map);

        this.camera = new OrthographicCamera();
        //this.camera.position.set(mappa.getWidth() / 2, mappa.getHeight() / 2);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.renderer.setView(this.camera);
        this.renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        this.camera.viewportWidth = width;
        this.camera.viewportHeight = height;

        this.camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        this.map.dispose();

        this.renderer.dispose();
    }

    public void update(float dt){

    }

    public void InputHandler(float dt){
        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX() , Gdx.input.getY(), 0);
            this.camera.unproject(touchPos);
            //bucket.x = touchPos.x - 64 / 2;
        }
    }
}
