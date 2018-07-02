package com.zx.test.day02;

/**
 * created by zengqintao on 2018-07-02 9:23 .
 **/
public class SingletonLazy {
    private SingletonLazy singleton = null;

    private SingletonLazy() {

    }

    public SingletonLazy getInstance() {
        if (singleton == null) {
            singleton = new SingletonLazy();
        }
        return singleton;
    }
}
