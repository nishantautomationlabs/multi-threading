package com.nal.multithreading.examples;

import com.nal.multithreading.Util.Utils;
import com.nal.multithreading.classes.FirstNameUsingThread;
import com.nal.multithreading.classes.LastNameUsingRunnable;

/**
 * Created by nishant on 15/12/19.
 */
public class ThreadDemoBasic {

    public static void main(String[] args) throws InterruptedException {
        Thread firstName = new FirstNameUsingThread(); //extending Thread class
        Runnable lastName =  new LastNameUsingRunnable();  //implementing Runnable interface
        Thread lastNameThread = new Thread(lastName);

        firstName.setPriority(1);  //lowest
        lastNameThread.setPriority(10); //highest

        firstName.start();
        Utils.sleep(50);
        lastNameThread.start();
        System.out.println("First Name Thread is Alive :" + firstName.isAlive());
        System.out.println("Last Name Thread is Alive :" + lastNameThread.isAlive());

        System.out.println("First Name Thread State :" + firstName.getState());
        System.out.println("Last Name Thread State :" + lastNameThread.getState());

        firstName.join();
        lastNameThread.join();

        System.out.println("First Name Thread is Alive :" + firstName.isAlive());
        System.out.println("Last Name Thread is Alive :" + lastNameThread.isAlive());

        System.out.println("First Name Thread State :" + firstName.getState());
        System.out.println("Last Name Thread State :" + lastNameThread.getState());

        System.out.println("First Name Thread ID :" + firstName.getId());
        System.out.println("Last Name Thread ID :" + lastNameThread.getId());

        System.out.println("First Name Thread Name :" + firstName.getName());
        System.out.println("Last Name Thread Name :" + lastNameThread.getName());

        System.out.println("First Name Thread Priority :" + firstName.getPriority());
        System.out.println("Last Name Thread Priority :" + lastNameThread.getPriority());
        System.out.println("Completed");


    }
}
