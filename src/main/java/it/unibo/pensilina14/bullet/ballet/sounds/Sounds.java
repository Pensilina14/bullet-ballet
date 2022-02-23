package it.unibo.pensilina14.bullet.ballet.sounds;

public enum Sounds {
	
    APPLAUSE("/applause.mp3"),
    COIN("/coin.mp4"),
    DAMAGE("/damage.mp4"),
    FALL("/fall-2.mp4"),
    HEALTH_INCREMENT("/healtIncrement.mp4"),
    JUMP("/jump2.mp4"),
    MENU_SOUND("/menu_sound.mp4"),
    SHOT("/shot2.mp4"),
    SOUNDTRACK_PAXX("/soundtrack_paxx.mp3"),
    SOUNDTRACK_ENNIO("/soundtrackennio.mp3"),
    SOUNDTRACK_GAMEBOY("/soundtrackgameboy.mp3"),
    SOUNDTRACK_GANGSTA("/soundtrackgangsta.mp3"),
    SOUNDTRACK_MORTY("/soundtrackmorty.mp3"),
    SOUNDTRACK_RIDER("/soundtrackrider.mp3"),
    SOUNDTRACK_SPACE("/soundtrackspace.mp3"),
    SOUNDTRACK_TOM("/takeonmesoundtrack.mp3"),
    SOUNDTRACK_NINJA("/ninjasoundtrack.mp3");
	
	private final String string;

	Sounds(final String string) {
		this.string = string;
	}
	
	@Override
	public String toString() {
		return this.string;
	}
	
}
