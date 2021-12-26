package main.com.ae2dms.entity.events;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.com.ae2dms.util.GameObject;
import main.com.ae2dms.util.Level;
import main.com.ae2dms.controller.TouristController;
import main.com.ae2dms.util.eventHandleInterface;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Save game entity
 */
public class saveGame implements eventHandleInterface {

    public static List<Level> levelsCopy = new ArrayList<>(5);
    public ArrayList<Point> diamondPosition = new ArrayList<>();
    public ArrayList<ArrayList<Point>> diamondPositionStore = new ArrayList<>();

    @Override
    public void eventHandle() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("skb files (*.skb)", "*.skb");
        fileChooser.getExtensionFilters().add(extFilter);
        Stage s = new Stage();
        File file = fileChooser.showSaveDialog(s);
        if (file == null) {
            return;
        }
        if(file.exists()){
            file.delete();
            diamondPositionStore.clear();
        }

        String exportFilePath = file.getAbsolutePath();

        try {
            FileWriter fileWriter = new FileWriter(exportFilePath);

            for(int i=TouristController.gameEngine.getCurrentLevel().getIndex()-1; i<levelsCopy.size(); i++) {
                if(i == TouristController.gameEngine.getCurrentLevel().getIndex()-1) {
                    fileWriter.write("LevelName: "+TouristController.gameEngine.getCurrentLevel().getName()+"\n");
                    fileWriter.write(String.valueOf(levelsCopy.get(TouristController.gameEngine.getCurrentLevel().getIndex()-1)));
                    fileWriter.write("\n");
                    continue;
                }

                fileWriter.write("LevelName: "+levelsCopy.get(i).getName() +"\n");
                fileWriter.write(String.valueOf(levelsCopy.get(levelsCopy.get(i).getIndex()-1)));
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<Level> newLevels = new ArrayList<>(5);
        int levelIndex = TouristController.gameEngine.getCurrentLevel().getIndex();

        BufferedReader reader = new BufferedReader(new FileReader(exportFilePath));
        boolean parsedFirstLevel = false;
        List<String> rawLevel = new ArrayList<>();
        String levelName = "";


        while (true) {
            String line = reader.readLine();

            if (line == null) {
                if (rawLevel.size() != 0) {
                    Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
                    newLevels.add(parsedLevel);
                }
                break;
            }

            if (line.contains("LevelName")) {
                if (parsedFirstLevel) {
                    Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
                    newLevels.add(parsedLevel);
                    rawLevel.clear();
                } else {
                    parsedFirstLevel = true;
                }

                levelName = line.replace("LevelName: ", "");
                continue;
            }

            line = line.trim();
            line = line.toUpperCase();
            if (line.matches(".*W.*W.*")) {
                rawLevel.add(line);
            }
        }

        for(int i=0; i<5; i++) {
            diamondPositionStore.add(levelsCopy.get(i).getDiamondPosition());
        }

        FileWriter fileWriter = new FileWriter(exportFilePath);
        int storeDiamondIndex = TouristController.gameEngine.getCurrentLevel().getIndex()-1;

        for (Level newLevel : newLevels) {
            diamondPosition = diamondPositionStore.get(storeDiamondIndex);
            for (Point point : diamondPosition) {
                newLevel.objectsGrid.putGameObjectAt(GameObject.DIAMOND, point);
            }
            storeDiamondIndex++;

            fileWriter.write("LevelName: " + newLevel.getName() + "\n");
            fileWriter.write(String.valueOf(newLevel));
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
}
