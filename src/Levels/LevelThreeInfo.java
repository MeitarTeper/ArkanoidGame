//Meitar Teper 314708397

package Levels;

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Sprites.Background;
import Sprites.Block;
import Sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Information of level Three.
 */
public class LevelThreeInfo implements LevelInformation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int SPEED = 5;
    private static final int ANGLE_BALL_ONE = 50;
    private static final int ANGLE_BALL_TWO = 300;
    private static final int PADDLE_W = 100;
    private static final int BLOCK_H = 25;
    private static final int BLOCK_W = 60;
    private static final int BLOCK_START_Y = 300;
    private static final int S_BLOCKS_NUMS = 6;
    private static final int E_BLOCKS_NUMS = 12;
    private static final int RIGHT_BORDER = 780;
    private int ballsNum;
    private int blocksNum;

    @Override
    public int numberOfBalls() {
        return this.ballsNum;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(ANGLE_BALL_ONE, SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(ANGLE_BALL_TWO, SPEED));
        this.ballsNum = velocities.size();
        return velocities;
    }
    @Override
    public int paddleSpeed() {
        return SPEED;
    }
    @Override
    public int paddleWidth() {
        return PADDLE_W;
    }
    @Override
    public String levelName() {
        return "level Three";
    }
    @Override
    public Sprite getBackground() {
        return new Background(this);
    }
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        double width = BLOCK_W;
        double height = BLOCK_H;
        int startY = BLOCK_START_Y;

        // Add blocks in the game's pattern to the list
        for (int i = S_BLOCKS_NUMS; i < E_BLOCKS_NUMS; i++) {
            int startX = RIGHT_BORDER;
            Color color = switch (i) {
                case 6 -> Color.PINK;
                case 7 -> Color.MAGENTA;
                case 8 -> Color.RED;
                case 9 -> Color.ORANGE;
                case 10 -> Color.YELLOW;
                default -> Color.GREEN;
            };
            for (int j = 0; j < i; j++) {
                startX -= width;
                blocks.add(new Block(new Rectangle(new Point(startX, startY), width, height), color));
            }
            startY -= height;
        }
        this.blocksNum = blocks.size();
        return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksNum;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(77, 217, 170));
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        d.setColor(new Color(119, 121, 115));
        d.fillRectangle(95, 200, 15, 400);
        d.setColor(new Color(230, 234, 128));
        d.fillCircle(102, 200, 15);
        d.setColor(Color.RED);
        d.fillCircle(102, 200, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(102, 200, 2);
        d.setColor(new Color(59, 59, 57));
        d.fillRectangle(85, 350, 35, 250);
        d.setColor(Color.BLACK);
        d.fillRectangle(50, 400, 100, 200);
        d.setColor(Color.WHITE);


        int startY = 410;
        int width = 9;
        int height = 30;
        for (int i = 0; i < 5; i++) {
            int startX = 59;
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(startX, startY, width, height);
                startX += 2 * width;
            }
            startY += height + 10;
        }
    }
}
