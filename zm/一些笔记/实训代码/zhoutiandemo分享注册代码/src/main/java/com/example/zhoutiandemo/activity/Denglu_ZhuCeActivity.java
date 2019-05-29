package com.example.zhoutiandemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhoutiandemo.R;
import com.example.zhoutiandemo.bean.LoginBean;
import com.example.zhoutiandemo.url.MyServer;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Denglu_ZhuCeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText denglu_id;
    private EditText denglu_pass;
    private Button denglu_btn;
    private TextView denglu_zhuce;
    private Button denlu_qq;
    private Button denlu_weixin;
    private Button denlu_xinlang;


    //5cb2df7d3fc195d7eb00116b  appkey

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu__zhu_ce);
        initView();
    }

    private void initView() {
        denglu_id = (EditText) findViewById(R.id.denglu_id);
        denglu_pass = (EditText) findViewById(R.id.denglu_pass);
        denglu_btn = (Button) findViewById(R.id.denglu_btn);
        denglu_zhuce = (TextView) findViewById(R.id.denglu_zhuce);
        denlu_qq = (Button) findViewById(R.id.denlu_qq);
        denlu_weixin = (Button) findViewById(R.id.denlu_weixin);
        denlu_xinlang = (Button) findViewById(R.id.denlu_xinlang);

        denglu_btn.setOnClickListener(this);
        denlu_qq.setOnClickListener(this);
        denlu_weixin.setOnClickListener(this);
        denlu_xinlang.setOnClickListener(this);
        denglu_zhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.denglu_btn:
                submit();
                getData();
                break;
            case R.id.denlu_qq:
                login(SHARE_MEDIA.QQ);
                break;
            case R.id.denlu_weixin:
                login(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.denlu_xinlang:
                login(SHARE_MEDIA.SINA);
                break;
            case R.id.denglu_zhuce:
                startActivity(new Intent(Denglu_ZhuCeActivity.this, ZhuCeActivity.class));
                break;
        }
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.dengluurl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        MyServer myServer = retrofit.create(MyServer.class);

        Observable<LoginBean> login = myServer.login(denglu_id.getText().toString(), denglu_pass.getText().toString());

        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean == null) {
                            Toast.makeText(Denglu_ZhuCeActivity.this, "出现错误！", Toast.LENGTH_SHORT).show();
                        } else {
                            if (loginBean.getCode() == 200) {
                                onSuccess(loginBean);
                            } else {
                                Toast.makeText(Denglu_ZhuCeActivity.this, loginBean.getRet().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(Denglu_ZhuCeActivity.this, "出现未知错误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void onSuccess(LoginBean loginBean) {
        Intent intent = new Intent(Denglu_ZhuCeActivity.this, Main3Activity.class);
        intent.putExtra("name", denglu_id.getText().toString());
        startActivity(intent);
        finish();
    }

    private void login(SHARE_MEDIA type) {
        UMShareAPI umShareAPI = UMShareAPI.get(this);
        umShareAPI.getPlatformInfo(Denglu_ZhuCeActivity.this, type, umAuthListener);
    }

    private static final String TAG = "Denglu_ZhuCeActivity";
    UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * form 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        //授权成功的回调
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Log.d(TAG, "key: " + key + ",value:" + value);
            }
            Toast.makeText(Denglu_ZhuCeActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        //授权失败的回调
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(Denglu_ZhuCeActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        //授权取消的回调
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(Denglu_ZhuCeActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    private void submit() {
        // validate
        String id = denglu_id.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
      
        String pass = denglu_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
