package main.com.ae2dms.util;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;

public class LevelTest {

    private Level level;
    List<String> entireLevel;

    @Before
    public void setUp() {
        entireLevel = new ArrayList<>();
        entireLevel.add("First level");
        level = new Level("Second level", 2, entireLevel);
    }

    @Test
    public void isCompleteTest() {
        boolean result = level.isComplete();
        assertTrue(result);
    }

    @Test
    public void getNameTest() {
        String name = level.getName();
        assertEquals("Second level", name);
    }

    @Test
    public void getIndexTest() {
        int index = level.getIndex();
        assertEquals(2, index);
    }

    @Test
    public void getKeeperPositionTest() {
        Point keeperPosition = level.getKeeperPosition();
        System.out.println(keeperPosition);
        assertEquals(new Point(0,3), keeperPosition);
    }

    @Test
    public void getTargetObjectTest() {
        Point source = new Point(0,2);
        Point delta = new Point(0,4);
        GameObject targetObject = level.getTargetObject(source, delta);
        assertEquals(GameObject.WALL, targetObject);
    }

    @Test
    public void toStringTest() {
        String gameGrid = level.toString();
        assertEquals("WIWSW WWWWW\n", gameGrid);
    }
}