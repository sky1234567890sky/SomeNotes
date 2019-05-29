package com.jiyun.photoimage;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CAMERA_CODE = 1;
    private static final int ALBUM_CODE = 2;
    private static final String TAG = MainActivity.class.getName();

    private Button btn1;
    private ImageView img;
    private File mFile;
    private Uri mImageUri;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn1.setOnClickListener(this);
        img = (ImageView) findViewById(R.id.img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                takePhoto();
                break;
            case R.id.btn2:
                takePick();
                break;
        }
    }

    //相册授权
    private void takePick() {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            openAlbum();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},200);
        }
    }
    //拍照授权
    private void takePhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager
                .PERMISSION_GRANTED) {
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 100) {//拍照
                openCamera();
            }else if (requestCode == 200){
                openAlbum();
            }
        }
    }

    //相册
    private void openAlbum() {

        //开启相册
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(intent,ALBUM_CODE);
    }

    //拍照
    private void openCamera() {

        //1.创建临时保存文件位置
        mFile = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        if (!mFile.exists()) {
            try {
                mFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //2.转换为Uri路径  (对7.0版本兼容)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mImageUri = Uri.fromFile(mFile);
        } else {
            //第二个参数要和清单文件中的配置保持一致
            mImageUri = FileProvider.getUriForFile(this, "com.baidu.upload.provider", mFile);
        }

        //3.开启相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);//将拍照图片存入mImageUri
        startActivityForResult(intent, CAMERA_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == CAMERA_CODE) {

                //拍照成功显示图片
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream
                            (mImageUri));
                    img.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                //文件上传
                uploadFile(mFile);
            }else if (requestCode == ALBUM_CODE){
               //相册选择显示
                //获取到图片对应的Uri路径
                Uri data1 = data.getData();
                /*try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data1));
                    img.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/

                //将Uri路径转换为对应的File文件
                File mF = getFileFromUri(data1,this);

                //文件上传
                uploadFile(mF);
            }
        }

    }

    private File getFileFromUri(Uri mImageUri, Context context) {

        if (mImageUri== null){
            return  null;
        }

        switch (mImageUri.getScheme()){
            case "content":
                return getFileFromContentUri(mImageUri, context);
            case "file":
                return  new File(mImageUri.getPath());
            default:
                return null;
        }
    }
    private File getFileFromContentUri(Uri mImageUri, Context context) {

        File file = null;

        Cursor cursor = context.getContentResolver().query(mImageUri, new String[]{MediaStore.MediaColumns.DATA}, null, null,
                null);

        if (cursor!=null){
            if (cursor.moveToNext()){
                String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
                cursor.close();

                if (!TextUtils.isEmpty(filePath)) {
                    file = new File(filePath);
                }
            }
        }
        return file;
    }
    private void uploadFile(File mFile) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), mFile);

        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "H1809A")
                .addFormDataPart("file", mFile.getName(), requestBody)
                .build();

        Request request = new Request.Builder()
                .url("http://yun918.cn/study/public/file_upload.php")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();

                Gson gson = new Gson();
                final UpLoadBean upLoadBean = gson.fromJson(str, UpLoadBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (upLoadBean != null) {
                            if (upLoadBean.getCode() == 200) {
                                Log.e(TAG, "run: " + upLoadBean.getData().getUrl());
                                Glide.with(MainActivity.this).load(upLoadBean.getData().getUrl())
                                        .into(img);
                                Toast.makeText(MainActivity.this, upLoadBean.getRes(), Toast
                                        .LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, upLoadBean.getRes(), Toast
                                        .LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
