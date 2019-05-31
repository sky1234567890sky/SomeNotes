package com.jiyun.day03.single;

/**
 * Created by $lzj on 2019/5/30.
 * 饿汉式
 */
public class Singleton1 {

    //1.构造方法私有化
    private Singleton1(){}

    //2.创建静态变量
    private static Singleton1 singleton1 = new Singleton1();

    //3.提供对外访问公共方法
    public static Singleton1 getSingleton1() {
        return singleton1;
    }
}
