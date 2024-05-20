//Meitar Teper 314708397

package GameInfo;

import Geometry.Ball;
import Sprites.Block;
import Sprites.HitListener;

/**
 * The ScoreTrackingListener class is responsible for tracking the score in a game.
 */
public class ScoreTrackingListener implements HitListener {
    private static final int ADD_SCORE = 5;
    private Counter currentScore;

    /**
     * Constructs a new ScoreTrackingListener.
     * @param scoreCounter the counter to track the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Updates the score when a block is hit.
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(ADD_SCORE);
    }
}
