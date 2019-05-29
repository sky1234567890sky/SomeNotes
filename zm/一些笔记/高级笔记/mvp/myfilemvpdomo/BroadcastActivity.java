package com.example.myfilemvpdomo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BroadcastActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;

    // 广播
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
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
                initData();
                break;
        }
    }
    private void initData() {
        Intent intent = new Intent("sky.intent.action.abc");
        intent.putExtra("cid","发送");
        sendBroadcast(intent);
    }
}
