package com.zx.test.day01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * created by zengqintao on 2018-06-29 15:31 .
 **/
public class SockServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                final Socket socket = serverSocket.accept();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                            String cityName = dataInputStream.readUTF();
                            System.out.println("接收客户端发送的请求：" + cityName);
                            Thread.sleep(1000);
                            Scanner scanner = new Scanner(System.in);
                            String result = scanner.next();
                            System.out.println("答：" + result);
                            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                            dataOutputStream.writeUTF(result);
                            dataInputStream.close();
                            dataOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                new Thread(runnable).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
