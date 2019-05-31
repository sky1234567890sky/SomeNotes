package com.jiyun.day03;

import com.jiyun.day03.builder.Computer;
import com.jiyun.day03.single.Singleton1;
import com.jiyun.day03.single.Singleton3;
import com.jiyun.day03.single.Singleton4;

import okhttp3.OkHttpClient;

/**
 * Created by $lzj on 2019/5/30.
 */
public class Test {

    public static void main(String[] args) {

        //单利
        //single();

        //构建者模式
        bulider();
    }

    private static void bulider() {
        Computer build = new Computer.Builder()
                .setColor("")
                .setOs("")
                .build();

        build.play();
    }

    private static void single() {
        Singleton1 singleton1 = Singleton1.getSingleton1();

        Singleton3 singleton3 = Singleton3.getSingleton3();

        Singleton4 instance = Singleton4.getInstance();
    }
}
