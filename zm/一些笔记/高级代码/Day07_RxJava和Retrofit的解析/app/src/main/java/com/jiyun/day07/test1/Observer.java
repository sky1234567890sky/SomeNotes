package com.jiyun.day07.test1;

import android.util.Log;

/**
 * Created by $lzj on 2019/4/9.
 */
public class Observer {

    private String name;

    public Observer(String name){
        this.name = name;
    }

    //观察者处理事件
    public void receiveMsg(String msg){
        System.out.println(name + "  收到了：" +msg);
    }
}
