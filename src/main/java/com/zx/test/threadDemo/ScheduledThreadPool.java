package com.zx.test.threadDemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * created by zengqintao on 2018-06-07 11:14 .
 **/
public class ScheduledThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        System.out.println("Current Time =" + date);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            WorkThread worker = new WorkThread("do heavy processing");
            scheduledExecutorService.scheduleWithFixedDelay(worker, 5, 3, TimeUnit.SECONDS);
        }
        Thread.sleep(30000);
        scheduledExecutorService.shutdown();
        while (!scheduledExecutorService.isTerminated()) {

        }
        System.out.println("Finished all threads");


    }
}
