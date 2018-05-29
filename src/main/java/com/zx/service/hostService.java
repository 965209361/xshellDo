package com.zx.service;

import com.zx.domain.Host;
import com.zx.domain.ServiceDomain;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * created by zengqintao on 2018-05-28 17:15 .
 **/
public class hostService {


    //mysql
    public Connection conn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://45.63.53.232:3306/program?useUnicode=true&characterEncoding=utf-8", "admin", "zxsoft0#");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public List<Host> getHost() {
        PreparedStatement prep = null;
        ResultSet rs = null;
        String sql = "SELECT `host`,area,mid FROM `server_machine`;";
        List<Host> list = new ArrayList<Host>();
        try {
            prep = conn().prepareStatement(sql);
            rs = prep.executeQuery();
            while (rs.next()) {
                Host host = new Host();
                host.setHost(rs.getString("host"));
                host.setArea(rs.getString("area"));
                host.setMid(rs.getInt("mid"));
                list.add(host);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ServiceDomain> getService(String id) {
        PreparedStatement prep = null;
        ResultSet rs = null;
        String sql = String.format("SELECT sid,service_name,process_name,path,cmad_start,cmad_restart,cmad_stop,cmad_status FROM `service` WHERE mid in(%s);",id);
        List<ServiceDomain> list = new ArrayList<ServiceDomain>();
        try {
            prep = conn().prepareStatement(sql);
            rs = prep.executeQuery();
            while (rs.next()) {
                ServiceDomain serviceDomain = new ServiceDomain();
                serviceDomain.setSid(rs.getInt("sid"));
                serviceDomain.setService_name(rs.getString("service_name"));
                serviceDomain.setProcess_name(rs.getString("process_name"));
                serviceDomain.setPath(rs.getString("path"));
                serviceDomain.setCmad_start(rs.getString("cmad_start"));
                serviceDomain.setCmad_restart(rs.getString("cmad_restart"));
                serviceDomain.setCmad_stop(rs.getString("cmad_stop"));
                serviceDomain.setCmad_status(rs.getString("cmad_status"));
                list.add(serviceDomain);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
