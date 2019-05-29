package com.jy.day13broadcastreceiver;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 发送无序广播
     */
    private Button mSend1;
    /**
     * 发送无序广播  动态
     */
    private Button mSend2;
    private MyBroadCastRec3 rec3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mSend1 = (Button) findViewById(R.id.send1);
        mSend1.setOnClickListener(this);
        mSend2 = (Button) findViewById(R.id.send2);
        mSend2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.send1:
                Intent it1 = new Intent("aaa");
                //8.0开始自定义广播系统不建议静态注册,如果静态注册,需要加下面这行代码,指定广播的类
                it1.setComponent(new ComponentName(this, MyBroadcastRec2.class));

                it1.putExtra("data2", "我是发送的信息。。。222");
                sendBroadcast(it1);
                break;
            case R.id.send2:
                Intent it3 = new Intent("ccc");
                it3.putExtra("data3","三个老虎在开会");
                sendBroadcast(it3);
                break;
        }
    }

    //此方法：  此界面运行前调用
    @Override
    protected void onResume() {
        super.onResume();
        //动态注册广播
        rec3 = new MyBroadCastRec3();
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("ccc");//添加过滤器
        registerReceiver(rec3,intentFilter3);//注册到系统中
    }

    //此页面暂停  解除动态注册的广播

    @Override
    protected void onPause() {
        super.onPause();
        //解除注册  清除缓存，节省内存
        unregisterReceiver(rec3);

    }
}
