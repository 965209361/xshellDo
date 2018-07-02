package com.zx.test.day02;

/**
 * created by zengqintao on 2018-07-02 9:19 .
 * 饿汉式
 **/
public class Singleton {

    private Singleton singleton = new Singleton();

    private Singleton (){

    }

    public Singleton getSingleton() {
        return singleton;
    }
}
