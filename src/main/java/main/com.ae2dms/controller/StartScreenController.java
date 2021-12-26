package main.com.ae2dms.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import main.com.ae2dms.enums.MusicFactory;
import main.com.ae2dms.util.Main;
import java.io.IOException;

/**
 * Controller of start screen
 */
public class StartScreenController {

    public AnchorPane startPan;
    public Button startGame;

    /**
     * Open the game running screen
     *
     * @param event         The event of user click on the start button
     * @throws IOException      If cannot load the {@code TouristMode.fxml} file, throw IOException
     */
    public void startgame(ActionEvent event) throws IOException {
        MusicFactory.PlayMusic("play background music");
        Main.setRoot("/fxml/TouristMode");
    }

    /**
     * @param actionEvent       The event of user click on the Info button
     * @throws IOException      If cannot load the {@code InfoScreen.fxml} file, throw IOException
     */
    public void Info(ActionEvent actionEvent) throws IOException {
        Main.setRoot("/fxml/InfoScreen");
    }
}
