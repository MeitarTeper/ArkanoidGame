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
 * Information of level Two.
 */
public class LevelTwoInfo implements LevelInformation {

    private static final int SPEED = 5;
    private static final int BALLS_NUM = 10;
    private static final int BLOCKS_NUM = 15;
    private static final int PADDLE_W = 500;
    private static final int BLOCKS_Y = 250;
    private static final int BLOCKS_X = 20;
    private static final int BLOCKS_H = 25;
    private static final double BLOCKS_W = 50.6;
    private int ballsNum;
    private int blocksNum;

    /**
     * Level Two constructor.
     */
    public LevelTwoInfo() {
        this.ballsNum = BALLS_NUM;
        this.blocksNum = BLOCKS_NUM;
    }

    @Override
    public int numberOfBalls() {
        return this.ballsNum;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        int firstAngle = 10;
        int secAngle = 310;
        for (int i = 0; i < ballsNum; i++) {
            if (i < 5) {
                velocities.add(Velocity.fromAngleAndSpeed(firstAngle, SPEED));
                firstAngle += 10;
            }
            velocities.add(Velocity.fromAngleAndSpeed(secAngle, SPEED));
            secAngle += 10;
        }
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
        return "level Two";
    }
    @Override
    public Sprite getBackground() {
        return new Background(this);
    }
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        double width = BLOCKS_W;
        double height = BLOCKS_H;
        double startX = BLOCKS_X;

        for (int i = 0; i < blocksNum; i++) {
            Color color = switch (i) {
                case 0, 1 -> new Color(245, 76, 103);
                case 2, 3 -> Color.MAGENTA;
                case 4, 5 -> new Color(189, 114, 217);
                case 6, 7, 8 -> new Color(166, 108, 239);
                case 9, 10 -> new Color(36, 180, 211);
                case 11, 12 -> new Color(64, 224, 208);
                case 13, 14 -> new Color(20, 162, 148);
                default -> Color.black;
            };
            blocks.add(new Block(new Rectangle(new Point(startX, BLOCKS_Y), width, height), color));
            startX += width;
        }
        return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksNum;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int startX = 150;
        int startY = 150;
        int endX = 20;
        int endY = 250;
        d.setColor(new Color(230, 234, 128));
        while (endX != 780) {
            d.drawLine(startX, startY, endX, endY);
            endX += 20;
        }
        d.fillCircle(startX, startY, 50);
        d.setColor(new Color(210, 213, 74));
        d.fillCircle(startX, startY, 45);
        d.setColor(new Color(248, 244, 11));
        d.fillCircle(startX, startY, 40);
    }
}
