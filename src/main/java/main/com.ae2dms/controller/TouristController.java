package main.com.ae2dms.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.com.ae2dms.entity.events.toggleDebug;
import main.com.ae2dms.util.*;
import main.com.ae2dms.entity.engine.moveEngine;
import main.com.ae2dms.entity.engine.moveRedEngine;
import main.com.ae2dms.enums.eventFactory;

import java.awt.*;
import java.io.*;


public class TouristController{

    public GridPane smallPan;
    public AnchorPane bigPan;
    public Label moveCount;
    public Label moveCountName;
    /**
     * Used to transfer primary stage created in Main
     */
    public static Stage primaryStage;
    /**
     * Used to transfer game Engine created here to other events
     */
    public static GameEngine gameEngine;
    /**
     * Used to check whether user have saved the game or not
     */
    public static File saveFile;
    /**
     * Used to count the times user click on the toggle music fucntion
     */
    public static int toggleMusicTimes = 0;

    /**
     * Execute before anything else
     */
    public void initialize() {
        loadDefaultSaveFile(primaryStage);
    }

    /**
     * Initialize game <br />
     * Create the game Engine and transfer it to somewhere need it
     *
     * @param input         File stream
     */
    public void initializeGame(InputStream input) {
        gameEngine = new GameEngine(input, true);
        moveEngine.gameEngine = gameEngine;
        moveRedEngine.gameEngine = gameEngine;
        toggleDebug.gameEngine = gameEngine;
        reloadGrid();
    }

    /**
     * Load the default game map file <br />
     * and transfer the loaded input stream to {@code initializeGame}
     *
     * @param primaryStage      PrimaryStage of the game
     */
    void loadDefaultSaveFile(Stage primaryStage) {
        this.primaryStage = primaryStage;
        InputStream in = getClass().getClassLoader().getResourceAsStream("level/SampleGame.skb");
        initializeGame(in);
        setEventFilter();
    }

    /**
     * Catch the actions on keyboard
     */
    private void setEventFilter() {
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            try {
                gameEngine.handleKey(event.getCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
            reloadGrid();
        });
    }

    /**
     * Load a new game
     */
    public void loadGame() {
        try {
            loadGameFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load game file <br />
     * If user click load game but do nothing, just ignore it <br />
     * else, transfer new input stream
     *
     * @throws FileNotFoundException        If cannot find the need file, throw {@code FileNotFoundException}
     */
    public void loadGameFile() throws FileNotFoundException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Save File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Sokoban save file", "*.skb"));
        saveFile = fileChooser.showOpenDialog(primaryStage);

        if (saveFile != null) {
            if (GameEngine.isDebugActive()) {
                GameEngine.logger.info("Loading save file: " + saveFile.getName());
            }
            GameEngine.currentLevel = null;
            initializeGame(new FileInputStream(saveFile));
        }
    }

    /**
     * Reload game grid and show real-time moves counts
     */
    public void reloadGrid() {
        if (gameEngine.isGameComplete()) {
            try {
                showVictoryMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        Level currentLevel = gameEngine.getCurrentLevel();
        LevelIterator levelGridIterator = (LevelIterator) currentLevel.iterator();

        smallPan.getChildren().clear();

        while (levelGridIterator.hasNext()) {
            addObjectToGrid(levelGridIterator.next(), levelGridIterator.getcurrentposition());
        }
        smallPan.autosize();
        primaryStage.sizeToScene();
        moveCount.setText(String.valueOf(GameEngine.movesCount));
    }

    /**
     * Add a game object to grid
     *
     * @param gameObject        A game object
     * @param location          The location of the given game object
     */
    public void addObjectToGrid(GameObject gameObject, Point location) {
        GraphicObject graphicObject = new GraphicObject(gameObject);
        smallPan.add(graphicObject, location.y, location.x);
    }


    /**
     * Show victory message if user finish the game
     *
     * @throws IOException      If cannot get event, throw exception
     */
    //   main.com.ae2dms.entity.events
    private void showVictoryMessage() throws IOException {
        eventFactory.getEvent("show victory message");
    }

    /**
     * Save game, user can choose the save location
     *
     * @param actionEvent       The event user click on the game interface
     * @throws IOException      If cannot get event, throw exception
     */
    public void saveGame(ActionEvent actionEvent) throws IOException {
        eventFactory.getEvent("save game");
    }

    /**
     * Close and exit game
     *
     * @param actionEvent       The event user click on the game interface
     * @throws IOException      If cannot get event, throw exception
     */
    public void closeGame(ActionEvent actionEvent) throws IOException {
        eventFactory.getEvent("close game");
    }

    /**
     * Undo movement, user can back to previous state <br />
     * If user undo at the beginning of a game level, show warning message
     *
     * @param actionEvent       The event user click on the game interface
     * @throws IOException      If cannot get event, throw exception
     */
    public void undo(ActionEvent actionEvent) throws IOException {
        eventFactory.getEvent("undo");
        reloadGrid();
    }

    /**
     * Stop or restart the music
     *
     * @param actionEvent       The event user click on the game interface
     * @throws IOException      If cannot get event, throw exception
     */
    public void toggleMusic(ActionEvent actionEvent) throws IOException {
        toggleMusicTimes++;
        eventFactory.getEvent("toggle music");
    }

    /**
     * Print out game information
     *
     * @param actionEvent       The event user click on the game interface
     * @throws IOException      If cannot get event, throw exception
     */
    public void toggleDebug(ActionEvent actionEvent) throws IOException {
        eventFactory.getEvent("toggle debug");
        reloadGrid();
    }

    /**
     * Restart current game level <br />
     * If user reset level at the beginning of a game level, show warning message
     *
     * @param actionEvent       The event user click on the game interface
     * @throws IOException      If cannot get event, throw exception
     */
    public void resetLevel(ActionEvent actionEvent) throws IOException {
        eventFactory.getEvent("reset level");
        reloadGrid();
    }

    /**
     * Show simple controlling skills about the game
     *
     * @param actionEvent       The event user click on the game interface
     * @throws IOException      If cannot get event, throw exception
     */
    public void showAbout(ActionEvent actionEvent) throws IOException {
        eventFactory.getEvent("show about");
    }
}
