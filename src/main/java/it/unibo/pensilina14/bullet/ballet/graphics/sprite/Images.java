package it.unibo.pensilina14.bullet.ballet.graphics.sprite;

import java.util.Random;

public enum Images {
	/**
	 * 
	 */
    BULLET("normal-bullet", "res/assets/sprites/bullets/Bullet.png"),
    /**
     * 
     */
    ENEMY("enemy", "res/assets/sprites/characters/enemies/enemy_idle.png"),
    /**
     * 
     */
    POISONING_ITEM("poison", "res/assets/sprites/items/poison_potion_resized0.png"),
    /**
     * 
     */
    HEALING_ITEM("heart", "res/assets/sprites/items/heart.png"),
    /**
     * 
     */
    DAMAGING_ITEM("damage", "res/assets/sprites/items/snail.png"),
    /**
     * 
     */
    BUNNY("bunny", "res/assets/sprites/obstacles/Jump.png"),
    /**
     * 
     */
    WUT("dynamic obstacle", "res/assets/sprites/obstacles/dynamicObstacle.png"),
    /**
     * 
     */
	GUN("Gun", "res/assets/sprites/weapons/gun.png"),
	/**
	 * 
	 */
	SHOTGUN("Shotgun", "res/assets/sprites/weapons/shotgun.png"),
	/**
	 * 
	 */
	AUTO("Autogun", "res/assets/sprites/weapons/auto.png"),
	/**
	 * 
	 */
	AMMO("Ammo", "res/assets/sprites/items/ammoBox.png"),
    /**
     *
     */
    FLAG("Flag", "res/assets/sprites/items/flag_resized2.png");

    private final String fileName;
    private final String objectName;
    private static final Random RAND = new Random();

    Images(final String objectName, final String fileName) {
        this.objectName = objectName;
    	this.fileName = fileName;
    }

    public String getObjectName() {
    	return this.objectName;
    }
    /* 
     * @see java.lang.Enum#toString()
     */

    public String getFileName() {
    	return this.fileName;
    }

    @Override
    public String toString() {
        return this.objectName + "\t" + this.fileName;
    }

    public enum Platforms {
    	/**
    	 * 
    	 */
        HALLOWEEN_PLATFORM("res/assets/maps/Tiles/halloween_tile.png"),
        /**
    	 * 
    	 */
        CAVE_PLATFORM("res/assets/maps/Tiles/rock_tile2.png"),
        /**
    	 * 
    	 */
        CAVE_PLATFORM2("res/assets/maps/Tiles/rock_tile4.png"),
        /**
    	 * 
    	 */
        CAVE_PLATFORM3("res/assets/maps/Tiles/rock_tile.jpg"),
        /**
    	 * 
    	 */
        JUNGLE_PLATFORM("res/assets/maps/Tiles/jungle_rock_tile.png"),
        /**
    	 * 
    	 */
        FOREST_PLATFORM("res/assets/maps/Tiles/grass_tile.png"),
        /**
    	 * 
    	 */
        SWAMP_PLATFORM("res/assets/maps/Tiles/Swamp-tile.jpg"),
        /**
    	 * 
    	 */
        LAVA_PLATFORM("res/assets/maps/Tiles/brick_wall-red.png"),
        /**
    	 * 
    	 */
        DESERT_PLATFORM("res/assets/maps/Tiles/desert_platform2.png"),
        /**
    	 * 
    	 */
        DESERT_PLATFORM2("res/assets/maps/Tiles/desert-tile.png"),
        /**
    	 * 
    	 */
        DESERT_PLATFORM3("res/assets/maps/Tiles/desert_platform3.png"),
        /**
    	 * 
    	 */
        DESERT_PLATFORM4("res/assets/maps/Tiles/desert_platform4.png"),
        /**
    	 * 
    	 */
        ICE_PLATFORM("res/assets/maps/Tiles/ice_tile3.png"),
        /**
    	 * 
    	 */
        FUTURISTIC_PLATFORM("res/assets/maps/Tiles/scifi_tile.jpg"),
        /**
    	 * 
    	 */
        SCIFI_PLATFORM("res/assets/maps/Tiles/scifi_tile3.jpg"),
        /**
    	 * 
    	 */
        SCIFI_PLATFORM2("res/assets/maps/Tiles/scifi_tile2.jpg"),
        /**
    	 * 
    	 */
        SCIFI_PLATFORM3("res/assets/maps/Tiles/scifi_tile.jpg"),
        /**
    	 * 
    	 */
        SPACE_PLATFORM("res/assets/maps/Tiles/space_platform.jpg"),
        /**
    	 * 
    	 */
        CRATE_PLATFORM("res/assets/maps/Tiles/crates_tile.png");

        private final String path;

        Platforms(final String path) {
            this.path = path;
        }

        public String getPath() {
            return this.path;
        }
    }

    public enum Coins {
    	/**
    	 * 
    	 */
        GOLD_COIN("res/assets/sprites/coins/MonedaD.png"),
        /**
    	 * 
    	 */
        SILVER_COIN("res/assets/sprites/coins/MonedaP.png"),
        /**
    	 * 
    	 */
        RED_COIN("res/assets/sprites/coins/MonedaR.png"),
        /**
    	 * 
    	 */
        EMERALD_COIN("res/assets/sprites/coins/spr_coin_strip4.png"),
        /**
    	 * 
    	 */
        LIGHT_BLUE_COIN("res/assets/sprites/coins/spr_coin_azu.png"),
        /**
    	 * 
    	 */
        YELLOW_COIN("res/assets/sprites/coins/spr_coin_ama.png"),
        /**
    	 * 
    	 */
        GREY_COIN("res/assets/sprites/coins/spr_coin_gri.png"),
        /**
    	 * 
    	 */
        RUBY_COIN("res/assets/sprites/coins/spr_coin_roj.png");

        private final String path;

        Coins(final String path) {
            this.path = path;
        }

        public String getPath() {
            return this.path;
        }

        public static Coins getDefaultCoin() {
            return Coins.GOLD_COIN;
        }

        public static Coins coinChooser(){
            final int max = Coins.values().length;
            final int randomMap = RAND.nextInt(max); // nextInt : 0 incluso, max escluso.
            for (final Coins c : Coins.values()) {
                if (c.ordinal() == randomMap) {
                    return c;
                }
            }
            return Coins.getDefaultCoin();
        }

        public static String getRandomCoinPath(){
            final int max = Coins.values().length;
            final int randomMap = RAND.nextInt(max); // nextInt : 0 incluso, max escluso.
            for (final Coins c : Coins.values()) {
                if (c.ordinal() == randomMap) {
                    return c.getPath();
                }
            }
            return Coins.getDefaultCoin().getPath();
        }
    }
}
