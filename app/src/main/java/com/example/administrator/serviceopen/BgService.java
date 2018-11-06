package com.example.administrator.serviceopen;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BgService extends Service {


    private final String TAG = BgService.class.getSimpleName();
    private MyBinder binder;

    @Override
    public IBinder onBind(Intent intent) {

        Log.d(TAG,"onBind");
        return binder;
    }

    public BgService() {
        super();
        Log.d(TAG,"BgService () ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate ");

        binder = new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand ");


        // 服务中在开服务



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind ");
        return super.onUnbind(intent);
    }


    class MyBinder extends Binder{

        public void getSimple(){
            String name = TAG;
            Toast.makeText(BgService.this, name, Toast.LENGTH_SHORT).show();
        }
    }
}
