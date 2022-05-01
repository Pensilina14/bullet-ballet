package it.unibo.pensilina14.bullet.ballet.sounds;

import javafx.scene.media.AudioClip;

import java.util.List;
import java.util.Random;

public class SoundsFactoryImpl implements SoundsFactory {

  private final Random random = new Random();

  @Override
  public Sound createSound(final Sounds sound) {
    final AudioClip audioClip =
        new AudioClip(this.getClass().getResource(sound.toString()).toExternalForm());
    return new SoundImpl(audioClip);
  }

  @Override
  public Sound createRandomSoundtrack() {
    final List<Sounds> shuffle = getShuffle();
    final int randomIndex = random.nextInt(shuffle.size());
    final AudioClip audioClip =
        new AudioClip(
            this.getClass().getResource(shuffle.get(randomIndex).toString()).toExternalForm());
    return new SoundImpl(audioClip);
  }

  private List<Sounds> getShuffle() {
    return List.of(
        Sounds.SOUNDTRACK_ENNIO,
        Sounds.SOUNDTRACK_GAMEBOY,
        Sounds.SOUNDTRACK_GANGSTA,
        Sounds.SOUNDTRACK_MORTY,
        Sounds.SOUNDTRACK_NINJA,
        Sounds.SOUNDTRACK_PAXX,
        Sounds.SOUNDTRACK_RIDER,
        Sounds.SOUNDTRACK_SPACE,
        Sounds.SOUNDTRACK_TOM);
  }
}
