package com.example.along.day16_demo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Toolbar tool;
    private Fragment_A fragment_a;
    private Fragment_B fragment_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFragment();
    }

    private void initFragment() {

        fragment_a = new Fragment_A();
        fragment_b = new Fragment_B();
        // 获得管理
        FragmentManager manager = getSupportFragmentManager();
        //开启事物
        FragmentTransaction transaction = manager.beginTransaction();
        // 添加  fragment
        transaction.add(R.id.frame,fragment_a);
        transaction.add(R.id.frame,fragment_b);
        //  设置显示隐藏
        transaction.show(fragment_a).hide(fragment_b);
        // 提交事务
        transaction.commit();
    }

    private void initView() {

        tool = findViewById(R.id.tool);
        tool.setTitle("标题");
        setSupportActionBar(tool);

        TabLayout tab = findViewById(R.id.tab);

        tab.addTab(tab.newTab().setText("首页1"));
        tab.addTab(tab.newTab().setText("首页2"));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();
                Log.i("tag","===> "+position);
                if(position==0){
                    // 首页1
                    getSupportFragmentManager().beginTransaction().show(fragment_a).hide(fragment_b).commit();
                }else{
                    // 首页2
                    getSupportFragmentManager().beginTransaction().show(fragment_b).hide(fragment_a).commit();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public Fragment_A getFragment_a() {
        return fragment_a;
    }

    public Fragment_B getFragment_b() {
        return fragment_b;
    }
}
