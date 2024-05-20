//Meitar Teper 314708397

package Sprites;

import GameInfo.Counter;
import GameInfo.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A sprite that displays the current score on the screen.
 */
public class ScoreIndicator implements Sprite {
    private static final int X = 350;
    private static final int Y = 15;
    private static final int FONT_SIZE = 14;
    private Counter score;

    /**
     * Constructs a ScoreIndicator.
     * @param score the Counter for the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draws the score indicator on the given DrawSurface.
     * @param surface the DrawSurface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawText(X, Y, "Score: " + this.score.getValue(), FONT_SIZE);
    }

    /**
     * Time passed method.
     */
    public void timePassed() {
    }

    /**
     * Adds the score indicator to the given Game by adding it as a sprite.
     * @param g the Game to add the score indicator to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
