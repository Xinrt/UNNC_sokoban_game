package main.com.ae2dms.util;

import java.awt.*;
import java.util.Iterator;


/**
 * Grid of game map
 */
public class GameGrid implements Iterable {
    final int COLUMNS;
    final int ROWS;
    private GameObject[][] gameObjects;

    /**
     * Input the game grid and initialize a two-dimensional array of game objects
     *
     * @param columns       Integer value of columns in game map
     * @param rows          Integer value of rows in game map
     */
    public GameGrid(int columns, int rows) {
        COLUMNS = columns;
        ROWS = rows;

        // Initialize the array
        gameObjects = new GameObject[COLUMNS][ROWS];
    }


    /**
     * Shift object's location in source to a new one
     *
     * @param sourceLocation    Object location in the source
     * @param delta         The distance of object moving
     * @return              A new location point
     */
    public static Point translatePoint(Point sourceLocation, Point delta) {
        Point translatedPoint = new Point(sourceLocation);
        translatedPoint.translate((int) delta.getX(), (int) delta.getY());
        return translatedPoint;
    }

    /**
     * Get object from source
     *
     * @param source        Object location in source
     * @param delta         The distance of object moving
     * @return              New game object
     */
    public GameObject getTargetFromSource(Point source, Point delta) {
        return getGameObjectAt(translatePoint(source, delta));
    }

    /**
     * Give values to {@code gameObjects[col][row]}, if knows column and row value
     *
     * @param col       Integer value of columns
     * @param row       Integer value of rows
     * @return          New {@code gameObjects[col][row]}
     * @throws ArrayIndexOutOfBoundsException       If location of an object is out of the map, throw {@code ArrayIndexOutOfBoundsException}
     */
    public GameObject getGameObjectAt(int col, int row) throws ArrayIndexOutOfBoundsException {
        if (isPointOutOfBounds(col, row)) {
            if (GameEngine.isDebugActive()) {
                System.out.printf("Trying to get null GameObject from COL: %d  ROW: %d", col, row);
            }
            throw new ArrayIndexOutOfBoundsException("The point [" + col + ":" + row + "] is outside the map.");
        }
        return gameObjects[col][row];
    }

    /**
     * Give values to {@code gameObjects[col][row]}, if knows the {@code Point} value
     *
     * @param p         Integration position of a object
     * @return          New {@code gameObjects[col][row]}
     */
    public GameObject getGameObjectAt(Point p) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null.");
        }

        return getGameObjectAt((int) p.getX(), (int) p.getY());
    }

    /**
     * Remove game object at the given {@code Point} position
     *
     * @param position      Position of an object
     * @return              New {@code gameObjects[col][row]}
     */
    public boolean removeGameObjectAt(Point position) {
        return putGameObjectAt(null, position);
    }

    /**
     * Put the object into a certain point
     *      if the point is valid, return true
     *      if the point is out of bound, return false
     *
     * @param gameObject        A game object
     * @param x                 x coordinate of a new location
     * @param y                 y coordinate of a new location
     * @return                  New {@code gameObjects[col][row]} if game object inside the bound
     *                          false, if game object out of the bound
     */
    public boolean putGameObjectAt(GameObject gameObject, int x, int y) {
        if (isPointOutOfBounds(x, y)) {
            return false;
        }

        gameObjects[x][y] = gameObject;
        return gameObjects[x][y] == gameObject;
    }

    /**
     * Same function as {@code public boolean putGameObjectAt(GameObject gameObject, int x, int y)}
     * But using a {@code Point} value
     *
     * @param gameObject            A game object
     * @param p                     integration position of a new location
     * @return                      New {@code gameObjects[col][row]} if game object inside the bound
     *                              false, if game object out of the bound
     */
    public boolean putGameObjectAt(GameObject gameObject, Point p) {
        return p != null && putGameObjectAt(gameObject, (int) p.getX(), (int) p.getY());
    }

    /**
     * Determine whether the location of an object is out of bound
     *
     * @param x             x coordinate of the object location
     * @param y             y coordinate of the object location
     * @return              true/false
     */
    private boolean isPointOutOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x >= COLUMNS || y >= ROWS);
    }

    /**
     * Determine whether the location of an object is out of bound
     *
     * @param p             integration position of an object
     * @return              true/false
     */
    private boolean isPointOutOfBounds(Point p) {
        return isPointOutOfBounds(p.x, p.y);
    }

    /**
     * Transfer {@code Grid} type into a string format
     *
     * @return              String format of a grid
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(gameObjects.length);

        for (GameObject[] gameObject : gameObjects) {
            for (GameObject aGameObject : gameObject) {
                if (aGameObject == null) {
                    aGameObject = GameObject.DEBUG_OBJECT;
                }
                sb.append(aGameObject.getCharSymbol());
            }

            sb.append('\n');
        }

        return sb.toString();
    }

    /**
     * Transfer string back to {@code Grid} type
     *
     * @param inputString           A string
     * @return                      A grid
     */
    public static GameGrid toGrid(String inputString) {
        int newRow = 0;
        int newCol = 0;
        while(inputString.charAt(newCol) != '\n')
        {
            newCol++;
        }

        for(int i=0; i<inputString.length(); i++)
        {
            if(inputString.charAt(i) == '\n')
            {
                newRow++;
            }
        }

        GameGrid outputGrid = new GameGrid(newRow, newCol);
        inputString = inputString.replaceAll("[\\n\\t\\r]", "");
        for(int row=0; row<newRow; row++)
        {
            for(int col=0; col<newCol; col++)
            {
                GameObject object = GameObject.fromChar(inputString.charAt(row*newCol+col));
                outputGrid.putGameObjectAt(object, row, col);
            }
        }
        return outputGrid;
    }

    /**
     * get game object continuously
     * @return          grid
     */
    @Override
    public Iterator<GameObject> iterator() {
        return new GridIterator(this);
    }

}