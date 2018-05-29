package com.zx.domain;

/**
 * created by zengqintao on 2018-05-15 11:37 .
 **/
public class ServiceDomain {
    private Integer sid;

    private Integer mid;
    //服务名
    private String service_name;
    //进程名
    private String process_name;
    //路径
    private String path;

    private String depict;
    //启动命令
    private String cmad_start;
    //重启命令
    private String cmad_restart;
    //停止命令
    private String cmad_stop;

    private String cmad_status;

    private Integer order_num;

    public Integer getSid() {
        return sid;
    }

    public Integer getMid() {
        return mid;
    }

    public String getService_name() {
        return service_name;
    }

    public String getProcess_name() {
        return process_name;
    }

    public String getPath() {
        return path;
    }

    public String getDepict() {
        return depict;
    }

    public String getCmad_start() {
        return cmad_start;
    }

    public String getCmad_restart() {
        return cmad_restart;
    }

    public String getCmad_stop() {
        return cmad_stop;
    }

    public String getCmad_status() {
        return cmad_status;
    }

    public Integer getOrder_num() {
        return order_num;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public void setCmad_start(String cmad_start) {
        this.cmad_start = cmad_start;
    }

    public void setCmad_restart(String cmad_restart) {
        this.cmad_restart = cmad_restart;
    }

    public void setCmad_stop(String cmad_stop) {
        this.cmad_stop = cmad_stop;
    }

    public void setCmad_status(String cmad_status) {
        this.cmad_status = cmad_status;
    }

    public void setOrder_num(Integer order_num) {
        this.order_num = order_num;
    }
}
