package it.unibo.pensilina14.bullet.ballet.graphics.map;

import it.unibo.pensilina14.bullet.ballet.graphics.sprite.Sprite;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Map {

    private final static int MAP_WIDTH = 1280;
    private final static int MAP_HEIGHT = 720;

    private final Set<Pair<Integer,Integer>> tiles = new HashSet<>();

    private java.util.Map<Sprite, Pair<Integer,Integer>> platforms = new HashMap<>();

    private Sprite platformSprite;

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

        /*public static Stream<Maps> stream() {
            return Stream.of(Maps.values());
        }*/

    }

    private final static Maps DEFAULT_MAP = Maps.HALLOWEEN;

    private final Random rand = new Random();

    private String PLATFORM_SPRITE = "res/assets/maps/Tiles/tmw_desert_spacing.png";

    private Maps map;

    public Map(){ //TODO: maybe not necessary.
        this.map = Map.DEFAULT_MAP;
    }

    //TODO: metodo da tenere qui o da mettere in MapScene
    public void generate(){
        //TODO: posizionare a y : 0 (ovvero, HEIGHT : 0 + qualcosa) le piastrelle, capire quante piastrelle ci stanno nella larghezza della mappa
        //TODO: quindi fare HEIGHT / larghezza_di_una_piastrella = numero_piastrelle che ci stanno nello schermo.
        //TODO: aggiungere le coordinate di queste piastrelle in un Unmodifiable Set.
        //TODO: però quando il player arriva a metà della mappa, la mappa si deve aggiornare mostrando nuove piastrelle (tiles) in nuove posizioni.
        //TODO: Le posizioni delle piastrelle dovranno essere aggiunte alle entità fisiche e quindi ci sarà una collisione/fisica che fa stare il player sopra esse.

        this.platformSprite = new Sprite(265,199);
        this.platformSprite.setSpriteImage(PLATFORM_SPRITE);

        int numTiles = (Map.MAP_WIDTH / this.platformSprite.getSpriteWidth()); // larghezza mappa / larghezza sprite

        List<Sprite> spriteList = new ArrayList<>();
        List<Pair<Integer,Integer>> platformsPositions = new ArrayList<>();

        for(int i = 0; i < numTiles; i++){
            spriteList.add(new Sprite(265,199));

            platformsPositions.add(new Pair<>(i * spriteList.get(i).getSpriteWidth(), Map.MAP_HEIGHT - spriteList.get(i).getSpriteHeight())); //this.platformSprite.getSpriteHeight()));

            this.platforms.put(spriteList.get(i), platformsPositions.get(i));
        }

        spriteList.forEach( s -> {
            s.setSpriteImage(PLATFORM_SPRITE);
        });

    }

    //TODO: if player moves to the right, if offset > (widht/2) && offset < width - (width/2), then Pane.setLayoutX(-(offset - (width/2))
    //TODO: Pane.getChildren().addAll(background, Mappa, UI)
    //TODO: if isPressed(Keycode.W) >= x then movePlayer(-x)
    //TODO: player jump y = - (negative) value.

    public String getMap(){
        return this.map.getPath();
    }

    public void setMap(Maps map){
        this.map = map;
    }

    public String mapChooser(){
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

    public java.util.Map<Sprite, Pair<Integer,Integer>> getPlatforms(){
        return this.platforms;
    }

    public Set<Pair<Integer,Integer>> getTiles(){
        return this.tiles;
    }

    public Sprite getPlatformSprite(){
        return this.platformSprite;
    }

    public void setPlatformSprite(Sprite platformSprite){
        this.platformSprite = platformSprite;
    }

    public int getMapWidth(){
        return Map.MAP_WIDTH;
    }

    public int getMapHeight(){
        return Map.MAP_HEIGHT;
    }
}
