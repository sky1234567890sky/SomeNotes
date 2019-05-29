package com.example.day11_d13zhang_homework;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    /*
    * 苏克阳 第十三章 第二题 3.19
    * */

    private LockScreenBroadcastReceiver lockScreenBroadcastReceiver;
    private String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //接口的实现
        lockScreenBroadcastReceiver = new LockScreenBroadcastReceiver(new LockScreenBroadcastReceiver.
                LockScreenListener() {
            @Override
            public void onScreenOn() {
                Log.i(TAG, "用户进入home界面=======>ACTION_USER_PRESENT)");
            }

            @Override
            public void onScreenOff() {//屏幕打开

                Log.i(TAG, "屏幕关闭=======>Intent.ACTION_SCREEN_ON");

            }
            @Override
            public void onUserPresent() {//屏幕关闭
                Log.i(TAG, "屏幕打开=======>Intent.ACTION_SCREEN_OFF");
            }
        });
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(lockScreenBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(lockScreenBroadcastReceiver);
        super.onDestroy();
    }
}


/*
在做解锁监听程序时，一开始采用监听屏幕SCREEN_ON和SCREEN_OFF这两个action。
        但奇怪的是，这两个action只能通过代码动态的形式注册，才能被监听到，使用AndroidManifest.xml 完全监听不到。
        百度后发现这是PowerManager那边在发这个广播的时候做了限制，限制只能有register到代码中的receiver才能接收。
        后来就找各种能静态注册AndroidManifest.xml同时能反映用户解锁行为的广播.于是找到android.intent.action.USER_PRESENT.
        每个用户隔一段时间重新开始使用手机时，首先按电源键点亮屏幕，紧接着解锁。android.intent.action.USER_PRESENT就是解锁时发出的intent.
        于是,监听android.intent.action.USER_PRESENT就能识别用户进入home界面，进而启动想启动的相关服务,包括弹出对话框welcome用户\后台启动程序升级服务等等。
*/


