package main.com.ae2dms.enums;
import main.com.ae2dms.entity.music.backgroundMusic;
import main.com.ae2dms.entity.music.bombMusic;

/**
 * Music factory
 */
public class MusicFactory {

    /**
     * Get corresponding music
     *
     * @param eventName         Music type
     */
    public static void PlayMusic(String eventName) {
        if (eventName.equals("play background music")) {
            backgroundMusic music = new backgroundMusic();
            music.musicPlayer();
        } else if (eventName.equals("play bomb music")) {
            bombMusic music = new bombMusic();
            music.musicPlayer();
        }
    }
}
