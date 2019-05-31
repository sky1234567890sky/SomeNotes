package com.jiyun.day07;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                baseRx();
                break;
        }
    }

    private void baseRx() {

        //1.被观察者
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {

            //被观察者触发事件，通知观察者
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.e(TAG, "Observable所在的线程为: "+ Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
//                emitter.onComplete();
//                emitter.onError(new Throwable("abcd"));
                emitter.onNext(4);
                emitter.onNext(5);

            }
        });

        //2.观察者
        Observer<Integer> observer = new Observer<Integer>() {

            Disposable disposable;

            //订阅方法
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
                disposable = d;
            }

            //接收普通数据
            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "observer所在的线程为: "+ Thread.currentThread().getName());
                Log.e(TAG, "onNext: " +integer);
                if (integer == 3){
                    disposable.dispose();//切断观察者与被观察者之间的订阅关系
                    Log.e(TAG, "是否切断: " +disposable.isDisposed());//判断是否切断成功
                }
            }

            //错误
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " +e.getMessage());
            }

            //完成
            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: " );
            }
        };

        //3.订阅
        //observable.subscribe(observer);


        //线程切换
        observable.subscribeOn(Schedulers.io())//子线程：  产生事件线程在哪个线程执行
                .observeOn(AndroidSchedulers.mainThread())//主线程    消费事件线程在哪个线程执行
                .subscribe(observer);//订阅
    }

}
