package com.zx.test.Log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * created by zengqintao on 2018-06-15 15:30 .
 **/
public class CosoleLog4j {
    private static final Logger log = LoggerFactory.getLogger(CosoleLog4j.class);

    public static void main(String[] args) {
        log.debug("Here is some DEBUG");
        log.info("Here {} is some Info");
        log.warn("Here is some WARN");
        log.error("Here is some ERROR");
        log.trace("Here is some FATAL");
    }
}
