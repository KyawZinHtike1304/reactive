package com.example.webfluxdemo.util;

public class SleepUntil {
    public static void sleepSeconds(int seconds){
        try{
            Thread.sleep(seconds *1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
