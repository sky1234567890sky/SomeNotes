package com.example.zhoutiandemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhoutiandemo.R;
import com.example.zhoutiandemo.adapter.PageAdapter;
import com.example.zhoutiandemo.fragment.DiTuFragment;
import com.example.zhoutiandemo.fragment.ShouCangFragment;
import com.example.zhoutiandemo.fragment.ShouYeFragment;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView toolbar_title;
    private Toolbar toolbar;
    private ViewPager vp;
    private TabLayout tab;
    private NavigationView navigationview;
    private DrawerLayout dl;
    private ImageView toolbar_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initView();
        //ToolBar
        initToolBar();
        //抽屉
        initNavigation();

        initData();
    }

    private void initData() {
        ArrayList<String> title = new ArrayList<>();
        title.add("首页");
        title.add("收藏");
        title.add("地图");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ShouYeFragment());
        fragments.add(new ShouCangFragment());
        fragments.add(new DiTuFragment());

        PageAdapter adapter = new PageAdapter(title, fragments, getSupportFragmentManager());
        vp.setAdapter(adapter);

        tab.setupWithViewPager(vp);
    }

    private void initNavigation() {
        //抽屉部分监听
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_shouye:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.menu_shoucang:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.menu_ditu:
                        vp.setCurrentItem(2);
                        break;
                }
                dl.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }

    private void initToolBar() {

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //toolbar上的ImageView操作
        toolbar_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dl.openDrawer(Gravity.LEFT);
            }
        });


        //toolbar切换部分
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                toolbar_title.setText(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView() {
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        navigationview = (NavigationView) findViewById(R.id.navigationview);
        dl = (DrawerLayout) findViewById(R.id.dl);
        toolbar_img = (ImageView) findViewById(R.id.toolbar_img);

        View headerView = navigationview.getHeaderView(0);
        ImageView header_img = headerView.findViewById(R.id.header_img);
        TextView header_tv = headerView.findViewById(R.id.header_tv);

        header_img.setOnClickListener(this);
        header_tv.setOnClickListener(this);

        Intent intent = getIntent();
        String url = intent.getStringExtra("img");
        String name = intent.getStringExtra("name");
        if (url != null) {
            Glide.with(Main3Activity.this).load(url).into(header_img);
            Glide.with(Main3Activity.this).load(url).into(toolbar_img);
        }
        if (name != null) {
            header_tv.setText(name);
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_img:
                //Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Main3Activity.this, ShangChuanActivity.class));
                break;
            case R.id.header_tv:
                startActivity(new Intent(Main3Activity.this, Denglu_ZhuCeActivity.class));
                break;
        }
    }
}
