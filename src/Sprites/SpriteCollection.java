//Meitar Teper 314708397

package Sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Sprites.SpriteCollection class.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * constructor of the sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * add the current sprite to the sprites list.
     * @param s - current sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * remove the current sprite from the sprites list.
     * @param s - current sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(sprites);
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d - the surface game
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}