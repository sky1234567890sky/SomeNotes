package com.example.a1.geeknewschr.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.base.BaseActivity;
import com.example.a1.geeknewschr.fragment.AboutFragment;
import com.example.a1.geeknewschr.fragment.CollectionFragment;
import com.example.a1.geeknewschr.fragment.GankFragment;
import com.example.a1.geeknewschr.fragment.GoldFragment;
import com.example.a1.geeknewschr.fragment.SettingFragment;
import com.example.a1.geeknewschr.fragment.V2exFragment;
import com.example.a1.geeknewschr.fragment.WeChatFragment;
import com.example.a1.geeknewschr.fragment.ZhihuFragment;
import com.example.a1.geeknewschr.persenter.MainPersenter;
import com.example.a1.geeknewschr.view.MainView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainView, MainPersenter> implements MainView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fl)
    FrameLayout mFl;
    @BindView(R.id.nav)
    NavigationView mNav;
    @BindView(R.id.dl)
    DrawerLayout mDl;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;
    private ArrayList<Integer> mTitle;
    private ArrayList<Fragment> mFragments;
    private FragmentManager mManager;

    private int TYPE_ZHIHU = 0;
    private int TYPE_WECHAT = 1;
    private int TYPE_GANK = 2;
    private int TYPE_GOLD = 3;
    private int TYPE_V2EX = 4;
    private int TYPE_COLLECTION = 5;
    private int TYPE_SETTING = 6;
    private int TYPE_ABOUT = 7;
    private int lastFragmentPosition;
    private MenuItem mSearchItem;

    @Override
    protected MainPersenter initPersenter() {
        return new MainPersenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mManager = getSupportFragmentManager();
        mToolbar.setTitle(R.string.zhihu);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolbar, R.string.about, R.string.about);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        mDl.addDrawerListener(toggle);
        toggle.syncState();

        initFragment();
        initTitle();

        addZhihuDailyNewsFragment();
    }

    private void addZhihuDailyNewsFragment() {
//        mManager.beginTransaction().add(R.id.fl, mFragments.get(0)).commit();
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.fl, mFragments.get(0));
        transaction.commit();
    }

    private void initTitle() {
        mTitle = new ArrayList<Integer>();
        mTitle.add(R.id.zhihu);
        mTitle.add(R.id.wecChat);
        mTitle.add(R.id.gank);
        mTitle.add(R.id.gold);
        mTitle.add(R.id.V2EX);
        mTitle.add(R.id.collection);
        mTitle.add(R.id.setting);
        mTitle.add(R.id.about);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new ZhihuFragment());
        mFragments.add(new WeChatFragment());
        mFragments.add(new GankFragment());
        mFragments.add(new GoldFragment());
        mFragments.add(new V2exFragment());
        mFragments.add(new CollectionFragment());
        mFragments.add(new SettingFragment());
        mFragments.add(new AboutFragment());
    }

    @Override
    protected void initListener() {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId != R.id.title_info && itemId != R.id.title_options) {
                    item.setChecked(true);
                    switch (itemId) {
                        case R.id.zhihu:
                            switchFragment(TYPE_ZHIHU);
                            mToolbar.setTitle(R.string.zhihu);

                            break;
                        case R.id.wecChat:
                            switchFragment(TYPE_WECHAT);
                            mToolbar.setTitle(R.string.wechat);
                            break;
                        case R.id.gank:
                            switchFragment(TYPE_GANK);
                            mToolbar.setTitle(R.string.gank);
                            break;
                        case R.id.gold:
                            switchFragment(TYPE_GOLD);
                            mToolbar.setTitle(R.string.glod);

                            break;
                        case R.id.V2EX:
                            switchFragment(TYPE_V2EX);
                            mToolbar.setTitle(R.string.V2EX);

                            break;
                        case R.id.collection:
                            switchFragment(TYPE_COLLECTION);
                            mToolbar.setTitle(R.string.collection);

                            break;
                        case R.id.setting:
                            switchFragment(TYPE_SETTING);
                            mToolbar.setTitle(R.string.setting);

                            break;
                        case R.id.about:
                            switchFragment(TYPE_ABOUT);
                            mToolbar.setTitle(R.string.about);

                            break;
                    }
                    mDl.closeDrawer(Gravity.LEFT);
                } else {
                    item.setChecked(false);
                }
                return false;
            }
        });

        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
//                ToastUtil.showLong("提交内容:"+query);

                if (lastFragmentPosition == TYPE_WECHAT){
                    switchFragment(TYPE_WECHAT);
                    Toast.makeText(MainActivity.this, "提交内容:" + query, Toast.LENGTH_SHORT).show();
                }else if (lastFragmentPosition == TYPE_GANK){
                    switchFragment(TYPE_GANK);
                    Toast.makeText(MainActivity.this, "提交内容:" + query, Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                Toast.makeText(MainActivity.this, newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
//                ToastUtil.showLong("展开11111");
                Toast.makeText(MainActivity.this, "展开11111", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
//                ToastUtil.showLong("关闭222222");
                Toast.makeText(MainActivity.this, "关闭222222", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void switchFragment(int type) {
        Fragment fragment = mFragments.get(type);
        Fragment hidefragment1 = mFragments.get(lastFragmentPosition);
        FragmentTransaction beginTransaction = mManager.beginTransaction();
        if (!fragment.isAdded()) {
            beginTransaction.add(R.id.fl, fragment);
        }
        beginTransaction.hide(hidefragment1);
        beginTransaction.show(fragment);
        beginTransaction.commit();

        lastFragmentPosition = type;

        if (type == TYPE_GANK || type == TYPE_WECHAT) {
            mSearchItem.setVisible(true);
        } else {
            mSearchItem.setVisible(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.serch_menu, menu);

        mSearchItem = menu.findItem(R.id.action_search);
        //隐藏搜索框
        mSearchItem.setVisible(false);
        mSearchView.setMenuItem(mSearchItem);//搜索按钮和条目绑定

        return true;
    }

    //按下监听。
    @Override
    public void onBackPressed() {
        //先关闭软键盘，再关闭搜索框  不是直接关闭软件
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
//            case MotionEvent.action_ba
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("是否确定退出程序？")
                    .setNegativeButton("no", null)
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).create();
            dialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
