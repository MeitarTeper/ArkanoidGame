//Meitar Teper 314708397

package Geometry;

/**
 * Geometry.Point class.
 */
public class Point {
    private static final double THRESHOLD = 0.000001d;

    private double x;
    private double y;

    /**
     * Constructor of a point.
     * @param x - x of the point
     * @param y - y of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate the distance of two points.
     * @param other - the other point
     * @return distance of this point to the other point
     */
    public double distance(Point other) {
        double xDist = other.x - x;
        double yDist = other.y - y;
        return Math.sqrt(xDist * xDist + yDist * yDist);
    }

    /**
     * Check if two points are equal.
     * @param other - the other point
     * @return - return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return (Math.abs(other.x - this.x) < THRESHOLD) && (Math.abs(other.y - this.y) < THRESHOLD);
    }

    /**
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }
    /**
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }
}
