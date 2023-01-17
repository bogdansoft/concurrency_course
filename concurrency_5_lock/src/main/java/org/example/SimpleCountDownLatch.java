package org.example;

/**
 * Simple CountDownLatch
 * As part of the java.util.concurrent package, the JDK contains many classes that help us coordinate between threads.
 * <p>
 * A few of those include the CountDownLatch, CyclicBarrier, Phaser, Exchanger, and others that may be added in future versions of the JDK.
 * <p>
 * However, all those classes are no more than higher-level wrappers built upon the same building blocks that we learned and already know very well. As such, if we never knew those classes existed, we wouldn't even need them, because we can implement their functionality with the tools we already have.
 * <p>
 * However it is still recommended to get somewhat familiar with at least some of them as they represent useful patterns.
 * <p>
 * In this exercise we are going to learn about the CountDownLatch , by implementing a subset of its functionality.
 * The SimpleCountDownLatch
 * <p>
 * To get familiar with CountDownLatch as well as gain confidence in our ability to implement it and other higher level classes we are going to implement a simple version of the CountDownLatch and implement the majority of the CountDownLatch's functionality.
 * <p>
 * The CountDownLatch and its simple version the SimpleCountDownLatch are initialized with a non-negative count.
 * <p>
 * The SimpleCountDownLatch allows one or more threads to wait until a set of operations being performed in other threads, completes.
 * <p>
 * The SimpleCountDownLatch has the following main operations:
 * <p>
 * countDown() - Decrements the count of the latch, releasing all waiting threads when the count reaches zero. If the current count already equals zero then nothing happens.
 * <p>
 * await() - Causes the current thread to wait until the latch has counted down to zero. If the current count is already zero then this method returns immediately.
 * <p>
 * The await method blocks until the current count reaches zero due to invocations of the countDown() method, after which all waiting threads are released and any subsequent invocations of await return immediately.
 * <p>
 * For more information and examples see CountDownLatch.
 * <p>
 * Hint : Consider using either version of the condition variables (Lock's condition variables or Object's wait/notify). For practice purposes you can try both.
 * <p>
 * Notes:
 * <p>
 * The SimpleCountDownLatch instance is single-use. In other words, once the count goes to zero, the SimpleCountDownLatch has no additional use.
 * <p>
 * The class has to be thread safe
 */
public class SimpleCountDownLatch {
    private int count;

    public SimpleCountDownLatch(int count) {
        this.count = count;
        if (count < 0) {
            throw new IllegalArgumentException("count cannot be negative");
        }
    }

    /**
     * Causes the current thread to wait until the latch has counted down to zero.
     * If the current count is already zero then this method returns immediately.
     */
    public void await() throws InterruptedException {
        synchronized (this) {
            while (this.count > 0) {
                wait();
            }
        }
    }

    /**
     * Decrements the count of the latch, releasing all waiting threads when the count reaches zero.
     * If the current count already equals zero then nothing happens.
     */
    public void countDown() {
        synchronized (this) {
            if (count > 0) {
                count--;

                if (count == 0) {
                    this.notifyAll();
                }
            }
        }
    }

    /**
     * Returns the current count.
     */
    public int getCount() {
        return this.count;
    }
}
/*class WaitNotifyClass {
    boolean isCompleted = false;

    public synchronized void declareSuccess() throws InterruptedException {
        while (!isCompleted) {
            wait();
        }

        System.out.println("Success!!");
    }

    public synchronized void finishWork() {
        isCompleted = true;
        notify();
    }
}

class LockClass {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    boolean isCompleted = false;

    public void declareSuccess() throws InterruptedException {
        lock.lock();
        try {
            while (!isCompleted) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }

        System.out.println("Success!!");
    }

    public void finishWork() {
        lock.lock();
        try {
            isCompleted = true;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}*/