package com.example.day04_homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.day04_homework.R;

public class DeActivity extends AppCompatActivity {

    /**
     * 请输入。。。。。
     */
    private EditText mEt;
    private EditText add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de);
        initView();
    }

    private void initView() {
        mEt = (EditText) findViewById(R.id.et);
        add = findViewById(R.id.add);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mEt.setText(name);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = mEt.getText().toString();

                Intent in = new Intent();
                in.putExtra("s",s);

                in.putExtra("add",add.getText().toString());
                setResult(200,in);
                finish();
            }
        });
    }
}
