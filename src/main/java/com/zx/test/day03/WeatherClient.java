package com.zx.test.day03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * created by zengqintao on 2018-07-04 15:58 .
 **/
public class WeatherClient {
    private static Logger log = LoggerFactory.getLogger(WeatherClient.class);

    public String getclient(String message) {
        try {
            while (true) {
                Socket socket = new Socket("192.168.155.235", 9999);
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeUTF(message);
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                String returnmessage = inputStream.readUTF();
                log.info("接收消息为：{}", returnmessage);
                outputStream.close();
                inputStream.close();
                return returnmessage;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
