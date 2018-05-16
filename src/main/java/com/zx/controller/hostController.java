package com.zx.controller;

import com.zx.domain.Host;
import com.zx.domain.ServiceDomain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by zengqintao on 2018-05-15 10:50 .
 **/
@Controller
@RequestMapping("/xshell")
public class hostController {

    @RequestMapping("/host")
    public String host(Host host, Model model){
        String name = host.getAccount();
        if(!StringUtils.isEmpty(name)&&!StringUtils.isEmpty(host.getHost())&&!StringUtils.isEmpty(host.getPassword())){
            model.addAttribute("InfoSucc","端口数据存入成功!");
        }else
            model.addAttribute("InfoError","端口数据为空,请重试");
        return "views/add";
    }

    @RequestMapping("/service")
    public String service(ServiceDomain serviceDomain,Model model){
        String name = serviceDomain.getProcess_name();
        if(!StringUtils.isEmpty(name)){
            model.addAttribute("InfoSucc","服务数据存入成功!");
        }else
            model.addAttribute("InfoError","服务数据为空,请重试");
        return "views/add";
    }
}
