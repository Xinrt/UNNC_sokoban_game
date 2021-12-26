package main.com.ae2dms.controller;

import javafx.event.ActionEvent;
import main.com.ae2dms.util.Main;

import java.io.IOException;

/**
 * Controller of information screen
 */
public class InfoScreenController {
    public void backToStart(ActionEvent actionEvent) throws IOException {
        Main.setRoot("/fxml/StartScreen");
    }
}
