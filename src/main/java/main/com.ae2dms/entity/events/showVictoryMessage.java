package main.com.ae2dms.entity.events;

import javafx.scene.effect.MotionBlur;
import main.com.ae2dms.util.eventHandleInterface;

import static main.com.ae2dms.entity.events.createNewDialog.newDialog;

/**
 * Show victory message entity
 */
public class showVictoryMessage implements eventHandleInterface {
    @Override
    public void eventHandle() {
        String dialogTitle = "Congratulations!";
        String dialogMessage = "You have passed all the game levels ";
        MotionBlur mb = new MotionBlur(0, 0);

        newDialog(dialogTitle, dialogMessage, mb);
    }
}
