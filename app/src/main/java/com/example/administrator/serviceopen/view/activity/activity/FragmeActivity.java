package com.example.administrator.serviceopen.view.activity.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.serviceopen.R;

/**
 * Created by ${yx} on 2018/11/7.
 */
public class FragmeActivity extends AppCompatActivity {

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState,  @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_fragment);
    }


}
