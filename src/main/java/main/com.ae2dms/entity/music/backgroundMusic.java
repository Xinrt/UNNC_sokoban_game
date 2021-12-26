package main.com.ae2dms.entity.music;

import main.com.ae2dms.util.Music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Play background music
 */
public class backgroundMusic implements Music {
    /**
     * Used to transfer single music controller
     */
    public static Clip backgroundMusicClip;

    /**
     * Load background music and play it
     */
    @Override
    public void musicPlayer() {
        AudioInputStream playMusic;

        try {
            File music = new File("src/main/resources/music/audio1.wav");
            if(music.exists())
            {
                playMusic = AudioSystem.getAudioInputStream(music);
                backgroundMusicClip = AudioSystem.getClip();
                backgroundMusicClip.open(playMusic);
                backgroundMusicClip.start();
                backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                throw new Exception("Fall to play music");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
