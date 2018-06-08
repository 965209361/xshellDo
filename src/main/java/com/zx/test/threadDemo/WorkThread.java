package com.zx.test.threadDemo;

import com.zx.test.demo.ObjectSiJi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * created by zengqintao on 2018-06-07 11:06 .
 **/
public class WorkThread implements Runnable {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = simpleDateFormat.format(new Date());
    private String workCommon;

    public WorkThread(String workCommon) {
        this.workCommon = workCommon;
    }

    int random = new Random().nextInt(4);

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--开始 time" + date + "----------" + ObjectSiJi.getDesc(random));
        proper();
        System.out.println(Thread.currentThread().getName() + "--结束 time" + date + "----------" + ObjectSiJi.getDesc(random));
    }

    public void proper() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "WorkThread{" +
                "workCommon='" + workCommon + '\'' +
                '}';
    }
}
