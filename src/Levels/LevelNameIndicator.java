//Meitar Teper 314708397

package Levels;

import GameInfo.GameLevel;
import Sprites.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The level name indicator class.
 */
public class LevelNameIndicator implements Sprite {
    private static final int FONT_SIZE = 14;
    private static final int TEXT_START_X = 500;
    private static final int TEXT_START_Y = 15;
    private String levelName;

    /**
     * LevelNameIndicator constructor.
     * @param levelName - the level name.
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawText(TEXT_START_X, TEXT_START_Y, "Level Name: " + levelName, FONT_SIZE);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
