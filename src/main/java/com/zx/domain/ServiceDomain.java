package com.zx.domain;

/**
 * created by zengqintao on 2018-05-15 11:37 .
 **/
public class ServiceDomain {
    private String service_name;
    private String process_name;
    private String path;
    private String cmad_start;

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setCmad_start(String cmad_start) {
        this.cmad_start = cmad_start;
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

    public String getCmad_start() {
        return cmad_start;
    }
}
