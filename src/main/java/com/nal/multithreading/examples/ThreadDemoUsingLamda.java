package com.nal.multithreading.examples;

import com.nal.multithreading.Util.Utils;

/**
 * Created by nishant on 15/12/19.
 */
public class ThreadDemoUsingLamda {

    public static void main(String[] args) throws InterruptedException {

        Thread firstNameThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.print("Nishant ");
                System.out.print(Thread.currentThread().getId());
                Utils.sleep(500);
            }
        }, "FIRST THREAD NAME");

        Runnable lastName = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.print(" Singh");
                System.out.println(Thread.currentThread().getId());
                Utils.sleep(500);
            }
        };

        Thread lastNameThread = new Thread(lastName);

        firstNameThread.start();
        Utils.sleep(50);
        lastNameThread.start();

        firstNameThread.join();
        lastNameThread.join();

        System.out.println("First Thread Name is " + firstNameThread.getName());

        System.out.println("Completed");


    }
}
