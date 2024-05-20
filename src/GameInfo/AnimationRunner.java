//Meitar Teper 314708397

package GameInfo;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * A class that runs every part of the game.
 */
public class AnimationRunner {
    private static final int FRAME_P_S = 60;
    private static final int ML_P_FRAME = 1000;
    private GUI gui;
    private int framesPerSecond;

    /**
     * constructor of the AnimationRunner.
     * @param gui - the gui
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = FRAME_P_S;
    }

    /**
     * The loop that runs the game flow and every part of it.
     * @param animation - current animation to run
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = ML_P_FRAME / framesPerSecond;
        while (!animation.shouldStop()) {
            //calculate the time since the last time
            // timing
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);
            gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
