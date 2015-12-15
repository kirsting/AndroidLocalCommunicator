package com.binasphere.androidlocalcommunicator;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.binasphere.androidlocalcommunicator.client.MyClient;
import com.binasphere.androidlocalcommunicator.server.MyServer;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private MyServer mServer;
    private MyHandler handler=new MyHandler(this);
    public static class  MyHandler extends Handler{
        WeakReference<Activity> mActivity;
        MyHandler(Activity activity){
            mActivity=new WeakReference<Activity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            Activity activity=mActivity.get();
            Toast.makeText(activity,"on Message Received",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ButterKnife.bind(this);

    }

    @OnClick(R.id.server_start)
    public void onServerClick(View v) {
        try {
            if (mServer == null) {
                mServer = new MyServer(8888,handler);
                mServer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.client_sent)
    public void onClientClick(View v) {
        new Thread() {
            @Override
            public void run() {
                MyClient client = new MyClient();
                client.sent("test");
                Log.d("Client", "sent");
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
