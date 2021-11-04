package it.unibo.pensilina14.bullet.ballet;

import it.unibo.pensilina14.bullet.ballet.menu.controller.Difficulties;
import it.unibo.pensilina14.bullet.ballet.menu.controller.Resolutions;

public class SettingsImpl implements Settings{

	private Resolutions resolution;
	private Difficulties difficulty;
	
	public SettingsImpl(Resolutions resolution, Difficulties difficulty) {
		this.resolution = resolution;
		this.difficulty = difficulty;
	}

	@Override
	public Resolutions getCurrentResolution() {
		return this.resolution;
	}

	@Override
	public Difficulties getCurrentDifficulty() {
		return this.difficulty;
	}

	@Override
	public void setResolution(Resolutions resolution) {
		this.resolution = resolution;
	}

	@Override
	public void setDifficulty(Difficulties difficulty) {
		this.difficulty = difficulty;
	}
	
}
