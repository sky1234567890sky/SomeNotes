package com.example.myfilemvpdomo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String cid = intent.getStringExtra("cid");
        Toast.makeText(context, ""+cid, Toast.LENGTH_SHORT).show();
    }
}
