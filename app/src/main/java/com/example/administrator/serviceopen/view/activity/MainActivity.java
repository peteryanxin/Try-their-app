package com.example.administrator.serviceopen.view.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.serviceopen.model.interfaces.RotateListener;
import com.example.administrator.serviceopen.model.service.BgService;
import com.example.administrator.serviceopen.R;
import com.example.administrator.serviceopen.view.activity.activity.FragmeActivity;
import com.example.administrator.serviceopen.view.activity.custom_ui.CricleImage;

public class MainActivity extends AppCompatActivity {

    /**
     *   MVC 设计模式 : Model View Controller
     *   把视图层 和 模型层 / 控制层 分开 .
     *   分离视图层和业务层
     */
    private Intent intent;
    private ServiceConnection conn;

    private final String TAG = MainActivity.class.getSimpleName();
    private CricleImage circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, BgService.class);
        Log.d(TAG,"onCreate");

        circle = findViewById(R.id.cr);


/***
 *  https://blog.csdn.net/lly347705530/article/details/78671696 明天关注
 */

        circle.setRotateListener(new RotateListener() {

            // 开启动画 .
            @Override
            public void startAnima() {

            }

            // 暂定 动画
            @Override
            public void pauseAnima() {

            }

            // 复原 动画
            @Override
            public void resumeAnima() {

            }

            // 停止 动画
            @Override
            public void stopAnima() {

            }
        });
    }

    public void startMusic(View view){

        circle.playMusic();

    }

    public void stopMusic(View view){

        circle.stopMusic();
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

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestory");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    public void goFragment(View view){

        Intent intent  = new Intent(this,FragmeActivity.class);

        startActivity(intent);



    }
}
