package com.zx.test.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * created by zengqintao on 2018-06-28 10:54 .
 **/
public class jdbc {
    public static void main(String[] args) {
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("url","user","password");
            statement=conn.prepareStatement("sql");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
