package com.jiyun.demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)==PackageManager.PERMISSION_GRANTED){
            initData();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 100:
                if (grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initData();
                }
                break;
        }
    }

    private void initData() {

        //视频
        Uri externalContentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        //音频
        Uri externalContentUri1 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        //相册
        Uri externalContentUri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        //短信
        Uri parse = Uri.parse("content://sms");

        //通讯录
        Uri contentUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String displayName = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
        String number = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Cursor query = getContentResolver().query(contentUri, new String[]{displayName,number}, null, null, null);
        if (query!=null){
            while (query.moveToNext()){
                String name = query.getString(query.getColumnIndex(displayName));
                String pNumber = query.getString(query.getColumnIndex(number));

                Log.e(TAG, "initData: "+name +":  "+ pNumber );
            }
        }
    }
}
