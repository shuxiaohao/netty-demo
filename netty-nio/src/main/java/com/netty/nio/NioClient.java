package com.netty.nio;

import java.util.Scanner;

/**
 * User: haroy
 * Date: 2018-09-24
 * Time: 下午4:39
 */
public class NioClient {
    private static String DEFAULT_HOST = "127.0.0.1";
    private static int DEFAULT_PORT = 12345;
    private static ClientHandle clientHandle;

    public static void start() {
        start(DEFAULT_HOST, DEFAULT_PORT);
    }

    public static synchronized void start(String ip, int port) {
        if (clientHandle != null) {
            clientHandle.stop();
        }
        clientHandle = new ClientHandle(ip, port);
        new Thread(clientHandle, "Server").start();
    }

    //向服务器发送消息
    public static boolean sendMsg(String msg) throws Exception {
        if (msg.equals("q")) {
            return false;
        }
        clientHandle.sendMsg(msg);
        return true;
    }

    public static void main(String[] args) throws Exception {
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        //运行客户端
        NioClient.start();
        while (NioClient.sendMsg(new Scanner(System.in).nextLine())) {
        }
    }
}
