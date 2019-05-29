package com.example.zhoutiandemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.zhoutiandemo.R;
import com.example.zhoutiandemo.adapter.ViewPageAdapter;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initData();
    }

    private void initData() {
        ArrayList<View> list = new ArrayList<>();
        list.add(View.inflate(this, R.layout.viewpager_view1, null));
        list.add(View.inflate(this, R.layout.viewpager_view2, null));
        list.add(View.inflate(this, R.layout.viewpager_view3, null));
        View view = View.inflate(this, R.layout.viewpager_view4, null);
        list.add(view);

        ViewPageAdapter pageAdapter = new ViewPageAdapter(list);
        vp.setAdapter(pageAdapter);
        View tiyan = view.findViewById(R.id.tiyan);

        tiyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, Main3Activity.class));
                finish();
            }
        });
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
    }
}
