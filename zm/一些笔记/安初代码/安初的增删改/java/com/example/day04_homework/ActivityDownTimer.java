package com.example.day04_homework;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityDownTimer extends AppCompatActivity {

    /**
     * 还剩3秒
     */
    private TextView mTime;
    int in = 3;

    @SuppressLint("HandlerLeak")
    Handler hand= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            in--;
            if(in>0){
                mTime.setText("还剩"+in+"秒");
            }else{
                timer.cancel();
                startActivity(new Intent(ActivityDownTimer.this,MainActivity.class));
            }
        }
    };
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_timer);
        initView();
    }
    private void initView() {
        mTime = (TextView) findViewById(R.id.time);
        mTime.setText("还剩"+in+"秒");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                hand.sendEmptyMessage(1);
            }
        };
        timer = new Timer();
        timer.schedule(timerTask,1000,1000);
    }
}
