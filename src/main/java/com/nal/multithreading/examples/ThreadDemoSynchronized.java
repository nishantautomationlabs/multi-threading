package com.nal.multithreading.examples;

import com.nal.multithreading.classes.Counter;

/**
 * Created by nishant on 15/12/19.
 */
public class ThreadDemoSynchronized {

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count is " + counter.getCount());

        System.out.println("Completed");


    }
}
