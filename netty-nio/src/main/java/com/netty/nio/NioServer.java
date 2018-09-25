package com.netty.nio;

import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * User: haroy
 * Date: 2018-09-24
 * Time: 下午4:39
 */
public class NioServer {

    private static int DEFAULT_PORT = 12345;
    private static ServerHandle serverHandle;
    public static void start(){
        start(DEFAULT_PORT);
    }
    public static synchronized void start(int port){
        if(serverHandle!=null) {
            serverHandle.stop();
        }
        serverHandle = new ServerHandle(port);
        new Thread(serverHandle,"Server").start();
    }
    public static void main(String[] args){
        start();
    }

}
