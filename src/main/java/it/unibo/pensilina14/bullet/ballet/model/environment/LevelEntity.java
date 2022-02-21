package it.unibo.pensilina14.bullet.ballet.model.environment;

import java.util.List;

/**
 * This enumeration keeps all the entities that
 * could be present inside the game along 
 * with their symbol reference written in the map file. 
 */
public enum LevelEntity {
	/**
	 * Represents an empty(air) spot.
	 */
    EMPTY('0'),
    /**
     * Represents a {@link Platform} that fills the tile it stays in.
     */
    PLATFORM('1'),
    /**
     * Stands for a coin.
     */
    COIN('2'),
    /**
     * Represents an {@link Obstacle}.
     */
    OBSTACLE('3'),
    /**
     * Outlines a {@link EntityList#Weapons#GUN}.
     */
    GUN('G'),
    /**
     * Outlines a {@link EntityList#Weapons#SHOTGUN}.
     */
    SHOTGUN('S'),
    /**
     * Outlines a {@link EntityList#Weapons#AUTOGUN}.
     */
    AUTOGUN('A'),
    /**
     * Represents the {@link Player}.
     */
    PLAYER('P'),
    /**
     * Figures an heart {@link Item}. For further reference look into {@link Items#HEART}.
     */
    HEART('*'),
    /**
     * Figures a poison {@link Item}. For further reference look into {@link Items#POISON}.
     */
    POISON('x'),
    /**
     * Figures a damage {@link Item}. For further reference look into {@link Items#DAMAGE}.
     */
    DAMAGE('d'),
    /**
     * Represents an {@link Enemy}.
     */
    ENEMY('!');

    private final char value;

    LevelEntity(final char value) {
        this.value = value;
    }

    public final char getValue() { 
        return this.value;
    }
    /**
     * @return a {@link List} containing all the elements of {@link this} enumeration.
     */
    public final List<LevelEntity> getEntities() {
        return List.of(EMPTY, PLATFORM, COIN, OBSTACLE, GUN, SHOTGUN, AUTOGUN, PLAYER, HEART, POISON, DAMAGE, ENEMY);
    }
}
