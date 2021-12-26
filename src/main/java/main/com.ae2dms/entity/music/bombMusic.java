package main.com.ae2dms.entity.music;

import main.com.ae2dms.util.Music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Play bomb music
 */
public class bombMusic implements Music {
    /**
     * Load bomb music and play it
     */
    @Override
    public void musicPlayer() {
        AudioInputStream playMusic;
        Clip clip;
        try {
            File music = new File("src/main/resources/music/bomb.wav");

            if(music.exists())
            {
                playMusic = AudioSystem.getAudioInputStream(music);
                clip = AudioSystem.getClip();
                clip.open(playMusic);
                clip.start();
            } else {
                throw new Exception("Fall to play music");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
