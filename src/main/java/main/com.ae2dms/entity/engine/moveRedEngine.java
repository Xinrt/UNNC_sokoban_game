package main.com.ae2dms.entity.engine;

import javafx.scene.effect.MotionBlur;
import main.com.ae2dms.util.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static main.com.ae2dms.entity.events.createNewDialog.newDialog;

/**
 *  Move engine for red keeper
 *  Only used to detect the movement of red keeper
 */
public class moveRedEngine extends Engine {

    public GameLogger logger = GameEngine.logger;
    public ArrayList<String> gridList = GameEngine.gridList;
    public Point keeperRedPosition = GameEngine.keeperRedPosition;
    public Point icePosition = GameEngine.icePosition;
    public Point temPosition = GameEngine.temPosition;
    public ArrayList<Point> doorPosition = GameEngine.doorPosition;
    public Level currentLevel = GameEngine.currentLevel;
    public storeMovesCount movesCountStore = new storeMovesCount();
    public findMin5MovesCount findMin5counts = new findMin5MovesCount();

    public static GameEngine gameEngine;

    /**
     * Move red keeper by using {@code Point delta}
     *
     * @param delta     The distance of red keeper moving
     * @throws IOException      If cannot read from keyboard, throw exception
     */
    public void moveRedKeeper(Point delta) throws IOException {

        if (gameEngine.isGameComplete()) {
            return;
        }

        GameObject keeperRed = currentLevel.objectsGrid.getGameObjectAt(keeperRedPosition);
        Point targetObjectPointRed = GameGrid.translatePoint(keeperRedPosition, delta);
        GameObject keeperRedTarget = currentLevel.objectsGrid.getGameObjectAt(targetObjectPointRed);


        if (GameEngine.isDebugActive()) {
            System.out.println("Current level state:");
            System.out.println(currentLevel.toString());
            System.out.println("Keeper Red pos: " + keeperRedPosition);
            System.out.println("Movement source obj: " + keeperRed);
            System.out.printf("Target object: %s at [%s]", keeperRedTarget, targetObjectPointRed);
        }

        boolean keeperMoved = false;

        switch (keeperRedTarget) {

            case WALL:

            case KEEPER:
                break;

            case ICE:
                if(((keeperRedPosition.x == (icePosition.x+1)) && (keeperRedPosition.y == icePosition.y))) {
                    temPosition.x = icePosition.x-1;
                    temPosition.y = icePosition.y;
                } else if((keeperRedPosition.x == (icePosition.x-1)) && (keeperRedPosition.y == icePosition.y)) {
                    temPosition.x = icePosition.x+1;
                    temPosition.y = icePosition.y;
                } else if((keeperRedPosition.x == icePosition.x) && (keeperRedPosition.y == (icePosition.y+1))) {
                    temPosition.x = icePosition.x;
                    temPosition.y = icePosition.y-1;
                } else if((keeperRedPosition.x == icePosition.x) && (keeperRedPosition.y == (icePosition.y-1))) {
                    temPosition.x = icePosition.x;
                    temPosition.y = icePosition.y+1;
                }

                currentLevel.objectsGrid.putGameObjectAt(currentLevel.objectsGrid.getGameObjectAt(temPosition), keeperRedPosition);
                currentLevel.objectsGrid.putGameObjectAt(keeperRed, temPosition);

                keeperMoved = true;
                break;

            case DOOR:
                if(((keeperRedPosition.x == (doorPosition.get(0).x)+1) && (keeperRedPosition.y == doorPosition.get(0).y))) {
                    temPosition.x = (doorPosition.get(1).x)+1;
                    temPosition.y = doorPosition.get(1).y;

                    currentLevel.objectsGrid.putGameObjectAt(currentLevel.objectsGrid.getGameObjectAt(temPosition), keeperRedPosition);
                    currentLevel.objectsGrid.putGameObjectAt(keeperRed, temPosition);

                } else if (((keeperRedPosition.x == (doorPosition.get(1).x)+1) && (keeperRedPosition.y == doorPosition.get(1).y))) {
                    temPosition.x = (doorPosition.get(0).x)+1;
                    temPosition.y = doorPosition.get(0).y;

                    currentLevel.objectsGrid.putGameObjectAt(currentLevel.objectsGrid.getGameObjectAt(temPosition), keeperRedPosition);
                    currentLevel.objectsGrid.putGameObjectAt(keeperRed, temPosition);
                }

                keeperMoved = true;
                break;

            case CRATE:

                GameObject crateTarget = currentLevel.getTargetObject(targetObjectPointRed, delta);
                if (crateTarget != GameObject.FLOOR) {
                    break;
                }

                currentLevel.objectsGrid.putGameObjectAt(currentLevel.objectsGrid.getGameObjectAt(GameGrid.translatePoint(targetObjectPointRed, delta)), targetObjectPointRed);
                currentLevel.objectsGrid.putGameObjectAt(keeperRedTarget, GameGrid.translatePoint(targetObjectPointRed, delta));
                currentLevel.objectsGrid.putGameObjectAt(currentLevel.objectsGrid.getGameObjectAt(GameGrid.translatePoint(keeperRedPosition, delta)), keeperRedPosition);
                currentLevel.objectsGrid.putGameObjectAt(keeperRed, GameGrid.translatePoint(keeperRedPosition, delta));
                keeperMoved = true;
                break;

            case FLOOR:
                currentLevel.objectsGrid.putGameObjectAt(currentLevel.objectsGrid.getGameObjectAt(GameGrid.translatePoint(keeperRedPosition, delta)), keeperRedPosition);
                currentLevel.objectsGrid.putGameObjectAt(keeperRed, GameGrid.translatePoint(keeperRedPosition, delta));
                keeperMoved = true;
                break;

            default:
                logger.severe("The object to be moved was not a recognised GameObject.");
                throw new AssertionError("This should not have happened. Report this problem to the developer.");
        }

        if (keeperMoved) {
            keeperRedPosition.translate((int) delta.getX(), (int) delta.getY());
            GameEngine.movesCount++;
            if (currentLevel.isComplete()) {
                if (GameEngine.isDebugActive()) {
                    System.out.println("Level complete!");
                }

                // store moves count
                movesCountStore.storeSteps();

                // print out top 5 min counts
                findMin5counts.findTop5();
                String dialogTitle;
                String dialogMessage;
                if(findMin5MovesCount.movesCountInIntList.size()>5) {
                    dialogTitle = "Top 5";
                    dialogMessage = "Top 1: " + findMin5MovesCount.movesCountInIntList.get(0) + "\n" +
                            "Top 2: " + findMin5MovesCount.movesCountInIntList.get(1) + "\n" +
                            "Top 3: " + findMin5MovesCount.movesCountInIntList.get(2) + "\n" +
                            "Top 4: " + findMin5MovesCount.movesCountInIntList.get(3) + "\n" +
                            "Top 5: " + findMin5MovesCount.movesCountInIntList.get(4) + "\n";
                } else {
                    Collections.sort(findMin5MovesCount.movesCountInIntList);
                    dialogTitle = "Top 1";
                    dialogMessage = "Top 1: " + findMin5MovesCount.movesCountInIntList.get(0);
                }

                MotionBlur mb = new MotionBlur(0, 0);
                newDialog(dialogTitle, dialogMessage, mb);

                gridList.clear();
                GameEngine.movesCount = 0;
                GameEngine.currentLevel = gameEngine.getNextLevel();
            }
        }
        gridList.add(currentLevel.objectsGrid.toString());
    }
}
