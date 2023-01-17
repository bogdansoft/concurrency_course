package org.example;

/**
 * Min - Max Metrics
 * In this exercise, we are going to implement a class called MinMaxMetrics .
 * <p>
 * A single instance of this class will be passed to multiple threads in our application.
 * <p>
 * MinMaxMetrics is an analytics class and is used to keep track of the minimum and the maximum of a particular business or performance metric in our application.
 * <p>
 * Example:
 * <p>
 * A stock trading application that keeps track of the minimum and maximum price of the stock on a daily basis.
 * <p>
 * <p>
 * <p>
 * The class will have 3 methods:
 * <p>
 * addSample(..) - Takes a new sample.
 * <p>
 * getMin() - Returns the sample with the minimum value we have seen so far.
 * <p>
 * getMax() - Returns the sample with the maximum value we have seen so far.
 * <p>
 * <p>
 * <p>
 * Notes:
 * <p>
 * - Each method can be called concurrently by any given number of threads, so the class needs to be thread-safe.
 * <p>
 * - In addition, this class is used for analytics, so it needs to be as performant as possible, as we don't want it to slow down our business logic threads too much.
 * <p>
 * - Threads that call getMin() or getMax() are interested in only one of the values and are never interested in both the min and the max at the same time
 * <p>
 * <p>
 * Please implement MinMaxMetrics below:
 * <p>
 * <p>
 * <p>
 * Important Note:
 * <p>
 * Only the logic of the class is unit tested, and it is impossible to test a class's thread safety.
 * <p>
 * The solution to the exercise is provided in the next lecture.
 */
public class MinMaxMetricsVolatile {
    // Add all necessary member variables
    private volatile long minValue;
    private volatile long maxValue;

    /**
     * Initializes all member variables
     */
    public MinMaxMetricsVolatile() {
        // Add code here
        minValue = Long.MIN_VALUE;
        maxValue = Long.MAX_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public void addSample(long newSample) {
        // Add code here
        synchronized (this) {
            this.minValue = Math.min(newSample, this.minValue);
            this.maxValue = Math.max(newSample, this.maxValue);
        }
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public synchronized long getMin() {
        // Add code here
        return minValue;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public synchronized long getMax() {
        // Add code here
        return maxValue;
    }
}

