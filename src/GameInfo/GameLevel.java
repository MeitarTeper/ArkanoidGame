//Meitar Teper 314708397

package GameInfo;

import Geometry.Ball;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Levels.LevelInformation;
import Levels.LevelNameIndicator;
import Sprites.BlockRemover;
import Sprites.BallRemover;
import Sprites.Collidable;
import Sprites.Sprite;
import Sprites.SpriteCollection;
import Sprites.Block;
import Sprites.ScoreIndicator;
import Sprites.Paddle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class game.
 */
public class GameLevel implements Animation {
    private static final int RIGHT_BORDER = 780;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int FRAME_LEN = 20;
    private static final int X_CENTER = 400;
    private static final int Y_CENTER = 550;
    private static final int RADIUS = 5;
    private static final int Y_CENTER_PADDLE = 560;
    private static final int PADDLE_H = 20;
    private static final int WIN_SCORE = 100;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private biuoop.KeyboardSensor keyboard;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter score;
    private ScoreIndicator scoreIndicator;
    private ScoreTrackingListener scoreTrackingListener;
    private AnimationRunner runner;
    private LevelInformation levelInformation;
    private boolean running;

    /**
     * GameLevel constructor.
     * @param levelInformation - the level information
     * @param gui - the gui
     * @param score - the score
     * @param scoreIndicator - the score indicator
     * @param scoreTrackingListener - the score Tracking Listener
     * @param keyboard - the keyboard
     * @param runner - the runner
     */
    public GameLevel(LevelInformation levelInformation, GUI gui, Counter score, ScoreIndicator scoreIndicator,
                     ScoreTrackingListener scoreTrackingListener,
                     KeyboardSensor keyboard, AnimationRunner runner) {
        this.gui = gui;
        this.keyboard = keyboard;
        this.runner = runner;
        this.levelInformation = levelInformation;
        this.score = score;
        this.scoreIndicator = scoreIndicator;
        this.scoreTrackingListener = scoreTrackingListener;
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.blockRemover = new BlockRemover(this, this.blocksCounter);
        this.ballRemover = new BallRemover(this, this.ballsCounter);
    }

    /**
     * Initialize a new game: create the Blocks and Geometry.Ball (and Sprites.Paddle) and add them to the game.
     */
    public void initialize() {
        int paddleWidth = levelInformation.paddleWidth();
        int midPaddle = paddleWidth / 2;
        Rectangle pad = new Rectangle(new Point(X_CENTER - (double) midPaddle, Y_CENTER_PADDLE),
                this.levelInformation.paddleWidth(), PADDLE_H);
        Paddle paddle = new Paddle(pad, levelInformation.paddleSpeed(), Color.BLUE, keyboard);
        //add the background to the game
        Sprite back = levelInformation.getBackground();
        back.addToGame(this);

        //add the frame blocks to the game
        List<Block> frameBlocks = createFrameBlocks();
        for (int i = 0; i < frameBlocks.size(); i++) {
            Block block = frameBlocks.get(i);
            block.addToGame(this);
            //death-zone
            if (i == 1) {
                block.addHitListener(ballRemover);
            }
        }

        //add the level blocks to the game
        List<Block> blocks = levelInformation.blocks();
        for (Block block : blocks) {
            block.addToGame(this);
            this.blocksCounter.increase(1);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }


        //add the level blocks to the game
        List<Velocity> velocities = levelInformation.initialBallVelocities();
        for (Velocity velocity : velocities) {
            Ball ball = new Ball(X_CENTER, Y_CENTER, RADIUS, Color.RED, environment);
            ball.setVelocity(velocity);
            ball.addToGame(this);
            ballsCounter.increase(1);
        }

        LevelNameIndicator levelName = new LevelNameIndicator(levelInformation.levelName());
        levelName.addToGame(this);
        paddle.addToGame(this);
        scoreIndicator.addToGame(this);
        this.running = true;

    }

    /**
     * runs the level game.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 4, this.sprites));
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        //pause
        if (this.keyboard.isPressed("p")) {
            Animation pauseScreen = new PauseScreen();
            runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, pauseScreen));
        }

        // stopping condition
        if (noMoreBalls()) {
            this.running = false;
        }
        if (this.blocksCounter.getValue() == 0) {
            //add score for winning
            this.score.increase(WIN_SCORE);
            this.running = false;
        }
    }


    /**
     * add collidable to the game.
     * @param c - the cillidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * remove collidable from the game.
     * @param c - the cillidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove sprite from the game.
     * @param s - the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * add sprite to the game.
     * @param s - the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * check if there is no more balls.
     * @return true/false
     */
    public boolean noMoreBalls() {
        return this.ballsCounter.getValue() == 0;
    }

    /**
     * create the frame blocks for the game.
     * @return list of the frame blocks
     */
    private List<Block> createFrameBlocks() {
        List<Block> blocks = new ArrayList<>();
        //score
        blocks.add(new Block(new Rectangle(new Point(0, 0), WIDTH, FRAME_LEN), Color.WHITE));
        //down
        blocks.add(new Block(new Rectangle(new Point(0, HEIGHT - FRAME_LEN), WIDTH, FRAME_LEN), Color.lightGray));
        //left
        blocks.add(new Block(new Rectangle(new Point(0, FRAME_LEN), FRAME_LEN, HEIGHT), Color.lightGray));
        //right
        blocks.add(new Block(new Rectangle(new Point(RIGHT_BORDER, FRAME_LEN), FRAME_LEN, HEIGHT), Color.lightGray));
        //up
        blocks.add(new Block(new Rectangle(new Point(0, FRAME_LEN), WIDTH, FRAME_LEN), Color.lightGray));
        return blocks;
    }


}

