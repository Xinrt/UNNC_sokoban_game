package main.com.ae2dms.util;

import main.com.ae2dms.util.GameObject;
import main.com.ae2dms.util.Level;

import java.awt.*;
import java.util.Iterator;

/**
 * Iterator of get objects from levels
 */
public class LevelIterator implements Iterator<GameObject> {

    private final Level gameObjects;
    int column = 0;
    int row = 0;

    /**
     * @param gameObjects       Game objects from super {@code Level}
     */
    public LevelIterator(Level gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * Judge whether there has next game object
     *
     * @return      true/false
     */
    @Override
    public boolean hasNext() {
        return !(row == gameObjects.objectsGrid.ROWS - 1 && column == gameObjects.objectsGrid.COLUMNS);
    }

    /**
     * Get the next game object
     *
     * @return      A game object
     */
    @Override
    public GameObject next() {
        if (column >= gameObjects.objectsGrid.COLUMNS) {
            column = 0;
            row++;
        }
        GameObject object = gameObjects.objectsGrid.getGameObjectAt(column, row);
        GameObject diamond = gameObjects.diamondsGrid.getGameObjectAt(column, row);
        GameObject retObj = object;
        column++;
        if (diamond == GameObject.DIAMOND) {
            if (object == GameObject.CRATE) {
                retObj = GameObject.CRATE_ON_DIAMOND;
            } else if (object == GameObject.FLOOR) {
                retObj = diamond;
            } else {
                retObj = object;
            }
        }
        return retObj;
    }

    /**
     * Help to transmit the current location value of a game object
     *
     * @return      Current location of a game object
     */
    public Point getcurrentposition() {
        return new Point(column, row);
    }
}
