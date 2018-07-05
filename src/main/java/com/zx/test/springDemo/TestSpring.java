package com.zx.test.springDemo;

import com.zx.pojo.Source;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * created by zengqintao on 2018-07-04 14:29 .
 **/
public class TestSpring {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContent.xml"
        });
        Source source = (Source) context.getBean("source");
        System.out.println(source.getFruit());
        System.out.println(source.getSugar());
        System.out.println(source.getSize());
    }
}
