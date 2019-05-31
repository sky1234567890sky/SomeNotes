package com.jiyun.day03.single;

/**
 * Created by $lzj on 2019/5/30.
 *
 * 双重锁
 */
public class Singleton3 {

    private Singleton3(){}

    private volatile static Singleton3 singleton3;

    public static Singleton3 getSingleton3() {
        if (singleton3 ==null){
            synchronized (Singleton3.class){
                if (singleton3 ==null){
                    singleton3 = new Singleton3();
                }
            }
        }
        return singleton3;
    }
}
