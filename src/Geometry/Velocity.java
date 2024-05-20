//Meitar Teper 314708397

package Geometry;

/**
 * Geometry.Velocity class.
 * Geometry.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor of Geometry.Velocity.
     * @param dx - dx of the velocity
     * @param dy - dy of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Calculate the velocity from given angle and speed.
     * @param angle - the angle
     * @param speed - the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radAngle = Math.toRadians(angle);
        double dx = speed * (Math.sin(radAngle));
        double dy = speed * (-Math.cos(radAngle));
        return new Velocity(dx, dy);
    }

    /**
     * set default velocity if the velocity is null.
     */
    public void setDefaultVelocity() {
        this.dx = 1;
        this.dy = 1;
    }

    /**
     * @return the dx of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the dy of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p - the initial point
     * @return - new point after the calculation
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Returns the speed (magnitude of the velocity vector).
     * @return the speed of the velocity.
     */
    public double getSpeed() {
        Double dx = this.getDx();
        Double dy = this.getDy();
        return Math.sqrt(dx * dx + dy * dy);
    }
}