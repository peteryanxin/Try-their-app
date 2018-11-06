package com.example.administrator.serviceopen;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private ServiceConnection conn;

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, BgService.class);
    }

    public void bind(View v) {

        // bind service
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                BgService.MyBinder binder = (BgService.MyBinder) service;
                binder.getSimple();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

                Log.d(TAG,"unbind 之后执行了");

            }
        };

        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    public void unbind(View v) {
        if (conn != null) {
            unbindService(conn);
        }
    }

    public void startS(View view) {
        startService(intent);
    }

    public void stopS(View view) {
        stopService(intent);
    }
}
