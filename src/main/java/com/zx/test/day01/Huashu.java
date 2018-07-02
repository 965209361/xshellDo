package com.zx.test.day01;

/**
 * created by zengqintao on 2018-06-29 9:35 .
 **/
public class Huashu {
    public static void main(String[] args) {
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 100; i < 1000; i++) {
            a = i / 100;
            b = i / 10 % 10;
            c = i % 10;
            if (i == a * a * a + b * b * b + c * c * c) {
                System.out.println(i + "--为水仙花数");
            }
        }
    }
}
