package com.jiyun.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSink;
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
                //initDataPath();
                //initDataUrl();
                //initDataForm();
                //initDataJson();
                //initDataNo();
                initData();
                break;
        }
    }

    /**
     * 通用
     */
    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .build();

        MyApi myApi = retrofit.create(MyApi.class);

        //1.form
        FormBody body = new FormBody.Builder()
                .add("page", "1")
                .add("count", "2")
                .add("type", "video")
                .build();
        Call<ResponseBody> form = myApi.data("getJoke", body,
                "application/x-www-form-urlencoded");

        //2.json
        MediaType type = MediaType.parse("application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page","1");
            jsonObject.put("count","2");
            jsonObject.put("type","video");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String string = jsonObject.toString();

        RequestBody requestBody = RequestBody.create(type, string);
        Call<ResponseBody> json = myApi.data("getJoke", requestBody,
                "application/json");

        //3.no
        RequestBody request1 = RequestBody.create(MediaType.parse
                ("application/x-www-form-urlencoded"), "");
        Call<ResponseBody> no = myApi.data("recommendPoetry", request1,
                "application/x-www-form-urlencoded");

    }

    /**
     * 无参
     */
    private void initDataNo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .build();

        MyApi myApi = retrofit.create(MyApi.class);

        //1.
        RequestBody requestBody = RequestBody.create(MediaType.parse
                ("application/x-www-form-urlencoded"), "");

        Call<ResponseBody> call = myApi.noData(requestBody);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e(TAG, "onResponse: "+string );
                txt.setText(string);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onResponse: "+t.getMessage() );
            }
        });
    }

    /**
     * Json
     */
    private void initDataJson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .build();

        MyApi myApi = retrofit.create(MyApi.class);

        //1.
        MediaType type = MediaType.parse("application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page","1");
            jsonObject.put("count","2");
            jsonObject.put("type","video");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String string = jsonObject.toString();

        RequestBody requestBody = RequestBody.create(type, string);

        Call<ResponseBody> call = myApi.jsonData2(requestBody,"application/json");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e(TAG, "onResponse: "+string );
                txt.setText(string);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onResponse: "+t.getMessage() );
            }
        });
    }

    /**
     * Form
     */
    private void initDataForm() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .build();

        MyApi myApi = retrofit.create(MyApi.class);

        //1.
        FormBody body = new FormBody.Builder()
                .add("page", "1")
                .add("count", "2")
                .add("type", "video")
                .build();
        //2.
        MediaType type = MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8");
        String formStr = "page=1&count=2&type=video";
        RequestBody requestBody = RequestBody.create(type, formStr);

        //3.
        RequestBody body1 = new RequestBody() {

            @Override
            public MediaType contentType() {
                return MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8");
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("page=1&count=2&type=video");
            }
        };

        Call<ResponseBody> call = myApi.formData1(requestBody);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e(TAG, "onResponse: "+string );
                txt.setText(string);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onResponse: "+t.getMessage() );
            }
        });
    }

    /**
     * Url
     */
    private void initDataUrl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.Url2)
                .build();

        MyServer myServer = retrofit.create(MyServer.class);

        /*Call<ResponseBody> call = myServer.urlData1
                ("categories?appKey=908ca46881994ffaa6ca20b31755b675");*/

        Call<ResponseBody> call = myServer.urlData2("categories",
                "908ca46881994ffaa6ca20b31755b675");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e(TAG, "onResponse: "+string );
                txt.setText(string);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onResponse: "+t.getMessage() );
            }
        });
    }

    /**
     * Path
     */
    private void initDataPath() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.Url1)
                .build();

        MyServer myServer = retrofit.create(MyServer.class);

        Call<ResponseBody> call = myServer.pathData(1);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e(TAG, "onResponse: "+string );
                txt.setText(string);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onResponse: "+t.getMessage() );
            }
        });
    }
}
