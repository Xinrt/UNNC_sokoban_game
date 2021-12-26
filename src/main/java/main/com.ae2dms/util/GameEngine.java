package main.com.ae2dms.util;

import javafx.scene.input.KeyCode;
import main.com.ae2dms.enums.MusicFactory;
import main.com.ae2dms.enums.engineFactory;
import main.com.ae2dms.entity.events.saveGame;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Engine of the game
 */
public class GameEngine {
    public static final String GAME_NAME = "SokobanFX";
    public static GameLogger logger;
    public static int movesCount = 0;
    public String mapSetName;
    private static boolean debug = false;
    public static Level currentLevel;
    private List<Level> levels;
    private boolean gameComplete = false;
    public static ArrayList<String> gridList = new ArrayList<>();
    public static Point keeperPosition;
    public static Point keeperRedPosition;
    public static Point icePosition;
    /**
     * Temporarily store the position of keeper
     */
    public static Point temPosition = new Point(0,0);
    public static ArrayList<Point> doorPosition = new ArrayList<>();
    public static ArrayList<Point> wallPosition = new ArrayList<>();

    /**
     * initialize {@code logger}, {@code levels} and {@code currentLevel}
     *
     * @param input             The read file
     * @param production        ture/false
     */
    public GameEngine(InputStream input, boolean production) {
        try {
            logger = new GameLogger();
            levels = loadGameFile(input);
            saveGame.levelsCopy.addAll(levels);
            currentLevel = getNextLevel();
            gridList.add(currentLevel.objectsGrid.toString());
        } catch (IOException x) {
            System.out.println("Cannot create logger.");
        } catch (NoSuchElementException e) {
            logger.warning("Cannot load the default save file: " + e.getStackTrace());
        }
    }

    /**
     * Check whether open the debug function
     *
     * @return          Boolean value of {@code debug}
     */
    public static boolean isDebugActive() {
        return debug;
    }

    /**
     * * catch the operation from keyboard <br />
     * and transform them into corresponding distance transfer value in the game
     *
     * @param code              Input from keyboard
     * @throws Exception        If cannot read the keyboard value, throw exception
     */
    public void handleKey(KeyCode code) throws Exception {
        switch (code) {
            case UP:
                move(new Point(-1, 0));
                break;

            case RIGHT:
                move(new Point(0, 1));
                break;

            case DOWN:
                move(new Point(1, 0));
                break;

            case LEFT:
                move(new Point(0, -1));
                break;

            case W:
                moveRed(new Point(-1, 0));
                break;

            case D:
                moveRed(new Point(0, 1));
                break;

            case S:
                moveRed(new Point(1, 0));
                break;

            case A:
                moveRed(new Point(0, -1));
                break;

            case L:
                removeWall();
                break;

            case F:
                removeWallRed();
                break;

            default:
                // TODO: implement something funny.
        }

        if (isDebugActive()) {
            System.out.println(code);
        }
    }

    /**
     * Move engine of blue keeper
     *
     * @param delta     The distance of blue keeper moving
     * @throws IOException      If cannot read from keyboard, throw exception
     */
    private void move(Point delta) throws IOException {
        findPosition();
        doorPosition = currentLevel.getDoorPosition();
        icePosition = currentLevel.getIcePosition();
        engineFactory.getMovement(delta);
    }


    /**
     * Move engine of red keeper
     *
     * @param delta     The distance of red keeper moving
     * @throws IOException      If cannot read from keyboard, throw exception
     */
    private void moveRed(Point delta) throws IOException {
        findPosition();
        doorPosition = currentLevel.getDoorPosition();
        icePosition = currentLevel.getIcePosition();
        engineFactory.getRedMovement(delta);

    }

    /**
     * Remove up, down, right, left objects surround the red keeper <br />
     *
     * If user press "F" of keyboard, the up, down, right, left objects of red keeper will be blown off
     * with an explosion sound
     *
     */
    private void removeWallRed() {
        wallPosition = currentLevel.getWallPosition();
        findPosition();
        MusicFactory.PlayMusic("play bomb music");
        engineFactory.remove(keeperRedPosition);
    }

    /**
     * Remove up, down, right, left objects surround the blue keeper <br />
     *
     * If user press "L" of keyboard, the up, down, right, left objects of blue keeper will be blown off
     * with an explosion sound
     *
     */
    private void removeWall() {
        wallPosition = currentLevel.getWallPosition();
        findPosition();
        MusicFactory.PlayMusic("play bomb music");
        engineFactory.remove(keeperPosition);
    }

    /**
     * Find the current position of both blue keeper and red keeper <br />
     * and store them
     */
    public void findPosition() {
        int col = getCurrentLevel().objectsGrid.COLUMNS;
        int row = getCurrentLevel().objectsGrid.ROWS;


        String objectString = gridList.get(gridList.size()-1).replaceAll("[\\n\\t\\r]", "");

        for(int i=0; i<col; i++)
        {
            for(int j=0; j<row; j++)
            {
                GameObject object = GameObject.fromChar(objectString.charAt(i*row+j));
                if(object == GameObject.KEEPER)
                {
                    keeperPosition = new Point(i,j);
                }
                if(object == GameObject.KEEPERRED)
                {
                    keeperRedPosition = new Point(i,j);
                }
            }
        }
    }
    
    /**
     * Load game file from source, if cannot load file, catch exception
     *
     * @param input         The read file
     * @return              An array with all game levels
     */
    private List<Level> loadGameFile(InputStream input) {
        List<Level> levels = new ArrayList<>(5);
        int levelIndex = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            boolean parsedFirstLevel = false;
            List<String> rawLevel = new ArrayList<>();
            String levelName = "";

            while (true) {
                String line = reader.readLine();

                if (line == null) {
                    if (rawLevel.size() != 0) {
                        Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
                        levels.add(parsedLevel);
                    }
                    break;
                }

                if (line.contains("MapSetName")) {
                    mapSetName = line.replace("MapSetName: ", "");
                    continue;
                }

                if (line.contains("LevelName")) {
                    if (parsedFirstLevel) {
                        Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
                        levels.add(parsedLevel);
                        rawLevel.clear();
                    } else {
                        parsedFirstLevel = true;
                    }

                    levelName = line.replace("LevelName: ", "");
                    continue;
                }

                line = line.trim();
                line = line.toUpperCase();
                if (line.matches(".*W.*W.*")) {
                    rawLevel.add(line);
                }
            }

        } catch (IOException e) {
            logger.severe("Error trying to load the game file: " + e);
        } catch (NullPointerException e) {
            logger.severe("Cannot open the requested file: " + e);
        }

        return levels;
    }

    /**
     * Detect whether the game is completed
     *
     * @return          boolean value of {@code gameComplete}
     */
    public boolean isGameComplete() {
        return gameComplete;
    }


    /**
     * Get next game level
     *
     * @return      Next game level
     */
    public Level getNextLevel() {
        if (currentLevel == null) {
            return levels.get(0);
        }

        int currentLevelIndex = currentLevel.getIndex();

        if (currentLevelIndex < levels.size()) {
            return levels.get(currentLevelIndex);
        }
        gameComplete = true;
        return null;
    }


    /**
     * Get current level
     *
     * @return      current game level
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }


    /**
     * Toggle debug
     */
    public void toggleDebug() {
        debug = !debug;
    }
}