package com.zx.test.kit;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * created by zengqintao on 2018-06-06 15:18 .
 **/
public class ProKit {
    public static Properties prop(String filename) {
        InputStream io = ProKit.class.getClassLoader().getResourceAsStream(filename);
        Properties prop = new Properties();
        try {
            prop.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
