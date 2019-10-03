package activityStarterCode.basicClasses;

import java.util.Random;

/**
 * Represents a random walk along the integer
 * number line starting at some value. At each step it either moves
 * forward or backward by one unit.
 */
public class RandomWalk {
    protected static final Random RNG = new Random();

    private int value;
    private int min, max;

    /**
     * Creates a new random walk starting with 0 as value.
     */
    public RandomWalk() {
        this(0);
    }

    /**
     * Creates a new random walk starting with initialValue
     * @param initialValue the starting value for value
     */
    public RandomWalk(int initialValue) {
        this(initialValue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public RandomWalk(int value, int min, int max) {
        this.value = value;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the current value for the random walk.
     */
    public int getValue() {
        return value;
    }

    /**
     * Updates the value randomly by adding either +1 or -1.
     * @return Updated value.
     */
    public int advanceValue() {
        if (value == max) {
            return --value;
        }

        if (value == min || RNG.nextBoolean()) {
            return ++value;
        }

        return --value;
    }
}

