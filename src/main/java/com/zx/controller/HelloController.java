package com.zx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    @RequestMapping("/")
    String add() {
        return "views/add";
    }

    /*@RequestMapping(value = "/views/add")
    public String index(){
        return "views/add";
    }*/
    @RequestMapping("/demo")
    String demo() {
        return "views/demo";
    }



}
