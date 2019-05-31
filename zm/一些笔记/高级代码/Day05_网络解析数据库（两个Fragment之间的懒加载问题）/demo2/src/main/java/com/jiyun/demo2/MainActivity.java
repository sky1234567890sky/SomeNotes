package com.jiyun.demo2;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jiyun.demo2.adapter.MyPagerAdapter;
import com.jiyun.demo2.fragment.CollectFragment;
import com.jiyun.demo2.fragment.HomeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment > list;
    private ArrayList<String> title;
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);

        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new CollectFragment());
        title = new ArrayList<>();
        title.add("首頁");
        title.add("收藏");

        adapter = new MyPagerAdapter(getSupportFragmentManager(),list,title);
        vp.setAdapter(adapter);

        tab.setupWithViewPager(vp);
    }
}
