package main.com.ae2dms.entity.events;

import main.com.ae2dms.util.eventHandleInterface;

/**
 * Close game entity
 */
public class closeGame implements eventHandleInterface {
    @Override
    public void eventHandle() {
        System.exit(0);
    }
}
