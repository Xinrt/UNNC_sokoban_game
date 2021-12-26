package main.com.ae2dms.entity.engine;

import main.com.ae2dms.util.Engine;
import main.com.ae2dms.util.GameEngine;
import main.com.ae2dms.util.GameObject;

import java.awt.*;

/**
 * Remove up, down, right, left objects surround the keeper
 */
public class removeObject extends Engine {

    /**
     * The up, down, right, left objects of keeper will be blown off
     *
     * @param keeperPosition        The location of keeper
     */
    public void remove(Point keeperPosition) {
        GameEngine.currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(keeperPosition.x+1,keeperPosition.y));
        GameEngine.currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(keeperPosition.x-1,keeperPosition.y));
        GameEngine.currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(keeperPosition.x,keeperPosition.y+1));
        GameEngine.currentLevel.objectsGrid.putGameObjectAt(GameObject.FLOOR, new Point(keeperPosition.x,keeperPosition.y-1));
    }
}
