package com.example.zhoutiandemo.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhoutiandemo.R;
import com.example.zhoutiandemo.bean.UpLoadBean;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//王董辉
public class ShangChuanActivity extends Activity implements View.OnClickListener {

    private Handler handler = new Handler();
    private int time = 1;
    private PopupWindow popupWindow;
    private File file;
    private Uri imageUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shang_chuan);

        initData();
    }

    private void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (time == 0) {
                    showPopwindow();
                } else {
                    time--;
                    initData();
                }
            }
        }, 1000);

    }

    private void showPopwindow() {
        View inflate = View.inflate(this, R.layout.popupwindow_shangchuan, null);
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        //点击外部消失
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.E3C6C6));

        popupWindow.setContentView(inflate);
        popupWindow.setTouchable(true);

        popupWindow.showAtLocation(inflate, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


        View xiangji = inflate.findViewById(R.id.xiangji);
        View xiangce = inflate.findViewById(R.id.xiangce);
        View quxiao = inflate.findViewById(R.id.shangchuan_quxiao);

        xiangji.setOnClickListener(this);
        xiangce.setOnClickListener(this);
        quxiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xiangji:
                takePhoto();
                break;
            case R.id.xiangce:
                takesD();
                break;
            case R.id.shangchuan_quxiao:
                finish();
                break;
        }
        popupWindow.dismiss();
    }

    private void takePhoto() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
            updateFile();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, 200);
        }
    }

    private void takesD() {
//权限判断
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            openAlbum();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (permissions[0] == Manifest.permission.CAMERA) {
                updateFile();
            } else if (permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                openAlbum();
            }
        }
    }

    private void updateFile() {
        //创建文件用于保存图片
        file = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //适配7.0
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            imageUri = Uri.fromFile(file);
        } else {
            //第二个参数要和清单文件中的配置保持一致
            imageUri = FileProvider.getUriForFile(this, "com.baidu.upload.provider", file);
        }

        //启动相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍照图片存入mImageUri
        startActivityForResult(intent, CAMERA_CODE);
    }

    private static final int CAMERA_CODE = 200;
    private static final int ALBUM_CODE = 100;
    private static final String TAG = "MainActivity";

    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, ALBUM_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    //处理照相之后的结果并上传
                    uploadOk(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == ALBUM_CODE) {
            //相册
            if (resultCode == RESULT_OK) {
                Uri imageUri = data.getData();
                //处理uri,7.0以后的fileProvider 把URI 以content provider 方式 对外提供的解析方法
                File file = getFileFromUri(imageUri, this);

                if (file.exists()) {
                    uploadOk(file);
                }
            }
        }
    }

    private File getFileFromUri(Uri imageUri, Context context) {
        if (imageUri == null) {
            return null;
        }
        switch (imageUri.getScheme()) {
            case "content":
                return getFileFromContentUri(imageUri, context);
            case "file":
                return new File(imageUri.getPath());
            default:
                return null;
        }
    }


    //通过内容解析中查询uri中的文件路径
    private File getFileFromContentUri(Uri imageUri, Context context) {
        if (imageUri == null) {
            return null;
        }
        File file = null;
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(imageUri, filePathColumn, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();

            if (!TextUtils.isEmpty(filePath)) {
                file = new File(filePath);
            }
        }
        return file;
    }

    //文件上传
    private void uploadOk(File file) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        //设置上传文件以及文件对应的MediaType类型
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//设置文件上传类型
                .addFormDataPart("key", "H1807A")//文件在服务器中保存的文件夹路径
                .addFormDataPart("file", file.getName(), requestBody)//包含文件名字和内容
                .build();

        Request request = new Request.Builder()
                .url("http://yun918.cn/study/public/file_upload.php")
                .post(body)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();

                Gson gson = new Gson();
                final UpLoadBean upLoadBean = gson.fromJson(str, UpLoadBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (upLoadBean != null && upLoadBean.getCode() == 200) {
                            Log.d("lzj", upLoadBean.getData().getUrl());
                            success(upLoadBean);
                        } else {
                            Toast.makeText(ShangChuanActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void success(UpLoadBean upLoadBean) {
        String url = upLoadBean.getData().getUrl();
        if (url!=null){
            Intent intent = new Intent(ShangChuanActivity.this,Main3Activity.class);
            intent.putExtra("img",url);
            startActivity(intent);
            Toast.makeText(this, upLoadBean.getRes(), Toast.LENGTH_SHORT).show();
        }

    }
}
