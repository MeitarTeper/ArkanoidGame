//Meitar Teper 314708397

package Geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * Geometry.Rectangle class.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private int intersectionSide;

    /**
     * rectangle constructor.
     * @param upperLeft - location of the rectangle
     * @param width - width of the rectangle
     * @param height - heigt of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * intersection rectangle side.
     * @param intersectionSide - intersection side
     */
    public void setIntersectionSide(int intersectionSide) {
        this.intersectionSide = intersectionSide;
    }
    /**
     * Returns a list of intersection points between the given line and the rectangle.
     * @param line - the line to check for intersection with the rectangle
     * @return a list of intersection points between the given line and the rectangle
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        Line[] sides = this.getSides();
        //check for intersections points on each side of the rectangle
        for (Line side : sides) {
            if (line.isIntersecting(side)) {
                Point intersection = line.intersectionWith(side);
                // if the lines are collinear and overlap, the intersection point will be the nearest point
                if (intersection == null) {
                    intersection = Line.compareDoubles(side.start().distance(line.end()),
                            side.end().distance(line.end())) ? line.end() : line.start();
                }
                intersectionPoints.add(intersection);
            }
        }
        return intersectionPoints;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * get the sides of this rectangle.
     * @return an array of the sides
     */
    public Line[] getSides() {
        Point upperRight = new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY());
        Point downLeft = new Point(this.getUpperLeft().getX(),
                this.getUpperLeft().getY() + this.getHeight());
        Point downRight = new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
        Line topLine = new Line(this.getUpperLeft(), upperRight);
        Line bottomLine = new Line(downLeft, downRight);
        Line leftVertical = new Line(this.getUpperLeft(), downLeft);
        Line rightVertical = new Line(upperRight, downRight);
        return new Line[] {topLine, bottomLine, leftVertical, rightVertical};
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the side which intersect with the ball
     */
    public int getIntersectionSide() {
        return this.intersectionSide;
    }
}