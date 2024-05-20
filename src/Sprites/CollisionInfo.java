//Meitar Teper 314708397

package Sprites;
import Geometry.Point;

/**
 * Sprites.CollisionInfo class.
 */
public class CollisionInfo {
    private Point collision;
    private Collidable collidable;
    /**
     * collisionInfo constructor.
     * @param collision - the collision point
     * @param collidable - the collidable
     */
    public CollisionInfo(Point collision, Collidable collidable) {
        this.collision = collision;
        this.collidable = collidable;
    }
    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}