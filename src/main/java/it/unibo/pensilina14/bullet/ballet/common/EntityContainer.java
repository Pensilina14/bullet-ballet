package it.unibo.pensilina14.bullet.ballet.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.model.characters.Enemy;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Platform;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Item;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;

public class EntityContainer implements Container {

	private final Optional<List<Enemy>> enemies;
    private final Optional<List<PhysicalObject>> obstacles;
    private final Optional<List<Item>> items;
    private final Optional<List<Platform>> platforms;
    private final Optional<List<Weapon>> weapons;

	public EntityContainer() {
		this.enemies = Optional.of(new ArrayList<>());
		this.obstacles = Optional.of(new ArrayList<>());
		this.items = Optional.of(new ArrayList<>());
		this.platforms = Optional.of(new ArrayList<>());
		this.weapons = Optional.of(new ArrayList<>());
	}


	@Override
	public final boolean isEmpty() {
		return this.enemies.isEmpty() && this.obstacles.isEmpty() 
				&& this.items.isEmpty() && this.platforms.isEmpty() && this.weapons.isEmpty();
	}
	
	public final Optional<List<Enemy>> getEnemies() {
		if (this.enemies.isPresent()) {
			return Optional.of(List.copyOf(this.enemies.get()));
		}
		return Optional.empty();
	}
	
	public final Optional<List<PhysicalObject>> getObstacles() {
		if (this.obstacles.isPresent()) {
			return Optional.of(List.copyOf(this.obstacles.get()));
		}
		return Optional.empty();
	}
	
	public final Optional<List<Item>> getItems() {
		if (this.items.isPresent()) {
			return Optional.of(List.copyOf(this.items.get()));
		}
		return Optional.empty();
	}
	
	public final Optional<List<Weapon>> getWeapon() {
		if (this.weapons.isPresent()) {
			return Optional.of(List.copyOf(this.weapons.get()));
		}
		return Optional.empty();
	}
}
