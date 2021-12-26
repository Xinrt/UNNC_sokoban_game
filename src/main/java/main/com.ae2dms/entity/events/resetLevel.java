package main.com.ae2dms.entity.events;

import javafx.scene.effect.MotionBlur;
import main.com.ae2dms.util.GameEngine;
import main.com.ae2dms.util.GameGrid;
import main.com.ae2dms.controller.TouristController;
import main.com.ae2dms.util.eventHandleInterface;

import static main.com.ae2dms.entity.events.createNewDialog.newDialog;

/**
 * Reset level entity
 */
public class resetLevel implements eventHandleInterface {
    @Override
    public void eventHandle() {
        GameEngine.movesCount=0;

        if(GameEngine.gridList.size()<2) {
            String dialogTitle = "Warning";
            String dialogMessage = "This is the start point \n" + "Please move!";
            MotionBlur mb = new MotionBlur(0, 0);

            newDialog(dialogTitle, dialogMessage, mb);
        } else {
            TouristController.gameEngine.getCurrentLevel().objectsGrid = GameGrid.toGrid(GameEngine.gridList.get(1));
        }
    }
}
