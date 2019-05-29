package com.jiyun.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by $lzj on 2019/4/22.
 */
public class MyReceiver0 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"abc",Toast.LENGTH_SHORT).show();

        Intent intent1 = new Intent(context,Main2Activity.class);
        //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
