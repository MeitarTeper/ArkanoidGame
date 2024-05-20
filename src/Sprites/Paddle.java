//Meitar Teper 314708397

package Sprites;

import GameInfo.GameLevel;
import Geometry.Point;
import Geometry.Ball;
import Geometry.Rectangle;
import Geometry.Velocity;
import Geometry.Line;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Sprites.Paddle class.
 */
public class Paddle implements Sprite, Collidable {
    private static final int LEFT_BORDER = 20;
    private static final int RIGHT_BORDER = 780;
    private static final int REGIONS = 5;
    private static final double REGION_ONE_ANGLE = 300;
    private static final double REGION_TWO_ANGLE = 330;
    private static final double REGION_THREE_ANGLE = 360;
    private static final double REGION_FOUR_ANGLE = 30;
    private static final double REGION_FIVE_ANGLE = 60;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private java.awt.Color color;
    private int speed;

    /**
     * Sprites.Paddle constructor.
     * @param paddle - the rectangle paddle
     * @param speed - the speed paddle
     * @param color - the color paddle
     * @param keyboard - the keyboard
     */
    public Paddle(Rectangle paddle, int speed, java.awt.Color color, KeyboardSensor keyboard) {
        this.paddle = paddle;
        this.speed = speed;
        this.color = color;
        this.keyboard = keyboard;
    }

    /**
     * Moves the paddle to the left by the paddle's current speed. If the paddle is already at the left border
     * of the game screen, the method will not move the paddle any further.
     */
    public void moveLeft() {
        if (Line.isNumGrader(this.paddle.getUpperLeft().getX(), LEFT_BORDER)) {
            Point newUpperPoint = new Point(this.paddle.getUpperLeft().getX() - this.speed,
                    this.paddle.getUpperLeft().getY());
            this.paddle = new Rectangle(newUpperPoint, this.paddle.getWidth(), this.paddle.getHeight());
        }
    }
    /**
     * Moves the paddle to the right by the paddle's current speed. If the paddle is already at the right border
     * of the game screen, the method will not move the paddle any further.
     */
    public void moveRight() {
        if (Line.isNumGrader(RIGHT_BORDER, this.paddle.getUpperLeft().getX() + this.paddle.getWidth())) {
            Point newUpperPoint = new Point(this.paddle.getUpperLeft().getX() + this.speed,
                    this.paddle.getUpperLeft().getY());
            this.paddle = new Rectangle(newUpperPoint, this.paddle.getWidth(), this.paddle.getHeight());
        }
    }
    /**
     * Updates the position of the paddle based on the user's keyboard input.
     * If the left arrow key is pressed, the paddle
     * moves to the left. If the right arrow key is pressed, the paddle moves to the right.
     */
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     * @param d - the DrawSurface to draw the paddle on.
     */
    public void drawOn(DrawSurface d) {
        int x = (int) Math.round(this.paddle.getUpperLeft().getX());
        int y = (int) Math.round(this.paddle.getUpperLeft().getY());
        int width = (int) Math.round(this.paddle.getWidth());
        int height = (int) Math.round(this.paddle.getHeight());
        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
    }

    /**
     * @return the collision rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * calculate the region of the collision point on the paddle and update the velocity according to that.
     * @param collisionPoint - the collision point
     * @param currentVelocity - the current velocity
     * @param hitter - the ball that made the hit
     * @return the new velocity
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        Velocity newVelocity;
        int region = (int) ((collisionPoint.getX() - this.paddle.getUpperLeft().getX())
                / (this.paddle.getWidth() / REGIONS) + 1);
        Double angle = REGION_THREE_ANGLE;
        if (region == 1) {
            angle = REGION_ONE_ANGLE;
        } else if (region == 2) {
            angle = REGION_TWO_ANGLE;
        } else if (region == 4) {
            angle = REGION_FOUR_ANGLE;
        } else if (region == 5) {
            angle = REGION_FIVE_ANGLE;
        }
        newVelocity = Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed());
        return newVelocity;
    }

    /**
     * add the paddle to the game.
     * @param g - game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}