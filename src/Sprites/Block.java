//Meitar Teper 314708397

package Sprites;

import GameInfo.GameLevel;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Geometry.Ball;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Sprites.Block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final int TOP_LINE = 1;
    private static final int BOTTOM_LINE = 2;
    private static final int LEFT_LINE = 3;
    private static final int RIGHT_LINE = 4;

    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor of the block.
     * @param rectangle - the block rectangle
     * @param color - the block color
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return the block rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * Computes the new velocity of the ball after hitting a specific side of a colliding object.
     * @param collisionPoint - The point at which the collision occurred.
     * @param currentVelocity - The current velocity of the ball, before the collision.
     * @param hitter - the ball that made the hit.
     * @return The new velocity of the ball, after the collision.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        Velocity newVelocity = currentVelocity;
        Line[] sides = rectangle.getSides();
        //hit the top line
        if (sides[0].inLines(collisionPoint)) {
            newVelocity = new Velocity(newVelocity.getDx(), -newVelocity.getDy());
            this.getCollisionRectangle().setIntersectionSide(TOP_LINE);
        }
        //hit the bottom line
        if (sides[1].inLines(collisionPoint)) {
            newVelocity = new Velocity(newVelocity.getDx(), -newVelocity.getDy());
            this.getCollisionRectangle().setIntersectionSide(BOTTOM_LINE);
        }
        //hit the left side
        if (sides[2].inLines(collisionPoint)) {
            newVelocity = new Velocity(-newVelocity.getDx(), newVelocity.getDy());
            this.getCollisionRectangle().setIntersectionSide(LEFT_LINE);
        }
        //hit the right side
        if (sides[3].inLines(collisionPoint)) {
            newVelocity = new Velocity(-newVelocity.getDx(), newVelocity.getDy());
            this.getCollisionRectangle().setIntersectionSide(RIGHT_LINE);
        }
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * Notifies all registered hit listeners about a hit event.
     * @param hitter - the ball that caused the hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Draws the ball on a given surface.
     * @param surface The surface on which the ball will be drawn.
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) Math.round(this.rectangle.getUpperLeft().getX());
        int y = (int) Math.round(this.rectangle.getUpperLeft().getY());
        int width = (int) Math.round(this.rectangle.getWidth());
        int height = (int) Math.round(this.rectangle.getHeight());
        //set the color of the rectangle and draw it
        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);
        // Set the color of the rectangle's border and draw the rectangle
        surface.setColor(Color.black);
        surface.drawRectangle(x, y, width, height);
    }

    /**
     * time passed method.
     */
    public void timePassed() { }

    /**
     * add the block to the game.
     * @param g - the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * remove the block from the game.
     * @param gameLevel - the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }


}
