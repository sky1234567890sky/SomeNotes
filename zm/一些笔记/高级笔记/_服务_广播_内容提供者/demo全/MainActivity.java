package com.jiyun.demo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 广播
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("lzj.intent.action.ABC");
        registerReceiver(new MyReceiver0(),intentFilter);
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:

                Intent intent = new Intent("lzj.intent.action.ABC");
                sendBroadcast(intent);
                break;
            case R.id.btn2:

                Intent intent1 = new Intent("lzj.intent.action.ABC");
                sendOrderedBroadcast(intent1,null);
                break;
        }
    }
}
