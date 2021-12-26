package main.com.ae2dms.enums;

import main.com.ae2dms.entity.events.*;

import java.io.IOException;

/**
 * Event factory
 */
public class eventFactory {
    /**
     * Get corresponding event
     *
     * @param eventName         The event name
     * @throws IOException      If catch the event, throw IOException
     */
    public static void getEvent(String eventName) throws IOException {
        if(eventName.equals("toggle music")) {
            toggleMusic event = new toggleMusic();
            event.eventHandle();
        } else if (eventName.equals("close game")) {
            closeGame event = new closeGame();
            event.eventHandle();
        } else if (eventName.equals("show about")) {
            showAbout event = new showAbout();
            event.eventHandle();
        } else if (eventName.equals("show victory message")) {
            showVictoryMessage event = new showVictoryMessage();
            event.eventHandle();
        } else if (eventName.equals("load game")) {
            loadGame event = new loadGame();
            event.eventHandle();
        } else if (eventName.equals("undo")) {
            undo event = new undo();
            event.eventHandle();
        } else if (eventName.equals("reset level")) {
            resetLevel event = new resetLevel();
            event.eventHandle();
        } else if (eventName.equals("toggle debug")) {
            toggleDebug event = new toggleDebug();
            event.eventHandle();
        } else if (eventName.equals("save game")) {
            saveGame event = new saveGame();
            event.eventHandle();
        }
    }
}
