package com.example.day11_d13zhang_homework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 苏克阳 on 2019/3/19.
 */

public class LockScreenBroadcastReceiver extends BroadcastReceiver {

    public   LockScreenListener lockScreenListener;

    /*
        * 自定义手机锁屏监听类继承BroadcASTReceiver
        * */
    @Override
    public void onReceive(Context context, Intent intent) {

        String action=intent.getAction();

        if(action.equals(Intent.ACTION_SCREEN_ON)){

            lockScreenListener.onScreenOn();

        }else if(action.equals(Intent.ACTION_SCREEN_OFF)){

            lockScreenListener.onScreenOff();

        }else if(action.equals(Intent.ACTION_USER_PRESENT)){

            lockScreenListener.onUserPresent();
        }



    }
//
//    做一个监听接口。
//
//    监听广播，监听锁屏 解锁  点亮屏幕的Action

    public interface LockScreenListener {

        String Tag="LockScreenListener";

        public void onScreenOn();

        public void onScreenOff();

        public void onUserPresent();
    }

    public LockScreenBroadcastReceiver(LockScreenListener lockScreenListener) {

        this.lockScreenListener = lockScreenListener;
    }


}
