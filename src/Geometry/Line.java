//Meitar Teper 314708397

package Geometry;
import java.util.List;

/**
 * Geometry.Line class.
 */
public class Line {
    private static final double THRESHOLD = 0.000001d;
    private static final int MID_DIVIDE = 2;
    private Point start;
    private Point end;
    private final double slope;

    /**
     * Constructor of the line.
     * @param start - start point of the line
     * @param end - end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.slope = numsEquals(end.getX(), start.getX())
                ? Double.POSITIVE_INFINITY : (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    /**
     * Constructor of the line.
     * @param x1 - x value of the starting point
     * @param y1 - y value of the starting point
     * @param x2 - x value of the ending point
     * @param y2 - y value of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.slope =  numsEquals(end.getX(), start.getX())
                ? Double.POSITIVE_INFINITY : (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    /**
     * @return Return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Calculate the middle point of the line.
     * @return Returns the middle point
     */
    public Point middle() {
        double xMid = (this.start.getX() + this.end.getX()) / MID_DIVIDE;
        double yMid = (this.start.getY() + this.end.getY()) / MID_DIVIDE;
        return new Point(xMid, yMid);
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Check if two lines are intersecting.
     * @param other - the other line
     * @return Returns true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //if there is intersecting point
        if (this.intersectionWith(other) != null) {
            return true;
            // the lines are equals
        } else if (this.equals(other)) {
            return true;
            // if one of the lines is a point ,or they are parallel - check if one of them is includes in the other
        } else if (numsEquals(start.getX(), end.getX()) && numsEquals(other.start.getX(), other.end.getX())
                || numsEquals(slope, other.slope)) {
            return (this.inLines(other.start) || this.inLines(other.end))
                    || (other.inLines(start) || other.inLines(end));
        } else {
            return false;
        }
    }

    /**
     * calculate the intersecting point of two lines.
     * @param other - the other line
     * @return if there isn't a point, or in case of inclusion returns null. else, return the point
     */
    public Point intersectionWith(Line other) {
        // Calculate the line equation
        double b1 = this.start.getY() - (this.slope * this.start.getX());
        double b2 = other.start.getY() - (other.slope * other.start.getX());

        //if the lines are equals
        if (this.equals(other)) {
            return null;
        }

        //if they are parallel or vertical
        if (numsEquals(this.slope, other.slope) && numsEquals(b1, b2)
                || slope == Double.POSITIVE_INFINITY && other.slope == Double.POSITIVE_INFINITY) {
            //check if one of the points are equal.
            // if they are - check that the other ones aren't include inside one of the lines.
            if (start.equals(other.start) && !this.inLines(other.end) && !other.inLines(this.end)
                    || start.equals(other.end) && !this.inLines(other.start) && !other.inLines(this.end)) {
                return start;
            } else if ((end.equals(other.end) && !this.inLines(other.start) && !other.inLines(this.start))
                    || (end.equals(other.start) && !this.inLines(other.end) && !other.inLines(this.start))) {
                return end;
            } else {
                return null;
            }
        }
        double x, y;
        // this line is vertical - calculate x,y according to the other line
        if (this.slope == Double.POSITIVE_INFINITY || this.slope == Double.NEGATIVE_INFINITY) {
            x = this.start.getX();
            y = other.slope * x + b2;
            // other line is vertical - calculate x,y according to this line
        } else if (other.slope == Double.POSITIVE_INFINITY || other.slope == Double.NEGATIVE_INFINITY) {
            x = other.start.getX();
            y = this.slope * x + b1;
        } else {
            x = (b2 - b1) / (this.slope - other.slope);
            y = this.slope * x + b1;
        }
        Point intersect = new Point(x, y);
        //verifies that the calculated point is inside the segments lines
        if (!this.inLines(intersect) || !other.inLines(intersect)) {
            return null;
        } else {
            return intersect;
        }
    }

    /**
     * Check if two lines are equals.
     * @param other - the other line
     * @return - true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return start.equals(other.start) && end.equals(other.end)
                || start.equals(other.end) && end.equals(other.start);
    }

    /**
     * check if two doubles are equals.
     * @param first - the first number
     * @param sec - the second number
     * @return true if equals, false otherwise
     */
    public static boolean numsEquals(double first, double sec) {
        return Math.abs(first - sec) < THRESHOLD;
    }


    /**
     * Checking if given point is inside the line segments.
     * @param point - the given point
     * @return - true if it is, false otherwise
     */
    public boolean inLines(Point point) {
        double y = point.getY();
        double x = point.getX();
        // check if x,y is inside the axis of the stating, ending points of the line
        return compareDoubles(y, Math.min(this.start.getY(), this.end.getY()))
                && compareDoubles(Math.max(this.start.getY(), this.end.getY()), y)
                && compareDoubles(x, Math.min(this.start.getX(), this.end.getX()))
                && compareDoubles(Math.max(this.start.getX(), this.end.getX()), x);
    }

    /**
     * Compare two doubles.
     * @param num1 - first number
     * @param num2 - second number
     * @return true if num1 > num2 or they are equals
     */
    public static boolean compareDoubles(double num1, double num2) {
        return num1 - num2 > THRESHOLD || Math.abs(num1 - num2) < THRESHOLD;
    }

    /**
     * Check if one number is grader then the second.
     * @param num1 - fist number
     * @param num2 - second number
     * @return true if num1 > num2
     */
    public static boolean isNumGrader(double num1, double num2) {
        return num1 - num2 > THRESHOLD;
    }

    /**
     * Finds the closest intersection point to the start of the line that intersects with the given rectangle.
     * @param rect - the rectangle to check for intersection points with the line
     * @return  the closest intersection point to the start of the line, or null if there are no intersection points
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        Point closestIntersection = null;
        double shortestDistance = Double.POSITIVE_INFINITY;
        for (Point point : intersectionPoints) {
            double distance = point.distance(this.start);
            if (isNumGrader(shortestDistance, distance)) {
                closestIntersection = point;
                shortestDistance = distance;
            }
        }
        return closestIntersection;
    }
}