package com.example.myfilemvpdomo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.myfilemvpdomo.adapter.MainPageAdapter;
import com.example.myfilemvpdomo.fram.DownFileFragment;
import com.example.myfilemvpdomo.fram.HomeFragment;
import com.example.myfilemvpdomo.fram.UploadFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar tol;
    private ViewPager vp;
    private TabLayout tab;
    private MainPageAdapter adapter;

    //苏克阳   2019/4/23
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        tol = (Toolbar) findViewById(R.id.tol);
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        tol.setTitle("首页");
        setSupportActionBar(tol);

        tab.addTab(tab.newTab().setText("首页").setIcon(R.mipmap.ic_launcher));
        tab.addTab(tab.newTab().setText("上传").setIcon(R.mipmap.ic_launcher));
        tab.addTab(tab.newTab().setText("下载").setIcon(R.mipmap.ic_launcher));

        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new UploadFragment());
        list.add(new DownFileFragment());
        adapter = new MainPageAdapter(getSupportFragmentManager(), list);
        vp.setAdapter(adapter);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                vp.setCurrentItem(position);
                switch (position) {
                    case 0:
                        tol.setTitle("首页");
                        break;
                        case 1:
                        tol.setTitle("上传");
                        break;
                        case 2:
                        tol.setTitle("下载");
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
    }
}
