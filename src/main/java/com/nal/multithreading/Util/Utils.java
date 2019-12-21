package com.nal.multithreading.Util;

/**
 * Created by nishant on 15/12/19.
 */
public class Utils {

    public static void sleep(int milliseconds)
    {
        try {
            Thread.sleep(milliseconds);
        }
        catch (Exception ex){}
    }
}
