package com.zx.test.day03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * created by zengqintao on 2018-07-04 15:58 .
 **/
public class WeatherServer {
    private static Logger log = LoggerFactory.getLogger(WeatherServer.class);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            final Socket socket = serverSocket.accept();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                        String read = inputStream.readUTF();
                        log.info("发来内容为：{}", read);
                        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                        Scanner scanner = new Scanner(System.in);
                        log.warn("请输入返回消息：");
                        String write = scanner.nextLine();
                        log.info("发送消息：{}", write);
                        outputStream.writeUTF(write);
                        inputStream.close();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(runnable).start();
        }
    }
}
