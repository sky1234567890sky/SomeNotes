package com.example.login_registerdemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login_registerdemo.net.LoginBean;
import com.example.login_registerdemo.net.MSyerver;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 账号
     */
    private EditText mLoginName;
    /**
     * 密码
     */
    private EditText mLoginPsw;
    /**
     * 登陆
     */
    private Button mLogin;
    /**
     * 注册
     */
    private Button mRegister;
    private CheckBox box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    private void initData() {
        String name = mLoginName.getText().toString().trim();
        String psw = mLoginPsw.getText().toString().trim();

        Retrofit rf = new Retrofit.Builder()
                .baseUrl(MSyerver.login)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        rf.create(MSyerver.class)
                .getLogin(name,psw)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(LoginBean loginBean) {

                        //成功并且记住密码
                        Toast.makeText(LoginActivity.this, "成功："+loginBean.toString(), Toast.LENGTH_SHORT).show();

                        //记住密码
                        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                        SharedPreferences.Editor edit = sp.edit();
                        //获取界面的勾选状态
                        boolean checked = box.isChecked();
                        // 要记住密码
                        //获取界面上的  账号  密码
                        if (checked){
                            String name = mLoginName.getText().toString().trim();
                            String psw = mLoginPsw.getText().toString().trim();
                            edit.putString("n",name);
                            edit.putString("p",psw);
                        }

                        //保存存储界面上的  勾选状态
                        edit.putBoolean("issave",checked);

                        edit.commit();//提交
                    }
                    @Override
                    public void onError(Throwable e) {
                        //失败
                        Toast.makeText(LoginActivity.this, "失败："+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onComplete() {

                    }
                });

    }
    private void initView() {
        mLoginName = (EditText) findViewById(R.id.login_name);
        mLoginPsw = (EditText) findViewById(R.id.login_psw);
        box = (CheckBox) findViewById(R.id.box);

        //获取密码
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
//        SharedPreferences.Editor edit = sp.edit();
        boolean issave = sp.getBoolean("issave", false);
        if (issave){
            //获取账号  获取密码  显示到界面上
            String name = sp.getString("n", "");
            String paw = sp.getString("p", "");
            // 显示到界面上
            mLoginName.setText(name);
            mLoginPsw.setText(paw);
        }
        //设置勾选状态
        box.setChecked(issave);

        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);

        mRegister = (Button) findViewById(R.id.register);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login://登录

                //点击登陆记住密码
                String name = mLoginName.getText().toString().trim();
                String psw = mLoginPsw.getText().toString().trim();

                if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(psw)){
                    initData();//记住密码
                }else{
                    Toast.makeText(this, "账号或者密码不能为空.....", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register://注册
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivityForResult(intent,100);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode ==200){

            String name = data.getStringExtra("name");
            String psw = data.getStringExtra("psw");
            mLoginName.setText(name);
            mLoginPsw.setText(psw);
        }
    }
}
