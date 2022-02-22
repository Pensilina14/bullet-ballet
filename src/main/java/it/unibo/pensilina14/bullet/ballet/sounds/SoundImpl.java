package it.unibo.pensilina14.bullet.ballet.sounds;

import java.util.Map;
import java.util.Optional;

import it.unibo.pensilina14.bullet.ballet.save.Save;
import javafx.scene.media.AudioClip;

public class SoundImpl implements Sound{

	private static final double NO_VOLUME = 0.0;
	private final Optional<AudioClip> audioClip;
	private final double volume;
	
	public SoundImpl(final AudioClip audioClip) {
		this.audioClip = Optional.of(audioClip);
		final Map<String, String> settingsMap = Save.loadSettings();
		this.volume = !settingsMap.isEmpty() ? Double.parseDouble(settingsMap.get(Save.AUDIO_STRING)) : SoundImpl.NO_VOLUME;
	}

	@Override
	public void play() {
		this.audioClip.ifPresent(audioClip -> audioClip.play(volume));
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
		return this.volume;
	}

}
