package com.jiyun.day07.test1;

import java.util.ArrayList;

/**
 * Created by $lzj on 2019/4/9.
 */
public class Observable {

    //存储容器
    private ArrayList<Observer> list = new ArrayList<>();

    //订阅
    public void subscribe(Observer observer){
        list.add(observer);
    }

    //通知触发
    public void notifyMsg(String msg){
        for (Observer o : list) {
            o.receiveMsg(msg);
        }
    }
}
