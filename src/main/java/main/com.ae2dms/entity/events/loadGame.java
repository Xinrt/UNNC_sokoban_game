package main.com.ae2dms.entity.events;

import main.com.ae2dms.util.Main;
import main.com.ae2dms.controller.TouristController;
import main.com.ae2dms.util.eventHandleInterface;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Load game entity
 */
public class loadGame implements eventHandleInterface {

    TouristController touristController = new TouristController();

    @Override
    public void eventHandle() throws FileNotFoundException {
        try {
            Main.setRoot("/fxml/TouristMode");
            touristController.loadGameFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
