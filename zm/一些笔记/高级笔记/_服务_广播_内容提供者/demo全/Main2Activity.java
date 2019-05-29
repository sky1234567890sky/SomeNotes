package com.jiyun.demo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * 服务
 */
public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                //start();
               bind();
                break;
            case R.id.btn2:
                //stop();
                unbind();
                break;
        }
    }

    private MyService.MyBinder myBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;

            myBinder.get(123456);
            String show = myBinder.show();
            Log.e("abc", "onServiceConnected: "+show );
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void unbind() {
        unbindService(connection);
    }

    private void bind() {
        intent = new Intent(this,MyService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    private void start() {
        intent = new Intent(this,MyService.class);
        startService(intent);
    }

    private void stop() {
        stopService(intent);
    }
}
