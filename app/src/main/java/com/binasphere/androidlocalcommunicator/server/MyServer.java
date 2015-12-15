package com.binasphere.androidlocalcommunicator.server;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static com.binasphere.androidlocalcommunicator.MainActivity.MyHandler;

/**
 * Created by Kerstin on 2015/12/15.
 */
public class MyServer extends ServerSocket {
    private boolean isRunning = false;
    private Thread tServer;
    private MyHandler handler;

    public MyServer() throws IOException {
        super();
        // TODO Auto-generated constructor stub
    }

    public MyServer(int port, int backlog, InetAddress localAddress) throws IOException {
        super(port, backlog, localAddress);
        // TODO Auto-generated constructor stub
    }

    public MyServer(int port, int backlog) throws IOException {
        super(port, backlog);
        // TODO Auto-generated constructor stub
    }

    public MyServer(int port) throws IOException {
        super(port);
        // TODO Auto-generated constructor stub
    }

    public MyServer(int port, MyHandler handler) throws IOException {
        super(port);
        this.handler = handler;
    }

    public void stop() {
        isRunning = false;
        try {
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        isRunning = true;
        Log.d("Server", "start");
        tServer = new Thread() {
            @Override
            public void run() {
                while (isRunning) {
                    Socket socket;
                    try {
                        socket = accept();
                        Log.d("Server", "accept");
                        InputStream is = socket.getInputStream();
                        byte[] buf = new byte[1024];
                        int len = 0;
                        if ((len = is.read(buf)) != -1) {
                            String temp = new String(buf, 0, len);
                            Log.d("Client to Server", temp);
                            Message message=Message.obtain();
                            message.obj=temp;
                            handler.sendMessage(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        tServer.start();

    }


}
