package main.com.ae2dms.enums;

import main.com.ae2dms.entity.engine.moveEngine;
import main.com.ae2dms.entity.engine.moveRedEngine;
import main.com.ae2dms.entity.engine.removeObject;
import main.com.ae2dms.util.GameEngine;
import main.com.ae2dms.util.GameObject;

import java.awt.*;
import java.io.IOException;

/**
 * The factory of a factory design pattern to manage {@code move} entities
 *
 * @author      Xinran TANG
 */
public class engineFactory {
    /**
     * Get the movement of blue keeper
     *
     * @param delta     The distance of blue keeper moving
     * @throws IOException      If cannot read from keyboard, throw exception
     */
    public static void getMovement(Point delta) throws IOException {
        moveEngine keeperMove = new moveEngine();
        keeperMove.moveBlueKeeper(delta);
    }

    /**
     * Get the movement of red keeper
     *
     * @param delta     The distance of red keeper moving
     * @throws IOException      If cannot read from keyboard, throw exception
     */
    public static void getRedMovement(Point delta) throws IOException {
        moveRedEngine keeperMove = new moveRedEngine();
        keeperMove.moveRedKeeper(delta);
    }

    /**
     * Remove the up, down, right, left objects of keeper
     *
     * @param keeperPosition        The location of keeper
     */
    public static void remove(Point keeperPosition) {
        removeObject removeSurrouding = new removeObject();
        removeSurrouding.remove(keeperPosition);
    }
}
