package main.com.ae2dms.entity.events;

import main.com.ae2dms.entity.music.backgroundMusic;
import main.com.ae2dms.controller.TouristController;
import main.com.ae2dms.util.eventHandleInterface;

/**
 * Toggle music entity
 */
public class toggleMusic implements eventHandleInterface {

    @Override
    public void eventHandle() {

        if(TouristController.toggleMusicTimes%2 != 0){
            backgroundMusic.backgroundMusicClip.stop();

        } else {
            backgroundMusic.backgroundMusicClip.start();
        }
    }
}
