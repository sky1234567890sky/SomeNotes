package com.jy.day13broadcastreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Administrator on 2019/3/19 0019.
 */

public class MyBroadCastRec3 extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String data3 = intent.getStringExtra("data3");
        Log.d("TAG","MyBroadCastRec3。。。。"+data3);

        //接收到广播，发送通知  创建通知
        Intent _it = new Intent(context, Main2Activity.class);
        PendingIntent activity = PendingIntent.getActivity(context, 100, _it, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context, "123")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("rec3的通知")
                .setContentText(data3)
                .setContentIntent(activity)
                .setAutoCancel(true)
                .build();

        //发送通知
        nm.notify(3,notification);



    }
}
