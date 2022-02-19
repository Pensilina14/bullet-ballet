package it.unibo.pensilina14.bullet.ballet.sounds;

import javafx.scene.media.AudioClip;

public interface Sound {

	/**
	 * This method will start the track
	 */
	void play();
	/**
	 * This method will stop the track
	 */
	void stop();
	/**
	 * 
	 * @return AudioClip
	 */
	AudioClip getAudioClip();
	/**
	 * 
	 * @return current audioClip volume
	 */
	double getCurrentVolume();
	
}
