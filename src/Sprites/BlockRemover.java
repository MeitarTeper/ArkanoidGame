//Meitar Teper 314708397

package Sprites;

import GameInfo.Counter;
import GameInfo.GameLevel;
import Geometry.Ball;

/**
 * a BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor of the BlockRemover.
     * @param gameLevel - the game
     * @param removedBlocks - counter of the removed blocks.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Removes the block from the game and decreases the remaining blocks count.
     * @param beingHit - the block that was hit
     * @param hitter -the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
