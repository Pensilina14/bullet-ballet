package it.unibo.pensilina14.bullet.ballet.sounds;


public interface SoundsFactory {
	
	/**
	 * 
	 * @param sound
	 * @return a Sound object containing a referement to AudioClip
	 */
	Sound createSound(final Sounds sound);
}
