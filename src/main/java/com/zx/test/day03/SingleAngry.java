package com.zx.test.day03;

/**
 * created by zengqintao on 2018-07-04 15:50 .
 **/
public class SingleAngry {
    private static SingleAngry singleAngry = new SingleAngry();

    private SingleAngry() {

    }

    public SingleAngry getInstance() {
        return singleAngry;
    }
}
