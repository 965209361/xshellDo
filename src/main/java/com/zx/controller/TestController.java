package com.zx.controller;

import com.zx.test.day03.WeatherClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * created by zengqintao on 2018-07-04 16:25 .
 **/
@Controller
public class TestController {
    @RequestMapping("/socketPage")
    String sockePage() {
        return "views/SocketPage";
    }

    @RequestMapping("/message")
    @ResponseBody
    public String getMessage(@RequestParam("text") String text) {
        WeatherClient weatherClient = new WeatherClient();
        String mes = weatherClient.getclient(text);
        if (mes != null) {
            return mes;
        } else {
            return null;
        }
    }

}
