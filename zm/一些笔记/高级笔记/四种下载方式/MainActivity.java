package com.jiyun.day15;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();
    private static final int THREAD_COUNT = 3;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private File sd;
    private String url = "http://cdn.banmi.com/banmiapp/apk/banmi_330.apk";
    private ProgressBar pb;
    private ProgressBar pb1;
    private ProgressBar pb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
			();
    }

    private void initSD() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            readSD();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == PackageManager
                        .PERMISSION_GRANTED) {
                    readSD();
                }
                break;
        }
    }

    private void readSD() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sd = Environment.getExternalStorageDirectory();
        }
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        pb = (ProgressBar) findViewById(R.id.pb);
        pb1 = (ProgressBar) findViewById(R.id.pb1);
        pb1.setOnClickListener(this);
        pb2 = (ProgressBar) findViewById(R.id.pb2);
        pb2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                ok(sd + "/" + "abc.apk");
                break;
            case R.id.btn2:
                retrofit(sd + "/" + "abc123.apk");
                break;
            case R.id.btn3:
                http(sd + "/" + "abc456.apk");
                break;
            case R.id.btn4:
                moreHttp();
                break;
        }
    }

    private String mDownPath = "/storage/emulated/0" + "/" + "abc000.apk";

    private void moreHttp() {
        ThreadManager.getThreadManager().execute(new Runnable() {
            @Override
            public void run() {

                try {
                    //1.通过网络加载获取文件长度
                    URL urlStr = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) urlStr.openConnection();
                    int contentLength = con.getContentLength();
                    Log.e(TAG, "run: " + contentLength);

                    //2.c创建空白文件，指定长度
                    RandomAccessFile rw = new RandomAccessFile(mDownPath, "rw");
                    rw.setLength(contentLength);

                    //3.各自线程设置下载对应的长度
                    long block = contentLength / THREAD_COUNT;

                    //4.创建指定个数的线程并给定下载范围下载
                    for (int i = 0; i < THREAD_COUNT; i++) {
                        long start = i * block;
                        long end = (i + 1) * block - 1;

                        if (i == THREAD_COUNT - 1) {
                            end = contentLength - 1;
                        }
                        //4.开始各自线程的下载即可（加请求范围头，判断状态码206，从对应线程开始位置写入数据）
                        //down(i, start, end);//多线程下载

                        downContinue(i, start, end);//多线程断点下载
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void downContinue(final int threadId, final long start, final long end) {
        Log.d(TAG, "线程: " + threadId + ",下载范围:" + start + "--" + end);
        ThreadManager.getThreadManager().execute(new Runnable() {
            @Override
            public void run() {

                //记录当前线程下载位置
                long currentPosition = start;

                //获取sp中的下载位置,如果为0,说明没有下载过或者下载完成过
                long position = (long)SharedPreferencesUtils.getParam(MainActivity.this, threadId + "",
                        0L);

                if (position == 0){//没有下载记录
                    currentPosition = start;
                    Log.d(TAG, "非断点续传: "+threadId+",currentPosition:"+currentPosition);
                }else{//有级录
                    currentPosition = position;
                    Log.d(TAG, "断点续传: "+threadId+",currentPosition:"+currentPosition);
                }

                URL urlStr = null;
                try {
                    urlStr = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) urlStr.openConnection();
                    //设置请求部分数据资源:Range:bytes=10-30
                    con.setRequestProperty("Range", "bytes=" + currentPosition + "-" + end);

                    int responseCode = con.getResponseCode();
                    Log.d(TAG, "responseCode: " + responseCode);
                    //206,请求部分资源
                    if (responseCode == 206) {
                        InputStream inputStream = con.getInputStream();
                        RandomAccessFile rw = new RandomAccessFile(mDownPath, "rw");
                        //设置当前线程写入的位置
                        rw.seek(currentPosition);

                        int length = -1;
                        byte[] bys = new byte[1024 * 10];

                        while ((length = inputStream.read(bys)) != -1) {
                            rw.write(bys, 0, length);

                            currentPosition += length;

                            Log.d(TAG, "线程: " + threadId + "开始：" + start + " 当前下载：" + currentPosition + " " +
                                    "结束:" + end);

                            //将当前写入的位置保存
                            SharedPreferencesUtils.setParam(MainActivity.this,threadId+"",currentPosition);
                            if (threadId == 0){
                                pb.setMax((int)end);
                                pb.setProgress((int)currentPosition);
                            }else  if (threadId == 1){
                                pb1.setMax((int)end);
                                pb1.setProgress((int)currentPosition);
                            }else  if (threadId == 2){
                                pb2.setMax((int)end);
                                pb2.setProgress((int)currentPosition);
                            }
                        }

                        inputStream.close();
                        rw.close();

                        Log.d(TAG, "线程: " + threadId + "下载玩完毕");
                        //完成下载后将当前的写入位置置零
                        SharedPreferencesUtils.setParam(MainActivity.this,threadId+"",0L);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void down(final int threadId, final long start, final long end) {
        Log.d(TAG, "线程: " + threadId + ",下载范围:" + start + "--" + end);
        ThreadManager.getThreadManager().execute(new Runnable() {
            @Override
            public void run() {


                URL urlStr = null;
                try {
                    urlStr = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) urlStr.openConnection();
                    //设置请求部分数据资源:Range:bytes=10-30
                    con.setRequestProperty("Range", "bytes=" + start + "-" + end);

                    int responseCode = con.getResponseCode();
                    Log.d(TAG, "responseCode: " + responseCode);
                    //206,请求部分资源
                    if (responseCode == 206) {

                        InputStream inputStream = con.getInputStream();
                        RandomAccessFile rw = new RandomAccessFile(mDownPath, "rw");
                        rw.seek(start);

                        int count = (int) start;

                        int length = -1;
                        byte[] bys = new byte[1024 * 10];

                        while ((length = inputStream.read(bys)) != -1) {
                            rw.write(bys, 0, length);

                            count += length;

                            Log.d(TAG, "线程: " + threadId + "开始：" + start + " 当前下载：" + count + " " +
                                    "结束:" + end);

                            if (threadId == 0){
                                pb.setMax((int)end);
                                pb.setProgress((int)count);
                            }else  if (threadId == 1){
                                pb1.setMax((int)end);
                                pb1.setProgress((int)count);
                            }else  if (threadId == 2){
                                pb2.setMax((int)end);
                                pb2.setProgress((int)count);
                            }
                        }

                        inputStream.close();
                        rw.close();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void http(final String path) {
        ThreadManager.getThreadManager().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL urlStr = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) urlStr.openConnection();

                    int responseCode = con.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = con.getInputStream();
                        int max = con.getContentLength();

                        saveFile(inputStream, path, max);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void retrofit(final String path) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        MyServer myServer = retrofit.create(MyServer.class);

        Observable<ResponseBody> download = myServer.download();

        download.subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        long max = responseBody.contentLength();
                        InputStream inputStream = responseBody.byteStream();

                        saveFile(inputStream, path, max);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void ok(final String path) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                InputStream inputStream = body.byteStream();
                long max = body.contentLength();

                //文件下载保存
                saveFile(inputStream, path, max);
            }
        });
    }

    private void saveFile(InputStream inputStream, final String path, long max) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(path));

            long count = 0;

            int length = -1;
            byte[] bys = new byte[1024 * 10];

            while ((length = inputStream.read(bys)) != -1) {
                fos.write(bys, 0, length);

                count += length;

                Log.e(TAG, "count: " + count + ", max:" + max);

                //进度条和视频播放SurfaceView可以直接在子线程中刷新
                pb.setMax((int) max);
                pb.setProgress((int) count);
            }

            inputStream.close();
            fos.close();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "下载完毕", Toast.LENGTH_SHORT).show();

                    //apk安装处理
                    mPath = path;
                    InstallUtil.installApk(MainActivity.this, path);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 8.0安装处理
     */
    private String mPath = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == InstallUtil.UNKNOWN_CODE) {
            InstallUtil.installApk(MainActivity.this, mPath);//再次执行安装流程，包含权限判等
        }
    }
}
