package com.zx.test.day03;

/**
 * created by zengqintao on 2018-07-04 15:52 .
 * 懒汉式
 **/
public class SingletonLazy {
    private static SingletonLazy lazy = null;

    private SingletonLazy() {

    }

    public SingletonLazy getInstance() {
        if (lazy == null) {
            lazy = new SingletonLazy();
        }
        return lazy;
    }
}
