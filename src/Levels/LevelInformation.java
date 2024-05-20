//Meitar Teper 314708397

package Levels;

import Geometry.Velocity;
import Sprites.Block;
import Sprites.Sprite;
import biuoop.DrawSurface;

import java.util.List;

/**
 * LevelInformation interface.
 */
public interface LevelInformation {
    /**
     * the number of balls of the level.
     * @return - the number.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * @return a list of the velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * the paddle speed of the level.
     * @return the speed
     */
    int paddleSpeed();

    /**
     * the paddle width of the level.
     * @return the width
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return - the name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return the background sprite
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level.
     * @return - a list of the blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * @return - the number of blocks
     */
    int numberOfBlocksToRemove();

    /**
     * the draw of the background.
     * @param d - the DrawSurface
     */
    void drawOn(DrawSurface d);
}
