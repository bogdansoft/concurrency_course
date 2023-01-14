package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * In this exercise we are going to implement a  MultiExecutor .
 * <p>
 * The client of this class will create a list of Runnable tasks and provide that list into MultiExecutor's constructor.
 * <p>
 * When the client runs the  executeAll(),  the MultiExecutor,  will execute all the given tasks.
 * <p>
 * To take full advantage of our multicore CPU, we would like the MultiExecutor to execute all the tasks in parallel,
 * by passing each task to a different thread.
 * Please implement the MultiExecutor below
 */
public class MultiExecutor {

    private List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        List<Thread> threadList = new ArrayList<>(tasks.size());

        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            threadList.add(thread);
        }

        for (Thread thr : threadList) {
            thr.start();
        }
    }

    public static void main(String[] args) {
        new MultiExecutor(List.of(Thread::new, Thread::new));
    }
}
