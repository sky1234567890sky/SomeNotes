package com.example.myfilemvpdomo.fram;


import android.Manifest;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myfilemvpdomo.api.MyServer;
import com.example.myfilemvpdomo.R;
import com.example.myfilemvpdomo.beans.UploadImage;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFragment extends Fragment {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.img2)
    ImageView img2;
    Unbinder unbinder;
    private File sd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_upload, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.img)
    public void onViewClicked() {
        initSd();
    }
    private void initSd() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){
            readSd();
        }else{
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (permissions.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readSd();
                }else{
                    Toast.makeText(getActivity(), "错误", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void readSd() {

        File file = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            sd = Environment.getExternalStorageDirectory();
            ///storage/emulated/legacy/chenyifaer.jpg
            file = new File(sd, "chenyifaer.jpg");
        }
        //普通参数
        RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"), "陈一法儿");
        //文件参数
        final RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);

        MultipartBody.Part file1 = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        Retrofit rf = new Retrofit.Builder()
                .baseUrl(MyServer.URLupdata)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rf.create(MyServer.class).get(body,file1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadImage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UploadImage uploadImage) {
                        UploadImage.DataBean data = uploadImage.getData();
                        Glide.with(getActivity()).load(data.getUrl()).into(img2);
                        Log.i("tag", "路径: "+data.getUrl());
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
