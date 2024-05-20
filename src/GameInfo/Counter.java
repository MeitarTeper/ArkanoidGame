//Meitar Teper 314708397

package GameInfo;

/**
 * The Counter class represents a simple counter that can be incremented or decremented.
 */
public class Counter {
    private int count;

    /**
     * Constructs a new Counter with an initial count of 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * add number to current count.
     * @param number - the number
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     * @param number - the number
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * returns the current count.
     * @return the current count
     */
    public int getValue() {
        return count;
    }
}
