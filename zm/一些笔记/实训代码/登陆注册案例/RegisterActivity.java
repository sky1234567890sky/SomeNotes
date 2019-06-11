package com.example.login_registerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 账号
     */
    private EditText mLoginName1;
    /**
     * 密码
     */
    private EditText mLoginPsw1;
    /**
     * 确认密码
     */
    private EditText mLoginPsw2;
    /**
     * 验证码
     */
    private EditText mCode;
    /**
     * 获取验证码
     */
    private Button mHuoqu;
    /**
     * 注册
     */
    private Button mLogin1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mLoginName1 = (EditText) findViewById(R.id.login_name1);
        mLoginPsw1 = (EditText) findViewById(R.id.login_psw1);
        mLoginPsw2 = (EditText) findViewById(R.id.login_psw2);


        mCode = (EditText) findViewById(R.id.code);
        mHuoqu = (Button) findViewById(R.id.huoqu);

        mHuoqu.setOnClickListener(this);
        mLogin1 = (Button) findViewById(R.id.login1);
        mLogin1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.huoqu:
                mCode.setText("验证码");
                break;
            case R.id.login1:
                String name = mLoginName1.getText().toString().trim();

                String psw = mLoginPsw1.getText().toString().trim();
                String psw1 = mLoginPsw2.getText().toString().trim();

                if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(psw)&& psw.equals(psw1)){
                    Intent intent = new Intent();
                    intent.putExtra("name",name);
                    intent.putExtra("psw",psw);
                    setResult(200,intent);
                    finish();
                }else{
                    Toast.makeText(this, "账号或者密码不能为空.....", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
