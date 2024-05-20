//Meitar Teper 314708397

package GameInfo;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen and a countdown from countFrom back to 1.
 */
public class CountdownAnimation implements Animation {
    private static final int TEXT_SIZE = 80;
    private static final double MILI_SEC = 1000;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int currentCount;
    private long startTime;

    /**
     * Constructs a CountdownAnimation object.
     * @param numOfSeconds the duration of the countdown in seconds
     * @param countFrom the starting count
     * @param gameScreen the SpriteCollection representing the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.currentCount = countFrom;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);

        long currentTime = System.currentTimeMillis();
        double usedTime = (currentTime - startTime) / MILI_SEC;

        //it's time to change number on the screen
        if (usedTime >= numOfSeconds / countFrom) {
            currentCount--;
            startTime = currentTime;
        }

        if (currentCount > 0) {
            d.setColor(Color.lightGray);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(currentCount), TEXT_SIZE);
        }
    }
    @Override
    public boolean shouldStop() {
        return currentCount <= 0;
    }
}
