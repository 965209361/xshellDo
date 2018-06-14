package com.zx.test.demo;


import com.zx.test.kit.ProKit;

import java.text.MessageFormat;
import java.util.Properties;

/**
 * created by zengqintao on 2018-06-06 16:49 .
 **/
public class ProDemo {
    static Properties prop = ProKit.prop("jdbc.properties");
    static final String PROPERTIES = "i like {0} and {1}";

    public static void main(String[] args) {
        String names = MessageFormat.format(PROPERTIES, prop.getProperty("name"), prop.getProperty("name1"));
        System.out.println(names);
    }
}
