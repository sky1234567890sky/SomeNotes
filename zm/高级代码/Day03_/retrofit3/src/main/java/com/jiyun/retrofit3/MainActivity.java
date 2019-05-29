package com.jiyun.retrofit3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
                initData();
                break;
        }
    }

    private void initData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyData.Url1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyData myData = retrofit.create(MyData.class);

        Call<Bean> data = myData.getData2(1);

        data.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                Bean body = response.body();
                List<Bean.ResultsBean> results = body.getResults();

                txt.setText(results.toString());
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {

            }
        });
    }

}
