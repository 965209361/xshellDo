package com.zx.test.day01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * created by zengqintao on 2018-06-29 15:43 .
 **/
public class SockClient {
    public static void main(String[] args) {
        while (true) {
            try {
                Socket socket = new Socket("192.168.155.235", 9999);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                InetAddress inetAddress = InetAddress.getLocalHost();
                String ip = inetAddress.getHostAddress();
                Scanner scanner = new Scanner(System.in);
                String value = scanner.next();
                dataOutputStream.writeUTF(value);
                System.out.println("ip:--" + ip + "---问：" + value);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String result = dataInputStream.readUTF();
                System.out.println("----" + result);
                dataInputStream.close();
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
