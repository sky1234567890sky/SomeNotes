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

public class MyBroadcastRec2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //
        String data2 = intent.getStringExtra("data2");
        Log.d("TAG","MyBroadcastRec2。。。。"+data2);

        //接收到广播，发送通知  创建通知
        Intent _it = new Intent(context, Main2Activity.class);
        PendingIntent activity = PendingIntent.getActivity(context, 100, _it, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context, "123")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("周二")
                .setContentText("明天周三不远了")
                .setContentIntent(activity)
                .setAutoCancel(true)
                .build();

        //发送通知
        nm.notify(1,notification);

    }
}
