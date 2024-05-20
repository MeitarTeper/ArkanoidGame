//Meitar Teper 314708397

package GameInfo;

import Levels.LevelInformation;
import Sprites.ScoreIndicator;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * Game Flow class for running the full game.
 */
public class GameFlow {
    private GUI gui;
    private AnimationRunner runner;
    private biuoop.KeyboardSensor keyboard;
    private Counter score;
    private ScoreIndicator scoreIndicator;
    private ScoreTrackingListener scoreTrackingListener;


    /**
     * Game flow constructor.
     * @param gui - the gui
     * @param runner - the runner
     * @param keyboard - the keyboard
     */
    public GameFlow(GUI gui, AnimationRunner runner, KeyboardSensor keyboard) {
        this.gui = gui;
        this.runner = runner;
        this.keyboard = keyboard;
        this.score = new Counter();
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.scoreIndicator = new ScoreIndicator(score);
    }

    /**
     * running the levels as a flow.
     * @param levels - the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        List<LevelInformation> copyLevels = levels;
        for (LevelInformation levelInfo : copyLevels) {

            GameLevel level = new GameLevel(levelInfo, gui, score, scoreIndicator, scoreTrackingListener,
                    keyboard, runner);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (level.noMoreBalls()) {
                Animation gameOver = new GameOverScreen(score);
                this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, gameOver));
                gui.close();
                break;
            }

        }
        Animation winScreen = new WinningScreen(score);
        this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, winScreen));
        gui.close();
    }
}
