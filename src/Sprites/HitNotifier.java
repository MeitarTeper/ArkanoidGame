//Meitar Teper 314708397

package Sprites;

/**
 * Interface for objects that can notify listeners of hit events.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl - the HitListener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - the HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}
