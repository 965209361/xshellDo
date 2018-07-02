package com.zx.test.day01;

/**
 * created by zengqintao on 2018-06-29 9:40 .
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
