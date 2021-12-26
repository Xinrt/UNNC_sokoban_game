package main.com.ae2dms.util;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.com.ae2dms.controller.TouristController;
import main.com.ae2dms.entity.events.showAbout;

import java.io.IOException;


/**
 * Start of game
 */
public class Main extends Application {

    private static Scene scene;

    /**
     * Create the primary stage and start game
     *
     * @param primaryStage      Primary stage of the game
     * @throws Exception        If cannot create scene, throw exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(GameEngine.GAME_NAME);
        scene = new Scene(loadFXML("/fxml/StartScreen"), 600, 630);
        primaryStage.setScene(scene);
        primaryStage.show();
        TouristController.primaryStage = primaryStage;
        showAbout.primaryStage = primaryStage;
    }

    /**
     * Set the root of game scene
     *
     * @param fxml          The name of a {@code .fxml} file
     * @throws IOException      If cannot load the {@code .fxml} file, throw IOException
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Load {@code .fxml} file
     *
     * @param fxml          The name of a {@code .fxml} file
     * @return              Load game
     * @throws IOException      If cannot create {@code FXMLLoader}, throw IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml+".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
