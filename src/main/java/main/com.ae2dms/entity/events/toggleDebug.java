package main.com.ae2dms.entity.events;

import main.com.ae2dms.controller.TouristController;
import main.com.ae2dms.util.GameEngine;
import main.com.ae2dms.util.eventHandleInterface;

/**
 * Toggle debug entity
 */
public class toggleDebug implements eventHandleInterface {

    public static GameEngine gameEngine;

    @Override
    public void eventHandle() {
        gameEngine.toggleDebug();
    }
}
