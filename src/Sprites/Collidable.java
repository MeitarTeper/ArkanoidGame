//Meitar Teper 314708397

package Sprites;
import GameInfo.GameLevel;
import Geometry.Ball;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;

/**
 * Sprites.Collidable interface.
 */
public interface Collidable {
    /**
     * @return - the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint - the collision point
     * @param currentVelocity - the current velocity
     * @param hitter - the ball that made the hit
     * @return the new velocity expected after the hit
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);

    /**
     * add the collidebles to the game.
     * @param g - the game
     */
    void addToGame(GameLevel g);
}