package com.example.along.myprojectdemo;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import fragment.HomePagerFragment;
import fragment.KonwledgeFragment;
import fragment.NavgationFragment;
import fragment.ProjectFragment;
import fragment.WxFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private LinearLayout ll;
    private NavigationView nav;
    private HomePagerFragment homePagerFragment;
    private KonwledgeFragment konwledgeFragment;
    private NavgationFragment navgationFragment;
    private ProjectFragment projectFragment;
    private WxFragment wxFragment;
    private TextView toolbar_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       initView();
       initFragment();

    }

    //初始化Fragment
    private void initFragment() {

        homePagerFragment = new HomePagerFragment();
        konwledgeFragment = new KonwledgeFragment();
        navgationFragment = new NavgationFragment();
        projectFragment = new ProjectFragment();
        wxFragment = new WxFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame,homePagerFragment);
        transaction.add(R.id.frame,konwledgeFragment);
        transaction.add(R.id.frame,navgationFragment);
        transaction.add(R.id.frame,projectFragment);
        transaction.add(R.id.frame,wxFragment);

        transaction.show(homePagerFragment)
                .hide(konwledgeFragment)
                .hide(navgationFragment)
                .hide(projectFragment)
                .hide(wxFragment).commit();

    }

    private void initView() {

        toolbar_tv = findViewById(R.id.toolbar_tv);
        // 默认第一个选中
        RadioButton rb_home = findViewById(R.id.rb_home);
        rb_home.setChecked(true);
        toolbar_tv.setText(R.string.home);

        RadioGroup rg = findViewById(R.id.rg);

        rg.setOnCheckedChangeListener(this);

        DrawerLayout dl = findViewById(R.id.dl);
        nav = findViewById(R.id.nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ll = findViewById(R.id.ll);

        //解决侧滑菜单图片不显示问题
        nav.setItemIconTintList(null);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.open, R.string.closs);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        //侧滑菜单的监听
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                return false;
            }
        });

        //监听侧滑打开关闭
        dl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                ll.setX(nav.getWidth()*slideOffset);

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){

            case R.id.rb_home:

                toolbar_tv.setText(R.string.home);
                getSupportFragmentManager().beginTransaction().show(homePagerFragment)
                        .hide(konwledgeFragment)
                        .hide(navgationFragment)
                        .hide(projectFragment)
                        .hide(wxFragment).commit();
                break;

            case R.id.rb_knowledge:

                toolbar_tv.setText("Knowledge");
                getSupportFragmentManager().beginTransaction().show(konwledgeFragment)
                        .hide(homePagerFragment)
                        .hide(navgationFragment)
                        .hide(projectFragment)
                        .hide(wxFragment).commit();

                break;

            case R.id.rb_wx:
                toolbar_tv.setText("Wx");
                getSupportFragmentManager().beginTransaction().show(wxFragment)
                        .hide(homePagerFragment)
                        .hide(navgationFragment)
                        .hide(projectFragment)
                        .hide(konwledgeFragment).commit();
                break;
            case R.id.rb_nav:
                toolbar_tv.setText("Navigation");
                getSupportFragmentManager().beginTransaction().show(navgationFragment)
                        .hide(homePagerFragment)
                        .hide(konwledgeFragment)
                        .hide(projectFragment)
                        .hide(wxFragment).commit();
                break;

            case R.id.rb_project:
                toolbar_tv.setText("Project");
                getSupportFragmentManager().beginTransaction().show(projectFragment)
                        .hide(homePagerFragment)
                        .hide(navgationFragment)
                        .hide(konwledgeFragment)
                        .hide(wxFragment).commit();
                break;


        }
    }
}
