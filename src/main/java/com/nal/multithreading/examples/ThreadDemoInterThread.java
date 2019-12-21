package com.nal.multithreading.examples;

import com.nal.multithreading.classes.Consumer;
import com.nal.multithreading.classes.Producer;
import com.nal.multithreading.classes.Warehouse;

/**
 * Created by nishant on 15/12/19.
 */
public class ThreadDemoInterThread {

    public static void main(String[] args) throws InterruptedException {

        Warehouse warehouse = new Warehouse();

        Producer producer = new Producer(warehouse);
        Consumer consumer = new Consumer(warehouse);

        Thread producerThread = new Thread(producer, "Producer");
        Thread consumerThread = new Thread(consumer, "Consumer");
        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        System.out.println("Completed");


    }
}
