package main.com.ae2dms.util;


import javafx.geometry.VerticalDirection;
import javafx.scene.input.KeyCode;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class MainTest extends ApplicationTest {

    Main main = new Main();

    @Override
    public void start(Stage primaryStage) throws Exception {
        main.start(primaryStage);
        assertTrue(primaryStage.isShowing());
        assertTrue(primaryStage.isResizable());
    }

    @Test
    public void viewInfo() {
        clickOn("#Information");
        sleep(1, TimeUnit.SECONDS);
        scroll(VerticalDirection.DOWN);
        sleep(1, TimeUnit.SECONDS);
        clickOn("#back");
        sleep(1, TimeUnit.SECONDS);
    }

    @Test
    public void startGame() {
        clickOn("#startGame");
        sleep(1, TimeUnit.SECONDS);
        push(KeyCode.LEFT);
        sleep(1, TimeUnit.SECONDS);
        push(KeyCode.D);
        sleep(1, TimeUnit.SECONDS);
        push(KeyCode.UP);
        sleep(1, TimeUnit.SECONDS);
        push(KeyCode.UP);
        sleep(1, TimeUnit.SECONDS);
        push(KeyCode.UP);
        push(KeyCode.L);
        sleep(1, TimeUnit.SECONDS);
        push(KeyCode.F);
        sleep(1, TimeUnit.SECONDS);
        push(KeyCode.UP);
        sleep(1, TimeUnit.SECONDS);
        push(KeyCode.UP);
        sleep(1, TimeUnit.SECONDS);
        push(KeyCode.SHORTCUT, KeyCode.Z);
        sleep(1, TimeUnit.SECONDS);
        clickOn("#Level");
        sleep(1, TimeUnit.SECONDS);
        clickOn("#resetLevelId");
        sleep(1, TimeUnit.SECONDS);
    }
}