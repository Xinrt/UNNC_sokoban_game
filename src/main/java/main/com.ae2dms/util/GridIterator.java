package main.com.ae2dms.util;

import main.com.ae2dms.util.GameGrid;
import main.com.ae2dms.util.GameObject;

import java.util.Iterator;

/**
 * Iterator of creating game grid
 */
public class GridIterator implements Iterator<GameObject> {
    private final GameGrid gameGrid;
    int row = 0;
    int column = 0;

    /**
     * @param gameGrid      Game grid from super {@code GameGrid}
     */
    public GridIterator(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    /**
     * Judge whether there has next game grid
     *
     * @return      true/false
     */
    @Override
    public boolean hasNext() {
        return !(row == gameGrid.ROWS && column == gameGrid.COLUMNS);
    }

    /**
     * Get game object at its position in a game gird
     *
     * @return      A game object
     */
    @Override
    public GameObject next() {
        if (column >= gameGrid.COLUMNS) {
            column = 0;
            row++;
        }
        return gameGrid.getGameObjectAt(column++, row);
    }
}
