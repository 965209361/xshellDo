package com.zx.test.day02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * created by zengqintao on 2018-07-02 11:22 .
 **/
public class WeatherClient {
    public static void main(String[] args) throws IOException {
        while (true) {
            Socket socket = new Socket("192.168.155.235", 9999);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String str = "今天天气如何";
            dataOutputStream.writeUTF(str);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String restr = dataInputStream.readUTF();
            System.out.println("再次提问"+restr);
            dataInputStream.readUTF();
            dataInputStream.close();
            dataOutputStream.close();
        }
    }
}
