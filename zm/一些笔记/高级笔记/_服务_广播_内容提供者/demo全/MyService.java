package com.jiyun.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyService", "get: "+ "onCreate" );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(){
            @Override
            public void run() {
                super.run();
            }
        }.start();
        Log.e("MyService", "get: "+ "onStartCommand" );

        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MyService", "get: "+ "onBind" );
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("MyService", "get: "+ "onUnbind" );
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("MyService", "get: "+ "onDestroy" );
        super.onDestroy();
    }

    //-------------------------------
    class MyBinder extends Binder {

        public void get(int max){
            Log.e("MyService", "get: "+max );
        }

        public String show(){
            return  "abc";
        }
    }
}
