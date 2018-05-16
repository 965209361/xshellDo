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
public class hostController {

    @RequestMapping("/host")
    public String host(Host host, Model model) {
        String name = host.getAccount();
        if (StringUtils.isEmpty(name)) {
            model.addAttribute("InfoError", "账号值为空,请重试");
        } else if (StringUtils.isEmpty(host.getHost())) {
            model.addAttribute("InfoError", "端口号为空,请重试");
        } else if (StringUtils.isEmpty(host.getPassword())) {
            model.addAttribute("InfoError", "密码值为空,请重试");
        } else if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(host.getHost()) && !StringUtils.isEmpty(host.getPassword())) {
            model.addAttribute("InfoSucc", "端口数据存入成功!");
        }
        return "views/add";
    }

    @RequestMapping("/service")
    public String service(ServiceDomain serviceDomain, Model model) {
        String name = serviceDomain.getProcess_name();
        if (StringUtils.isEmpty(serviceDomain.getService_name())) {
            model.addAttribute("InfoError", "服务器名为空,请重试");
        } else if (StringUtils.isEmpty(name)) {
            model.addAttribute("InfoError", "process..为空,请重试");
        } else if (StringUtils.isEmpty(serviceDomain.getPath())) {
            model.addAttribute("InfoError", "路径为空,请重试");
        } else if (StringUtils.isEmpty(serviceDomain.getCmad_start())) {
            model.addAttribute("InfoError", "Cmad值为空,请重试");
        } else if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(serviceDomain.getPath()) && !StringUtils.isEmpty(serviceDomain.getCmad_start()) && !StringUtils.isEmpty(serviceDomain.getService_name())) {
            model.addAttribute("InfoSucc", "服务数据存入成功!");
        }
        return "views/add";
    }
}
