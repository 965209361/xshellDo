package com.zx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.zx.controller")
public class SpringBootStart {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootStart.class, args);
    }
}
