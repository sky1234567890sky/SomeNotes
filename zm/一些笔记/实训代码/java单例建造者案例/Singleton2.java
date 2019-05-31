package com.jiyun.day03.single;

/**
 * Created by $lzj on 2019/5/30.
 * 懒汉式
 */
public class Singleton2 {

    //1.
    private Singleton2(){}

    private static Singleton2 singleton2;

    public static Singleton2 getSingleton2() {
        if (singleton2 == null){
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}
