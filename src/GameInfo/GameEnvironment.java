//Meitar Teper 314708397

package GameInfo;

import Geometry.Line;
import Geometry.Point;
import Sprites.Collidable;
import Sprites.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * GameInfo.GameEnvironment class.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * GameInfo.Game environment constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     * @param c - the collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * remove the given collidable from the environment.
     * @param c - the collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Returns the closest collision point between the given trajectory and a collidable object.
     * @param trajectory The trajectory of the object.
     * @return The closest collision point between the trajectory and a collidable object,
     * or null if there is no collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collidablesCopy = new ArrayList<>(collidables);
        CollisionInfo closestCollision = null;

        for (Collidable collidable : collidablesCopy) {
            //check the nearest intersection point on this rectangle
            Point intersection = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (intersection != null) {
                double distance = trajectory.start().distance(intersection);
                // If this is the first collision, or it's closer than the current
                // closest collision, update closestCollision
                if (closestCollision == null
                        || Line.isNumGrader(trajectory.start().distance(closestCollision.collisionPoint()), distance)) {
                    closestCollision = new CollisionInfo(intersection, collidable);
                }
            }
        }
        return closestCollision;
    }
}