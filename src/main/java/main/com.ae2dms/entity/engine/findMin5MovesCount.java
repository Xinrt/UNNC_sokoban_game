package main.com.ae2dms.entity.engine;

import main.com.ae2dms.util.GameEngine;
import main.com.ae2dms.util.Level;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Aims at finding the minimum 5 moves counts of user <br />
 * and store them into an integer {@code ArrayList}
 *
 * @author      Xinran TANG
 */
public class findMin5MovesCount {

    public static ArrayList<Integer> movesCountInIntList = new ArrayList<>();
    public String[] movesCountInStringList;
    public Level currentLevel = GameEngine.currentLevel;
    StringBuilder buffer = new StringBuilder();
    BufferedReader bufferReader = null;


    /**
     * The method used to implement finding minimum 5 moves counts
     * and store moves counts in {@code movesCountInIntList}
     *
     * @throws IOException     If cannot read {@code .txt} files, through IOException
     */
    public void findTop5() throws IOException {
        int currentLevelIndex = currentLevel.getIndex();
        movesCountInIntList.clear();
        if(currentLevelIndex == 1) {
            bufferReader = new BufferedReader(new FileReader("src/main/resources/movesCount/level1.txt"));
        } else if(currentLevelIndex == 2) {
            bufferReader = new BufferedReader(new FileReader("src/main/resources/movesCount/level2.txt"));
        } else if(currentLevelIndex == 3) {
            bufferReader = new BufferedReader(new FileReader("src/main/resources/movesCount/level3.txt"));
        } else if(currentLevelIndex == 4) {
            bufferReader = new BufferedReader(new FileReader("src/main/resources/movesCount/level4.txt"));
        } else if(currentLevelIndex == 5) {
            bufferReader = new BufferedReader(new FileReader("src/main/resources/movesCount/level5.txt"));
        }

        String bufferString;
        while((bufferString = bufferReader.readLine())!=null){
            buffer.append(bufferString.trim());
        }


        String movesCountInString = buffer.toString();

        movesCountInStringList = movesCountInString.split(" ");

        for (String s : movesCountInStringList) {
            movesCountInIntList.add(Integer.parseInt(s));
        }

        Collections.sort(movesCountInIntList);

        buffer.setLength(0);
    }
}
