package main.com.ae2dms.util;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Game levels
 */
public final class Level implements Iterable<GameObject> {
    public GameGrid objectsGrid;
    public final GameGrid diamondsGrid;
    private final String name;
    private final int index;
    private int numberOfDiamonds = 0;
    private Point keeperPosition = new Point(0, 0);
    private ArrayList<Point> diamondPosition = new ArrayList<>();
    private Point keeperRedPosition = new Point(0, 0);
    private Point icePosition = new Point(0,0);
    private ArrayList<Point> doorPosition = new ArrayList<>();
    private ArrayList<Point> wallPosition = new ArrayList<>();


    /**
     *  Find locations of each game object in {@code Point} type
     *
     * @param levelName         Game level's name in string type
     * @param levelIndex        Game level's index in integer type
     * @param raw_level         A string list store levels
     */
    public Level(String levelName, int levelIndex, List<String> raw_level) {
        if (GameEngine.isDebugActive()) {
            System.out.printf("[ADDING LEVEL] LEVEL [%d]: %s\n", levelIndex, levelName);
        }

        name = levelName;
        index = levelIndex;

        int rows = raw_level.size();
        int columns = raw_level.get(0).trim().length();

        objectsGrid = new GameGrid(rows, columns);
        diamondsGrid = new GameGrid(rows, columns);

        for (int row = 0; row < raw_level.size(); row++) {

            for (int col = 0; col < raw_level.get(row).length(); col++) {
                GameObject curTile = GameObject.fromChar(raw_level.get(row).charAt(col));

                if (curTile == GameObject.DIAMOND) {
                    numberOfDiamonds++;
                    diamondsGrid.putGameObjectAt(curTile, row, col);
                    curTile = GameObject.FLOOR;
                    diamondPosition.add(new Point(row, col));
                } else if (curTile == GameObject.KEEPER) {
                    keeperPosition = new Point(row, col);
                } else if (curTile == GameObject.KEEPERRED) {
                    keeperRedPosition = new Point(row, col);
                } else if (curTile == GameObject.DOOR) {
                    doorPosition.add(new Point(row, col));
                } else if (curTile == GameObject.ICE) {
                    icePosition = new Point(row, col);
                } else if (curTile == GameObject.WALL) {
                    wallPosition.add(new Point(row, col));
                }

                objectsGrid.putGameObjectAt(curTile, row, col);
                curTile = null;
            }
        }
    }

    /**
     * Determine whether the user have passed a level
     *
     * @return          ture/false
     */
    public boolean isComplete() {
        int cratedDiamondsCount = 0;
        for (int row = 0; row < objectsGrid.ROWS; row++) {
            for (int col = 0; col < objectsGrid.COLUMNS; col++) {
                if (objectsGrid.getGameObjectAt(col, row) == GameObject.CRATE && diamondsGrid.getGameObjectAt(col, row) == GameObject.DIAMOND) {
                    cratedDiamondsCount++;
                }
            }
        }
        return cratedDiamondsCount >= numberOfDiamonds;
    }

    /**
     * Get the name of a game level
     *
     * @return          Game level's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get index of a game level
     *
     * @return          Game level's index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Help to transmit blue keeper's location value to wherever need it
     *
     * @return          The location of blue keeper
     */
    public Point getKeeperPosition() {
        return keeperPosition;
    }

    /**
     * Help to transmit diamond's location value to wherever need it
     *
     * @return          The location of diamond
     */
    public ArrayList<Point> getDiamondPosition() {
        return diamondPosition;
    }

    /**
     * Help to transmit ice's location value to wherever need it
     *
     * @return          The location of ice
     */
    public Point getIcePosition() {
        return icePosition;
    }

    /**
     * Help to transmit doors' location value to wherever need it
     *
     * @return          The locations of door
     */
    public ArrayList<Point> getDoorPosition() {
        return doorPosition;
    }

    /**
     * Help to transmit walls's location value to wherever need it
     *
     * @return          The locations of wall's
     */
    ArrayList<Point> getWallPosition() {
        return wallPosition;
    }

    /**
     * Get a target object
     *
     * @param source    Location in a source
     * @param delta     The distance of object moving
     * @return          one of the game object
     */
    public GameObject getTargetObject(Point source, Point delta) {
        return objectsGrid.getTargetFromSource(source, delta);
    }

    /**
     * Transfer game grid to {@code String} type
     *
     * @return      String format of whole map
     */
    @Override
    public String toString() {
        return objectsGrid.toString();
    }

    /**
     * Get game objects continuously
     *
     * @return      Game objects
     */
    @Override
    public Iterator<GameObject> iterator() {
        return new LevelIterator(this);
    }

}