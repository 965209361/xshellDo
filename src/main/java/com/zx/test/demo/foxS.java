package com.zx.test.demo;

/**
 * created by zengqintao on 2018-06-22 15:08 .
 **/
public class foxS {
    public static void main(String[] args) {
        String name = "data";
        while (name.equals("data")) {
            System.out.println("hello");
            for (int i = 0; i < 10; i++) {
                if (i == 7)
                    name = "dawugui";
                System.out.println("循环遍历");
            }
            System.out.println("last--" + name);
        }
    }

}
