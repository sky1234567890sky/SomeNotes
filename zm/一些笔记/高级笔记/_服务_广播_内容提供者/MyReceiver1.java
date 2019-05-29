package com.jiyun.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String abc = intent.getStringExtra("abc");
        Toast.makeText(context,"MyReceiver1" +abc,Toast.LENGTH_SHORT).show();

        Intent intent1 = new Intent(context,Main2Activity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
