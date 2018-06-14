package com.zx.service;

import com.alibaba.fastjson.JSONObject;
import com.zx.domain.Host;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * created by zengqintao on 2018-05-28 17:15 .
 **/
public class HostService {
    Properties proKit =prop("jdbc.properties");

    //mysql
    public Connection conn() {
        Connection conn = null;
        try {
            Class.forName(proKit.getProperty("com.driver"));
            conn = DriverManager.getConnection(proKit.getProperty("com.jdbc"), proKit.getProperty("com.user"), proKit.getProperty("com.password"));
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
        try {
            prep.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /* public List<ServiceDomain> getService(String id) {
         PreparedStatement prep = null;
         ResultSet rs = null;
         String sql = String.format("SELECT sid,depict,service_name,process_name,path,cmad_start,cmad_restart,cmad_stop,cmad_status FROM `service` WHERE mid in(%s);", id);
         List<ServiceDomain> list = new ArrayList<ServiceDomain>();
         try {
             prep = conn().prepareStatement(sql);
             rs = prep.executeQuery();
             while (rs.next()) {
                 ServiceDomain serviceDomain = new ServiceDomain();
                 serviceDomain.setSid(rs.getInt("sid"));
                 serviceDomain.setDepict(rs.getString("depict"));
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
         try {
             prep.close();
             rs.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return list;
     }*/
    //获取服务相关信息
    public List<JSONObject> getJsonService(int[] ids) {
        PreparedStatement prep = null;
        ResultSet rs = null;
        List<JSONObject> list = new ArrayList<JSONObject>();
        for (int i = 0; i < ids.length; i++) {
            String sql = String.format("SELECT c.sid,m.`host`,m.area,c.depict,c.service_name,c.process_name,c.path,c.cmad_start,c.cmad_restart,c.cmad_stop,c.cmad_status FROM `service` c INNER JOIN server_machine m ON c.mid =%s and m.mid =%s;", ids[i], ids[i]);
            try {
                prep = conn().prepareStatement(sql);
                rs = prep.executeQuery();
                while (rs.next()) {
                    JSONObject json = new JSONObject();
                    json.put("sid", rs.getInt("sid"));
                    json.put("host", rs.getString("host"));
                    json.put("area", rs.getString("area"));
                    json.put("depict", rs.getString("depict"));
                    json.put("service_name", rs.getString("service_name"));
                    json.put("process_name", rs.getString("process_name"));
                    json.put("path", rs.getString("path"));
                    json.put("cmad_start", rs.getString("cmad_start"));
                    json.put("cmad_restart", rs.getString("cmad_restart"));
                    json.put("cmad_stop", rs.getString("cmad_stop"));
                    json.put("cmad_status", rs.getString("cmad_status"));
                    list.add(json);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            prep.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据所得到的id值来获取相关的信息
     */
    public List<JSONObject> gettypeValueId(int ids) {
        PreparedStatement prep = null;
        ResultSet rs = null;
        List<JSONObject> list = new ArrayList<JSONObject>();
        String sql = String.format("SELECT c.sid,m.instructions,c.depict,c.service_name,c.process_name,c.path,c.cmad_start,c.cmad_restart,c.cmad_stop,c.cmad_status FROM `service` c INNER JOIN order_desc m ON c.order_num = %d and m.order_num=%d;", ids, ids);
        try {
            prep = conn().prepareStatement(sql);
            rs = prep.executeQuery();
            while (rs.next()) {
                JSONObject json = new JSONObject();
                json.put("sid", rs.getInt("sid"));
                json.put("instructions", rs.getString("instructions"));
                json.put("depict", rs.getString("depict"));
                json.put("service_name", rs.getString("service_name"));
                json.put("process_name", rs.getString("process_name"));
                json.put("path", rs.getString("path"));
                json.put("cmad_start", rs.getString("cmad_start"));
                json.put("cmad_restart", rs.getString("cmad_restart"));
                json.put("cmad_stop", rs.getString("cmad_stop"));
                json.put("cmad_status", rs.getString("cmad_status"));
                list.add(json);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            prep.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 下拉框从数据库索引
     *
     * @return
     */
    public List<JSONObject> getSelectValue() {
        PreparedStatement prep = null;
        ResultSet rs = null;
        List<JSONObject> list = new ArrayList<JSONObject>();
        String sql = "SELECT order_num,instructions FROM `order_desc`;";
        try {
            prep = conn().prepareStatement(sql);
            rs = prep.executeQuery();
            while (rs.next()) {
                JSONObject json = new JSONObject();
                json.put("order_num", rs.getString("order_num"));
                json.put("instructions", rs.getString("instructions"));
                list.add(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            prep.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public Properties prop(String fileName) {
        InputStream is = HostService.class.getClassLoader().getResourceAsStream(fileName);
        Properties prop = new Properties();
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
