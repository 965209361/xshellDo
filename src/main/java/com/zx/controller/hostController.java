package com.zx.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
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

import javax.annotation.Resource;
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


    /**
     * 查询列表展示页面
     * @param model
     * @return
     */
    @RequestMapping("/fuwu")
    String service(Model model) {
        hostService hostService = new hostService();
        List<Host> list = hostService.getHost();
        model.addAttribute("rows", list);
        model.addAttribute("InfoSucc", "列表展示");

        return "views/service";
    }

    /**
     * 展开详情时携带单个tid进行查询
     * @param tid
     * @return
     */
    @RequestMapping("/content/{tid}")
    @ResponseBody
    List<JSONObject> content(@PathVariable("tid") int tid) {
        int[] arr = {tid};
        hostService hostService = new hostService();
        List<JSONObject> listService = hostService.getJsonService(arr);
        return listService;
    }


    /**
     *根据类别中的type值进行查询
     * @param typeValueId
     * @return
     */
    @RequestMapping("/typeValue/{typeValueId}")
    @ResponseBody
    List<JSONObject> typeValueId(@PathVariable("typeValueId") int typeValueId) {
        hostService hostService = new hostService();
        List<JSONObject> listService = hostService.gettypeValueId(typeValueId);
        return listService;
    }

    /**
     * 根据多个id进行查询
     * @param request
     * @param chk_value
     * @return
     */
    @RequestMapping("/ServiceDetailList")
    @ResponseBody
    List<JSONObject> list(HttpServletRequest request, int[] chk_value) {
        hostService hostService = new hostService();
        List<JSONObject> listService = hostService.getJsonService(chk_value);
        return listService;
    }


    /**
     * 获取需要查询的接口
     * @param request
     * @param chk_value
     * @return
     */
    @RequestMapping("/getSidForXshell")
    @ResponseBody
    String info(HttpServletRequest request, String[] chk_value) {
        hostService hostService = new hostService();
        String str = "";
        for (int i = 0; i < chk_value.length; i++) {
            str = str + "," + chk_value[i];
        }
        str = str.substring(1);
        System.out.println(str);
        return "";
    }
    /**
     * 获取下拉框的接口*/
    @RequestMapping("/getSelectValue")
    @ResponseBody
    List<JSONObject> getSelectValue() {
        hostService hostService = new hostService();
        List<JSONObject> list = hostService.getSelectValue();
        return list;
    }
}
