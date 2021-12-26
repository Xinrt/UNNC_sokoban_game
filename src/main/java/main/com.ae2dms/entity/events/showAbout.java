package main.com.ae2dms.entity.events;

import javafx.stage.Stage;
import main.com.ae2dms.util.eventHandleInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Show about entity
 */
public class showAbout implements eventHandleInterface {

    public static Stage primaryStage;

    @Override
    public void eventHandle() throws IOException {
        String title = "About this game";
        String message = "Blue control: UP DOWN LEFT RIGHT ---bomb: L\nRed control: W S A D ---bomb: F";

        createNewDialog.newDialog(title, message, null);
    }

}
