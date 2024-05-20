//Meitar Teper 314708397

package Sprites;

import GameInfo.GameLevel;
import Levels.LevelInformation;
import biuoop.DrawSurface;

/**
 * background class.
 */
public class Background implements Sprite {
    private LevelInformation levelInfo;

    /**
     * Background constructor.
     * @param levelInfo
     */
    public Background(LevelInformation levelInfo) {
        this.levelInfo = levelInfo;
    }
    @Override
    public void drawOn(DrawSurface d) {
        this.levelInfo.drawOn(d);
    }
    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
