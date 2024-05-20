//Meitar Teper 314708397

package GameInfo;

import biuoop.DrawSurface;

/**
 * The game over screen.
 */
public class GameOverScreen implements Animation {
    private static final int TEXT_SIZE = 32;
    private static final int TEXT_START = 10;
    private Counter score;

    /**
     * GameOverScreen constructor.
     * @param score - the score
     */
    public GameOverScreen(Counter score) {
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(TEXT_START, d.getHeight() / 2, "Game Over. Your score is " + score.getValue(), TEXT_SIZE);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
