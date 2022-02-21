package it.unibo.pensilina14.bullet.ballet.sounds;

import javafx.scene.media.AudioClip;

public class SoundsFactoryImpl implements SoundsFactory{

	@Override
	public Sound createSound(final Sounds sound) {
		final AudioClip audioClip = new AudioClip(this.getClass().getResource(sound.toString()).toExternalForm());
		return new SoundImpl(audioClip);
	}
	
}
