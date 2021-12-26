package main.com.ae2dms.util;

import main.com.ae2dms.util.GameGrid;
import main.com.ae2dms.util.GameObject;
import main.com.ae2dms.util.GridIterator;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GameGridTest {

    private GameGrid gameGrid;

    @Before
    public void setUp() {
        gameGrid = new GameGrid(5, 5);
    }

    @Test
    public void translatePointTest() {
        Point sourceLocation = new Point(0,2);
        Point delta = new Point(0, 4);
        Point translatePoint = GameGrid.translatePoint(sourceLocation, delta);
        assertEquals(new Point(0,6), translatePoint);
    }

    @Test
    public void getTargetFromSourceTest() {
        Point source = new Point(0,1);
        Point delta = new Point(0,2);
        GameObject targetFromSource = gameGrid.getTargetFromSource(source, delta);
        assertEquals(null, targetFromSource);
    }

    @Test
    public void getGameObjectAtTest() {
        int col = 2;
        int row = 2;
        GameObject gameObjectAt = gameGrid.getGameObjectAt(col, row);
        assertEquals(null, gameObjectAt);
    }

    @Test
    public void getGameObjectAtTest2() {
        Point p = new Point(1,1);
        GameObject gameObjectAt = gameGrid.getGameObjectAt(p);
        assertEquals(null, gameObjectAt);
    }

    @Test
    public void removeGameObjectAtTest() {
        Point position = new Point(1,1);
        boolean removeObject = gameGrid.removeGameObjectAt(position);
        assertTrue(removeObject);
    }

    @Test
    public void putGameObjectAtTest() {
        GameObject gameObject = GameObject.CRATE;
        int x = 2;
        int y = 2;
        boolean putObject = gameGrid.putGameObjectAt(gameObject, x, y);
        assertTrue(putObject);
    }

    @Test
    public void putGameObjectAtTest2() {
        GameObject gameObject = GameObject.CRATE;
        Point p = new Point(2,2);
        boolean putObject = gameGrid.putGameObjectAt(gameObject, p);
        assertTrue(putObject);
    }

    @Test
    public void testToStringTest() {
        String sb = gameGrid.toString();
        assertEquals("=====\n=====\n=====\n=====\n=====\n", sb);
    }
}