package com.zx.test.day01;

/**
 * created by zengqintao on 2018-06-29 9:38 .
 * 饿汉式
 **/
public class SingletonAngry {
    private static SingletonAngry angry = new SingletonAngry();

    private SingletonAngry() {

    }

    public SingletonAngry getInstance() {
        return angry;
    }
}
