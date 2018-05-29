package com.zx.domain;

/**
 * created by zengqintao on 2018-05-15 10:18 .
 **/
public class Host {
    private Integer mid;

    private String host;

    private String account;

    private String passwd;
    //区域
    private String area;
    //类型
    private Integer type;
    //状态
    private Integer status;

    public Integer getMid() {
        return mid;
    }

    public String getHost() {
        return host;
    }

    public String getAccount() {
        return account;
    }

    public String getArea() {
        return area;
    }

    public Integer getType() {
        return type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPasswd() {
        return passwd;
    }
}
