package com.jiyun.day07.test1;

/**
 * 卖报案例：
 */
public class TestMain {

    public static void main(String[] args) {
        //1.创建被观察者
        Observable observable = new Observable();

        //2.创建观察者
        Observer observer1 = new Observer("张三");
        Observer observer2 = new Observer("李四");
        Observer observer3 = new Observer("王五");

        //3.产生订阅关系
        observable.subscribe(observer1);
        observable.subscribe(observer2);
        observable.subscribe(observer3);

        //4.触发被观察者中的方法，给观察者传递对应的事件消息
        observable.notifyMsg("最新报纸到货了，请细心品读！！");
    }
}
