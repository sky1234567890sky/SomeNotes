package com.jy.day13broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2019/3/19 0019.
 */

public class MyBroadcaseReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG","开机自动...............");
        //判断如果是开机的广播来了，则启动本app的Mainactivity  --自动启动本app
        if(intent.getAction() == Intent.ACTION_BOOT_COMPLETED){//当前的广播是  启动完成广播
            Intent it = new Intent(context, MainActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//为我们的app的首页activity创建 存放的栈
            context.startActivity(it);
            //发给通知  系统启动完成


        }
    }
}
