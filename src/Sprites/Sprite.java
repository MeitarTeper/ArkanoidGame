//Meitar Teper 314708397

package Sprites;
import GameInfo.GameLevel;
import biuoop.DrawSurface;

/**
 * Sprites.Sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d - the surface of the game
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add the sprite to the game.
     * @param g - the game
     */
    void addToGame(GameLevel g);
}