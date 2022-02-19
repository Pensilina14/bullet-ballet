package it.unibo.pensilina14.bullet.ballet.sounds;

import java.util.Map;
import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.save.Save;
import javafx.scene.media.AudioClip;

public class SoundImpl implements Sound{

	private static final double NO_VOLUME = 0.0;

	private static final double VOLUME = 10; //TODO: remove?
	private final Optional<AudioClip> audioClip;
	
	public SoundImpl(final AudioClip audioClip) {
		this.audioClip = Optional.of(audioClip);
	}

	@Override
	public void play() {
		// Cos' però fa 1 decriptazione, 1 lettura da file e 1 creazione di mappa con tutte le impostazioni ogni volta che viene chiamato play.
		final Map<String, String> settingsMap = Save.loadSettings();
		final double volume = !settingsMap.isEmpty() ? Double.parseDouble(settingsMap.get(Save.AUDIO_STRING)) : SoundImpl.NO_VOLUME;
		this.audioClip.ifPresent(audioClip -> audioClip.play(volume));
		//this.audioClip.get().play(volume); //TODO: remove
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
