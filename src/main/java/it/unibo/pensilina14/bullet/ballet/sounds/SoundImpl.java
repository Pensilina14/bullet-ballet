package it.unibo.pensilina14.bullet.ballet.sounds;

import java.util.Optional;

import javafx.scene.media.AudioClip;

public class SoundImpl implements Sound{

	private static final double VOLUME = 10;
	private final Optional<AudioClip> audioClip;
	
	public SoundImpl(final AudioClip audioClip) {
		this.audioClip = Optional.of(audioClip);
	}

	@Override
	public void play() {
		this.audioClip.get().play(VOLUME);
	}

	@Override
	public void stop() {
		this.audioClip.get().stop();
	}

	@Override
	public AudioClip getAudioClip() {
		return this.audioClip.get();
	}

	@Override
	public double getCurrentVolume() {
		return VOLUME;
	}

}
