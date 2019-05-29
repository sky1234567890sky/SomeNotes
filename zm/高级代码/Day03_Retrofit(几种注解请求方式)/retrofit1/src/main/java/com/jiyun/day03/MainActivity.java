package com.jiyun.day03;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();
    private Button btn;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        txt = (TextView) findViewById(R.id.txt);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                //initData();
                initPost();
                break;
        }
    }

    private void initPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.Url)
                .build();

        MyServer myServer = retrofit.create(MyServer.class);

//        Call<ResponseBody> call = myServer.postData1(1, 2, "video");

        Map<String,Object> map = new HashMap<>();
        map.put("page",1);
        map.put("count",2);
        map.put("type","video");
        Call<ResponseBody> call = myServer.postData2(map);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();

                    Log.e(TAG, "onResponse: "+string );

                    txt.setText(string);//默认直接回调主线程
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onResponse: "+t.getMessage() );
            }
        });
    }

    private void initData() {

        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.Url)
                .build();

        //2.获取MyServer接口服务对象
        MyServer myServer = retrofit.create(MyServer.class);

        //3.获取Call对象
//        Call<ResponseBody> call = myServer.getData1();

//        Call<ResponseBody> call = myServer.getData2(1, 2, "video");

        Map<String,Object> map = new HashMap<>();
        map.put("page",1);
        map.put("count",2);
        map.put("type","video");

        Call<ResponseBody> call = myServer.getData3(map);

        //4.Call对象执行请求
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();

                    Log.e(TAG, "onResponse: "+string );

                    txt.setText(string);//默认直接回调主线程
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onResponse: "+t.getMessage() );
            }
        });
    }
}
