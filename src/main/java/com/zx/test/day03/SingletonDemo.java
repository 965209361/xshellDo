package com.zx.test.day03;

/**
 * created by zengqintao on 2018-07-04 15:47 .
 **/
public class SingletonDemo {
    public static void main(String[] args) {
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 100; i < 1000; i++) {
            a = i / 100;
            b = i / 10 % 10;
            c = i % 10;
            if (i == a * a * a + b * b * b + c * c * c) {
                System.out.println(i+"为水仙花数");
            }
        }
    }
}
