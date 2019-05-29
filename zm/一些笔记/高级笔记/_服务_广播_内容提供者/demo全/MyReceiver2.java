package com.jiyun.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"MyReceiver2",Toast.LENGTH_SHORT).show();

        abortBroadcast();//拦截
    }
}
