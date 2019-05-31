package com.jiyun.exam;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HomeFragment.ABC {

    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment> list;
    private MyPageAdapter adapter;
    private HomeFragment homeFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);

        homeFragment = new HomeFragment("我是一個按鈕");
        Bundle bundle = new Bundle();
        bundle.putString("abc","abc");
        homeFragment.setArguments(bundle);
        homeFragment.setAbc(this);

        myFragment = new MyFragment();

        list = new ArrayList<>();
        list.add(homeFragment);
        list.add(myFragment);

        tab.addTab(tab.newTab().setText("首頁").setIcon(R.drawable.selectr_home));
        tab.addTab(tab.newTab().setText("我的").setIcon(R.drawable.selectr_my));

        adapter = new MyPageAdapter(getSupportFragmentManager(),list);
        vp.setAdapter(adapter);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,1,100,"水平");
        menu.add(1,2,100,"網格");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                //vp.setCurrentItem(0);
                tab.getTabAt(0).select();
                homeFragment.setManager(new LinearLayoutManager(this));
                break;
            case 2:
                //vp.setCurrentItem(0);
                tab.getTabAt(0).select();
                homeFragment.setManager(new GridLayoutManager(this,2));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAbc(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();

        myFragment.setTEXT(str);
    }
}
