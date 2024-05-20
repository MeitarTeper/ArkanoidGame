//Meitar Teper 314708397

package GameInfo;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation class.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean shouldStop;
    private boolean isAlreadyPressed;

    /**
     * KeyPressStoppableAnimation constructor.
     * @param keyboard - the keyboard
     * @param key - the key
     * @param animation - the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String key, Animation animation) {
        this.keyboard = keyboard;
        this.key = key;
        this.animation = animation;
        this.shouldStop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //check if the key is really pressed
        if (this.isAlreadyPressed) {
            if (!this.keyboard.isPressed(this.key)) {
                this.isAlreadyPressed = false;
            }
        } else {
            if (this.keyboard.isPressed(this.key)) {
                this.shouldStop = true;
            }
        }
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }
}
