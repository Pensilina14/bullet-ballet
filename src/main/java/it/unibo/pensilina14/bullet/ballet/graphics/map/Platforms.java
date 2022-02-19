package it.unibo.pensilina14.bullet.ballet.graphics.map;

public enum Platforms {
    HALLOWEEN_PLATFORM("res/assets/maps/Tiles/halloween_tile.png"),
    CAVE_PLATFORM("res/assets/maps/Tiles/rock_tile2.png"),
    CAVE_PLATFORM2("res/assets/maps/Tiles/rock_tile4.png"),
    CAVE_PLATFORM3("res/assets/maps/Tiles/rock_tile.jpg"),
    JUNGLE_PLATFORM("res/assets/maps/Tiles/jungle_rock_tile.png"),
    FOREST_PLATFORM("res/assets/maps/Tiles/grass_tile.png"),
    SWAMP_PLATFORM("res/assets/maps/Tiles/Swamp-tile.jpg"),
    LAVA_PLATFORM("res/assets/maps/Tiles/brick_wall-red.png"),
    DESERT_PLATFORM("res/assets/maps/Tiles/desert_platform2.png"),
    DESERT_PLATFORM2("res/assets/maps/Tiles/desert-tile.png"),
    DESERT_PLATFORM3("res/assets/maps/Tiles/desert_platform3.png"),
    DESERT_PLATFORM4("res/assets/maps/Tiles/desert_platform4.png"),
    ICE_PLATFORM("res/assets/maps/Tiles/ice_tile3.png"),
    FUTURISTIC_PLATFORM("res/assets/maps/Tiles/scifi_tile.jpg"),
    SCIFI_PLATFORM("res/assets/maps/Tiles/scifi_tile3.jpg"),
    SCIFI_PLATFORM2("res/assets/maps/Tiles/scifi_tile2.jpg"),
    SCIFI_PLATFORM3("res/assets/maps/Tiles/scifi_tile.jpg"),
    SPACE_PLATFORM("res/assets/maps/Tiles/space_platform.jpg"),
    CRATE_PLATFORM("res/assets/maps/Tiles/crates_tile.png");

    private final String path;

    Platforms(final String path){
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }
}
