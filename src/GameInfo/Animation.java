//Meitar Teper 314708397

package GameInfo;
import biuoop.DrawSurface;

/**
 * The Animation interface represents an animation in a game.
 * It provides methods to perform a single frame of the animation
 * and determine if the animation should stop.
 */
public interface Animation {
    /**
     * Perform a single frame of the animation.
     * @param d the DrawSurface to render the frame on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Check if the animation should stop.
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}
