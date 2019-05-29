package com.example.myfilemvpdomo.fram;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.myfilemvpdomo.api.InstallUtil;
import com.example.myfilemvpdomo.api.MyServer;
import com.example.myfilemvpdomo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownFileFragment extends Fragment implements View.OnClickListener {


    private Button btn1;
    private File sd;
    private SeekBar bar;
    private String mapk;

    public DownFileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_down_file, container, false);
        initView(inflate);
        initData();
        return inflate;
    }
    private void initView(View inflate) {
        btn1 = (Button) inflate.findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        bar = (SeekBar) inflate.findViewById(R.id.bar);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                downFile(sd + "/" + "ss123.apk");
                break;
        }
    }
    private void initData() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            readSd();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }
    private void readSd() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sd = Environment.getExternalStorageDirectory();
        }
    }

    private void downFile(final String s) {

        Retrofit rf = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(MyServer.downLoad)
                .build();

        rf.create(MyServer.class).download()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {

                        InputStream inputStream = responseBody.byteStream();
                        long max = responseBody.contentLength();
                        saveLoad(inputStream, max, s);

                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void saveLoad(InputStream inputStream, long max, final String s) {
        File file = new File(s);
        try {
            FileOutputStream stream = new FileOutputStream(file);
            int count = 0;

            int length = -1;

            byte bys[] = new byte[1024 * 10];

            while ((length = inputStream.read(bys)) != -1) {

                stream.write(bys, 0, length);
                count += length;
                Log.i("tag", "进度: "+count+"，总大小："+max);
                //最大进度和当前进度
                bar.setMax((int)max);
                bar.setProgress((int)count);
            }

            inputStream.close();
            stream.close();

            //
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "下载完毕", Toast.LENGTH_SHORT).show();
                    mapk = s;
                    //安装
                    InstallUtil.installApk(getActivity(),s);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    readSd();
                }else{
                    Toast.makeText(getActivity(), "错误", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_OK && InstallUtil.UNKNOWN_CODE == resultCode){
            InstallUtil.installApk(getActivity(),mapk);
        }
    }
}
