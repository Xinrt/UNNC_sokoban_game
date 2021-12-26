package main.com.ae2dms.util;

import main.com.ae2dms.entity.engine.moveEngine;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.InputStream;

import static javafx.scene.input.KeyCode.LEFT;
import static org.junit.Assert.*;

public class GameEngineTest {

    GameEngine gameEngineTest;
    Point tempPosition;
    Point keeperPositionTest;
    Level currentLevel;
    InputStream input;


    @Before
    public void setUp() {
        input = getClass().getClassLoader().getResourceAsStream("level/SampleGame.skb");
        gameEngineTest = new GameEngine(input, true);
        moveEngine.gameEngine = gameEngineTest;
        currentLevel = gameEngineTest.getCurrentLevel();
    }

    @Test
    public void isDebugActiveTest() {
        assertEquals(false, GameEngine.isDebugActive());
    }

    @Test
    public void handleKeyTest() throws Exception {
        keeperPositionTest = currentLevel.getKeeperPosition();
        tempPosition = keeperPositionTest;
        gameEngineTest.handleKey(LEFT);
        gameEngineTest.findPosition();
        keeperPositionTest=GameEngine.keeperPosition;
        assertEquals( keeperPositionTest.y, tempPosition.y - 1);
    }

    @Test
    public void findPositionTests() {
        gameEngineTest.findPosition();
        keeperPositionTest=GameEngine.keeperPosition;
        assertEquals(new Point(18, 10), keeperPositionTest);
    }

    @Test
    public void isGameCompleteTest() {
        assertFalse(gameEngineTest.isGameComplete());
    }

    @Test
    public void getNextLevelTest() {
        gameEngineTest.getNextLevel();
        assertEquals(1, GameEngine.currentLevel.getIndex());
    }

    @Test
    public void getCurrentLevelTest() {
        gameEngineTest.getCurrentLevel();
        assertEquals(1, GameEngine.currentLevel.getIndex());
    }
}