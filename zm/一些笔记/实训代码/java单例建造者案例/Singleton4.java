package com.jiyun.day03.single;

/**
 * Created by $lzj on 2019/5/30.
 *
 * 静态内部类
 */
public class Singleton4 {

    private static class SingletonHolder{
        public static final Singleton4 SINGLETON_4 = new Singleton4();
    }

    private Singleton4(){}

    public static Singleton4 getInstance(){
        return  SingletonHolder.SINGLETON_4;
    }
}
