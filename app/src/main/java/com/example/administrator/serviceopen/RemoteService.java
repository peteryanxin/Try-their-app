package com.example.administrator.serviceopen;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class RemoteService extends Service {

    private final String TAG = RemoteService.class.getSimpleName();
    private MyBinder binder;

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public RemoteService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    class MyBinder extends Binder{

        public void getShowSimpleName(){
            Toast.makeText(RemoteService.this, TAG, Toast.LENGTH_SHORT).show();
        }
    }
}
