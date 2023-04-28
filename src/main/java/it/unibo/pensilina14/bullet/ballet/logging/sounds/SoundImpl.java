package it.unibo.pensilina14.bullet.ballet.sounds;

import it.unibo.pensilina14.bullet.ballet.save.Save;
import javafx.scene.media.AudioClip;

import java.util.Map;
import java.util.Optional;

public class SoundImpl implements Sound {

  public static final double DEFAULT_VOLUME = 0.5;
  private final Optional<AudioClip> audioClip;

  public SoundImpl(final AudioClip audioClip) {
    this.audioClip = Optional.of(audioClip);
  }

  @Override
  public void play() {
    final Map<String, String> settingsMap = Save.loadSettings();
    final double volume =
        !settingsMap.isEmpty()
            ? Double.parseDouble(settingsMap.get(Save.AUDIO_STRING))
            : SoundImpl.DEFAULT_VOLUME;
    this.audioClip.get().play(volume);
  }

  @Override
  public void stop() {
    this.audioClip.get().stop();
  }

  @Override
  public AudioClip getAudioClip() {
    return this.audioClip.get();
  }
}
