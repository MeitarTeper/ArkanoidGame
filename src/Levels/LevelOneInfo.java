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
 * Information of level one.
 */
public class LevelOneInfo implements LevelInformation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int SPEED = 5;
    private static final int P_WIDTH = 100;
    private static final int BLOCK = 30;
    private static final int HALF_BLOCK = 15;
    private int ballsNum;
    private int blocksNum;

    /**
     * LevelOneInfo constructor.
     */
    public LevelOneInfo() {
        this.ballsNum = 1;
        this.blocksNum = 1;
    }

    @Override
    public int numberOfBalls() {
        return this.ballsNum;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, SPEED));

        return velocities;
    }
    @Override
    public int paddleSpeed() {
        return SPEED;
    }
    @Override
    public int paddleWidth() {
        return P_WIDTH;
    }
    @Override
    public String levelName() {
        return "level One";
    }
    @Override
    public Sprite getBackground() {
        return new Background(this);
    }
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        blocks.add(new Block(new Rectangle(new Point((double) (WIDTH / 2) - HALF_BLOCK,
                (double) HEIGHT / 3 - HALF_BLOCK), BLOCK, BLOCK), Color.WHITE));
        return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksNum;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, WIDTH, HEIGHT);

        int xCenter = WIDTH / 2;
        int yCenter = HEIGHT / 3;
        d.setColor(Color.BLUE);
        d.drawCircle(xCenter, yCenter, 60);
        d.drawCircle(xCenter, yCenter, 90);
        d.drawCircle(xCenter, yCenter, 120);
        d.drawLine(xCenter, 350, xCenter, 225);
        d.drawLine(xCenter, 175, xCenter, 50);
        d.drawLine(425, yCenter, 550, yCenter);
        d.drawLine(375, yCenter, 250, yCenter);
    }
}
