package com.zx.controller;

import com.zx.domain.Host;
import com.zx.domain.ServiceDomain;
import com.zx.service.hostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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
        } else if (StringUtils.isEmpty(host.getPasswd())) {
            model.addAttribute("InfoError", "密码值为空,请重试");
        } else {
            model.addAttribute("InfoSucc", "端口数据存入成功!");
        }
        return "views/add";
    }

    @RequestMapping("/service")
    public String service(ServiceDomain serviceDomain, Model model) {

        if (StringUtils.isEmpty(serviceDomain.getService_name())) {
            model.addAttribute("InfoError", "服务器名为空,请重试");
        } else if (StringUtils.isEmpty(serviceDomain.getProcess_name())) {
            model.addAttribute("InfoError", "process..为空,请重试");
        } else if (StringUtils.isEmpty(serviceDomain.getPath())) {
            model.addAttribute("InfoError", "路径为空,请重试");
        } else if (StringUtils.isEmpty(serviceDomain.getCmad_start())) {
            model.addAttribute("InfoError", "Cmad值为空,请重试");
        } else {
            model.addAttribute("InfoSucc", "service数据存入成功!");
        }
        return "views/add";
    }

    @RequestMapping("/info")
    public String service(@RequestParam("host") String host) {
        System.out.println(host);
        return "views/page";
    }

    @RequestMapping("/fuwu")
    String service(Model model) {
        hostService hostService = new hostService();
        List<Host> list = hostService.getHost();
        model.addAttribute("rows", list);
        model.addAttribute("InfoSucc", "列表展示");

        return "views/service";
    }

    @RequestMapping("/content/{tid}")
    @ResponseBody
    List<ServiceDomain> content(@PathVariable("tid") int tid) {
        hostService hostService = new hostService();
        List<ServiceDomain> listService = hostService.getService(String.valueOf(tid));
        return listService;
    }

    @RequestMapping("/list")
    @ResponseBody
    List<ServiceDomain> list(HttpServletRequest request, String[] chk_value) {
        hostService hostService = new hostService();
        String str = "";
        for (int i = 0; i < chk_value.length; i++) {
                str = str +","+ chk_value[i];
        }
        str = str.substring(1);
        System.out.println(str);
        List<ServiceDomain> listService = hostService.getService(str);
        return listService;
    }
}
