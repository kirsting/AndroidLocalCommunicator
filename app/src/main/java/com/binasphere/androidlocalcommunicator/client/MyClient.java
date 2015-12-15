package com.binasphere.androidlocalcommunicator.client;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Kerstin on 2015/12/15.
 */
public class MyClient extends Socket {
    public void sent(String msg) {
        try {
            connect(new InetSocketAddress("127.0.0.1", 8888));
            int localPort = getLocalPort();
            msg=msg+" port ="+localPort;
            Log.d("Client", "port "+ localPort);
            OutputStream os = getOutputStream();
            os.write(msg.getBytes());
            shutdownOutput();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
