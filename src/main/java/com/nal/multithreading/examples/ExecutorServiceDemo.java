package com.nal.multithreading.examples;

import com.nal.multithreading.Util.Utils;
import com.nal.multithreading.classes.FirstNameUsingThread;
import com.nal.multithreading.classes.LastNameUsingRunnable;
import com.nal.multithreading.classes.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nishant on 15/12/19.
 */
public class ExecutorServiceDemo {

    public static void main(String[] args) {

//        singleThreadExecution();
        fixedThreadExecution();
     }

    private static void singleThreadExecution() {
        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.execute(new FirstNameUsingThread());
        executorService.execute(new Thread(new LastNameUsingRunnable()));

        executorService.shutdown();
    }

    public static void fixedThreadExecution() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Task(1));
        executorService.execute(new Task(2));
        executorService.execute(new Task(3));
        executorService.execute(new Task(4));
        executorService.execute(new Task(5));
        executorService.execute(new Task(6));
        executorService.execute(new Task(7));

        executorService.shutdown();
    }
}
