//Meitar Teper 314708397

package Sprites;

import GameInfo.Counter;
import GameInfo.GameLevel;
import Geometry.Ball;

/**
 * The BallRemover class is responsible for removing balls from the game when they hit a block.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor of the BallRemover.
     * @param gameLevel - the game
     * @param removedBalls - the counter to track the remaining balls.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * Removes the ball from the game and decreases the remaining balls count.
     * @param beingHit - the block that was hit
     * @param hitter -the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}
