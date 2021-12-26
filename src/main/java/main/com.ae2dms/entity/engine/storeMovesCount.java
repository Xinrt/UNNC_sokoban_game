package main.com.ae2dms.entity.engine;

import main.com.ae2dms.util.GameEngine;
import main.com.ae2dms.util.Level;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Put moves counts in corresponding {@code .txt} file
 */
public class storeMovesCount {

    /**
     * copy the current level in {@code GameEngine}
     */
    public Level currentLevel = GameEngine.currentLevel;
    /**
     * transfer moves counts
     */
    public int movesCount = GameEngine.movesCount;

    /**
     * Open corresponding {@code .txt} file and append to it
     *
     * @throws IOException       If cannot write into {@code .txt} files, through IOException
     */
    public void storeSteps() throws IOException {
        // store move steps into .txt file
        int currentLevelIndex = currentLevel.getIndex();
        if(currentLevelIndex == 1) {
            Writer level1 = new FileWriter("src/main/resources/movesCount/level1.txt",true);
            level1.append(String.valueOf(movesCount));
            level1.append(" ");
            level1.close();
        } else if(currentLevelIndex == 2) {
            Writer level2 = new FileWriter("src/main/resources/movesCount/level2.txt",true);
            level2.append(String.valueOf(movesCount));
            level2.append(" ");
            level2.close();
        } else if(currentLevelIndex == 3) {
            Writer level3 = new FileWriter("src/main/resources/movesCount/level3.txt",true);
            level3.append(String.valueOf(movesCount));
            level3.append(" ");
            level3.close();
        } else if(currentLevelIndex == 4) {
            Writer level4 = new FileWriter("src/main/resources/movesCount/level4.txt",true);
            level4.append(String.valueOf(movesCount));
            level4.append(" ");
            level4.close();
        } else if(currentLevelIndex == 5) {
            Writer level5 = new FileWriter("src/main/resources/movesCount/level5.txt",true);
            level5.append(String.valueOf(movesCount));
            level5.append(" ");
            level5.close();
        }
    }
}
