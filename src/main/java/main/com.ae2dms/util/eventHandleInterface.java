package main.com.ae2dms.util;

import java.io.IOException;

/**
 * The interface of a factory design pattern
 *
 * @author      Xinran TANG
 */
public interface eventHandleInterface {
    /**
     * Event handler
     *
     * @throws IOException      If cannot catch event, throw exception
     */
    void eventHandle() throws IOException;
}
