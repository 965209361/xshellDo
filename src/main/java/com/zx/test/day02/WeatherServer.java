package com.zx.test.day02;

import com.zx.test.day01.SockServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * created by zengqintao on 2018-07-02 10:39 .
 **/
public class WeatherServer {
    public static void main(String[] args) throws IOException {
        ServerSocket sockServer = new ServerSocket(9999);
        while (true) {
            final Socket socket = sockServer.accept();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                        String inputContent = dataInputStream.readUTF();
                        System.out.println("接收内容：" + inputContent);
                        String result = "天气寒冷";
                        System.out.println("返回消息" + result);
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        dataOutputStream.writeUTF(result);
                        dataInputStream.close();
                        dataOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(runnable).start();
        }
    }
}
