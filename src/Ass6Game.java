//Meitar Teper 314708397

import GameInfo.AnimationRunner;
import GameInfo.GameFlow;
import Levels.LevelInformation;
import Levels.LevelOneInfo;
import Levels.LevelThreeInfo;
import Levels.LevelTwoInfo;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Ass5Game class.
 */
public class Ass6Game {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * The main method of the game.
     *
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Game", WIDTH, HEIGHT);
        AnimationRunner runner = new AnimationRunner(gui);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new LevelOneInfo());
        levels.add(new LevelTwoInfo());
        levels.add(new LevelThreeInfo());
        GameFlow gameFlow = new GameFlow(gui, runner, keyboard);

        if (args.length > 0) {
            // Run levels based on the command-line arguments
            List<LevelInformation> selectedLevels = new ArrayList<>();
            for (String arg : args) {
                try {
                    int levelNumber = Integer.parseInt(arg);
                    if (levelNumber >= 1 && levelNumber <= levels.size()) {
                        LevelInformation level = levels.get(levelNumber - 1);
                        selectedLevels.add(level);
                    }
                } catch (NumberFormatException e) {
                    // Ignore non numeric arguments
                }
            }
            if (!selectedLevels.isEmpty()) {
                gameFlow.runLevels(selectedLevels);
            } else {
                // If no valid levels are selected, run all levels by default
                gameFlow.runLevels(levels);
            }
        } else {
            // Run all levels by default
            gameFlow.runLevels(levels);
        }
    }
}

