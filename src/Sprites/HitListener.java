//Meitar Teper 314708397

package Sprites;

import Geometry.Ball;

/**
 * Interface for objects that listen to hit events.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit - the object
     * @param hitter - the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
