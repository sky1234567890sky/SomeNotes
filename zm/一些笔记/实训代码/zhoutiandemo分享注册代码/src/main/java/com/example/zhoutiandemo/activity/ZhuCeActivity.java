package com.example.zhoutiandemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhoutiandemo.R;
import com.example.zhoutiandemo.bean.YanZhengBean;
import com.example.zhoutiandemo.url.MyServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZhuCeActivity extends AppCompatActivity {

    private WebView zhuce_web;
    private static final String TAG = "ZhuCeActivity";
    private TextView yanzhengma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        initView();
        initData();
    }

    private void initData() {
        zhuce_web.loadUrl(MyServer.zhuCeurl);
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MyServer.yanzhengurl)
                .build();

        MyServer myServer = retrofit.create(MyServer.class);

        Call<YanZhengBean> call = myServer.getYanZheng();

        call.enqueue(new Callback<YanZhengBean>() {
            @Override
            public void onResponse(Call<YanZhengBean> call, Response<YanZhengBean> response) {
                String data = response.body().getData();

                if (data != null) {
                    Log.d(TAG, "onResponse: " + data);
                    yanzhengma.setText(data);
                }
            }

            @Override
            public void onFailure(Call<YanZhengBean> call, Throwable t) {
                Toast.makeText(ZhuCeActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        zhuce_web = (WebView) findViewById(R.id.zhuce_web);
        yanzhengma = (TextView) findViewById(R.id.yanzhengma);
    }

/*    private void initZhuCe() {

        submit();


    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MyServer.yanzhengurl)
                .build();

        MyServer myServer = retrofit.create(MyServer.class);

        Call<YanZhengBean> call = myServer.getYanZheng();

        call.enqueue(new Callback<YanZhengBean>() {
            @Override
            public void onResponse(Call<YanZhengBean> call, Response<YanZhengBean> response) {
                data = response.body().getData();

                if (data != null) {
                    yanzhengma.setText(data);
                }
            }

            @Override
            public void onFailure(Call<YanZhengBean> call, Throwable t) {
                Toast.makeText(ZhuCeActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submit() {
        String id = zhuce_id.getText().toString().trim();
        String pass = zhuce_pass.getText().toString().trim();
        String pass_pass = zhuce_pass_pass.getText().toString().trim();
        String yanzhengma = zhuce_yanzhengma.getText().toString().trim();

        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(pass_pass)) {
            Toast.makeText(this, "用户信息不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.equals(pass_pass) != true) {
            Toast.makeText(this, "密码不相同", Toast.LENGTH_SHORT).show();
            return;
        }
        if (data != null) {
            if (TextUtils.isEmpty(yanzhengma)) {
                Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                return;
            }
            if (yanzhengma.equals(data) != true) {
                Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
                return;
            } else {
                onSuccess(id,pass);
                return;
            }
        } else {
            Toast.makeText(this, "请先获取验证码", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    private void onSuccess(String id, String pass) {

        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        finish();
    }*/
}
