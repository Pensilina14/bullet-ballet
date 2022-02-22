package it.unibo.pensilina14.bullet.ballet.sounds;

import java.util.List;
import java.util.Random;

import javafx.scene.media.AudioClip;

public class SoundsFactoryImpl implements SoundsFactory{
	
	private final Random random = new Random();
	
	@Override
	public Sound createSound(final Sounds sound) {
		final AudioClip audioClip = new AudioClip(this.getClass().getResource(sound.toString()).toExternalForm());
		return new SoundImpl(audioClip);
	}

	@Override
	public Sound createRandomSoundtrack() {
		final List<Sounds> soundtracks = List.of(Sounds.SOUNDTRACK, Sounds.SOUNDTRACK_PAXX, Sounds.SOUNDTRACK_SALVEENE);
		final int randomIndex = random.nextInt(soundtracks.size());
		final AudioClip audioClip = new AudioClip(this.getClass().getResource(soundtracks.get(randomIndex).toString())
				.toExternalForm());
		return new SoundImpl(audioClip);
	}
	
}
