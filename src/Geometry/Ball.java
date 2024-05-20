//Meitar Teper 314708397

package Geometry;

import GameInfo.GameLevel;
import GameInfo.GameEnvironment;
import Sprites.CollisionInfo;
import Sprites.Sprite;
import biuoop.DrawSurface;

/**
 * Geometry.Ball class.
 */
public class Ball implements Sprite {
    private static final double ROUND = 0.5;
    private static final int TOP_LINE = 1;
    private static final int BOTTOM_LINE = 2;
    private static final int LEFT_LINE = 3;
    private static final int RIGHT_LINE = 4;
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;

    /**
     * constructor of the ball.
     * @param x - x value of the center
     * @param y - y value of the center
     * @param r - radius of the ball
     * @param color - color of the ball
     * @param gameEnvironment - the game environment
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * constructor of the ball.
     * @param center - center of the ball
     * @param r - radius of the ball
     * @param color - color of the ball
     * @param gameEnvironment - the game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Seter of the velocity.
     * @param v - the velocity
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Seter of the velocity.
     * @param dx - the dx of the velocity
     * @param dy - the dy of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * @return the x value of the center
     */
    public int getX() {
        double x = this.center.getX();
        if (x - Math.floor(x) <= ROUND) {
            return (int) Math.floor(x);
        } else {
            return (int) Math.ceil(x);
        }
    }

    /**
     * @return the y value of the center
     */
    public int getY() {
        double y = this.center.getY();
        if (y - Math.floor(y) <= ROUND) {
            return (int) Math.floor(y);
        } else {
            return (int) Math.ceil(y);
        }
    }

    /**
     * @return the radius size
     */
    public int getSize() {
        return this.r;
    }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on the given DrawSurface.
     * @param surface - the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Updates the position of the ball and checks if it collides with any object in the game environment.
     */
    public void timePassed() {
        //compute the ball trajectory and check if moving on this trajectory will hit anything
        Point nextCenter = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, nextCenter);
        CollisionInfo collision = gameEnvironment.getClosestCollision(trajectory);
        //the ball hit a block
        if (collision != null) {
            Velocity newVelocity = collision.collisionObject().hit(collision.collisionPoint(), this.getVelocity(),
                    this);
            nextCenter = intersectSide(collision, nextCenter);
            this.setVelocity(newVelocity);
        }
        // Update the center of the ball with its new position.
        this.center = nextCenter;
    }

    /**
     * @param collision - The information regarding the collision that occurred.
     * @param nextCenter - The current center of the ball, before the collision.
     * @return The new center of the ball, after the collision.
     */
    public Point intersectSide(CollisionInfo collision, Point nextCenter) {
        if (this.getVelocity() == null) {
            this.getVelocity().setDefaultVelocity();
        }
        Point newCenter = nextCenter;
        //hit the top line
        if (collision.collisionObject().getCollisionRectangle().getIntersectionSide() == TOP_LINE) {
            newCenter = new Point(collision.collisionPoint().getX(),
                    collision.collisionPoint().getY() - this.r);
            //hit the bottom line
        } else if (collision.collisionObject().getCollisionRectangle().getIntersectionSide() == BOTTOM_LINE) {
            newCenter = new Point(collision.collisionPoint().getX(),
                    collision.collisionPoint().getY() + this.r);
        }
        //hit the left side
        if (collision.collisionObject().getCollisionRectangle().getIntersectionSide() == LEFT_LINE) {
            newCenter = new Point(collision.collisionPoint().getX() - this.r, nextCenter.getY());
            //hit the right side
        } else if (collision.collisionObject().getCollisionRectangle().getIntersectionSide() == RIGHT_LINE) {
            newCenter = new Point(collision.collisionPoint().getX() + this.r, nextCenter.getY());
        }
        return newCenter;
    }

    /**
     * add the ball to the game.
     * @param g - game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove the ball from the game.
     * @param g - the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}