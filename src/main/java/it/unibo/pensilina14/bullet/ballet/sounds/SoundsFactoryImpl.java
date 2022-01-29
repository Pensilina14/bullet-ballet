package it.unibo.pensilina14.bullet.ballet.sounds;

import javafx.scene.media.AudioClip;

public class SoundsFactoryImpl implements SoundsFactory{

	@Override
	public AudioClip createSound(final Sounds sound) {
		return new AudioClip(this.getClass().getResource(sound.toString()).toExternalForm());
	}

}
