//Meitar Teper 314708397

package GameInfo;

import biuoop.DrawSurface;

/**
 * the pause screen.
 */
public class PauseScreen implements Animation {
    private static final int TEXT_SIZE = 32;
    private static final int TEXT_START = 10;


    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(TEXT_START, d.getHeight() / 2, "paused -- press space to continue", TEXT_SIZE);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
