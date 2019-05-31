package com.rongbao.jyjy.rongbao.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.rongbao.jyjy.rongbao.R;
import com.rongbao.jyjy.rongbao.apiservice.Constants;
import com.rongbao.jyjy.rongbao.apiservice.Content;
import com.rongbao.jyjy.rongbao.base.BaseActivity;
import com.rongbao.jyjy.rongbao.base.BaseApp;
import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.base.BaseView;
import com.rongbao.jyjy.rongbao.bean.MyDB_Login_Bean;
import com.rongbao.jyjy.rongbao.db.MyDB_Login_BeanDao;
import com.rongbao.jyjy.rongbao.fragment.ComPass_Fragment;
import com.rongbao.jyjy.rongbao.fragment.Map_Fragment;
import com.rongbao.jyjy.rongbao.fragment.Ring_spack_Fragment;
import com.rongbao.jyjy.rongbao.fragment.Video_Fragment;
import com.rongbao.jyjy.rongbao.present.Empty_Present;
import com.rongbao.jyjy.rongbao.util.SpUtil;
import com.rongbao.jyjy.rongbao.util.ToastUtil;
import com.rongbao.jyjy.rongbao.util.UIModeUtil;
import com.rongbao.jyjy.rongbao.view.Empty_View;
import com.rongbao.jyjy.rongbao.view.fragment.Compass_View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MainActivity extends BaseActivity<BasePresent, BaseView> implements Empty_View {
    private static final String TAG = "MainActivity";
    @BindView(R.id.main_toolbar)
    Toolbar mMainToolbar;
    @BindView(R.id.main_toolbar_image)
    ImageView mMainToolbarImage;
    @BindView(R.id.main_toolbar_title)
    TextView mMainToolbarTitle;
    @BindView(R.id.main_na)
    NavigationView mMainNa;
    @BindView(R.id.main_dl)
    DrawerLayout mMainDl;
    @BindView(R.id.main_tab)
    TabLayout mMainTab;
    @BindView(R.id.main_container)
    FrameLayout mMainContainer;
    private String umeng_name;
    private String header;
    private String username;
    private int itemId;
    private int position1;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<Integer> titles = new ArrayList<>();
    private MyDB_Login_BeanDao myDB_login_beanDao;
    private TextView friends_sure;
    private TextView friends_cancel;
    private PopupWindow popupWindow;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresent getPresent() {
        return new Empty_Present();
    }


    @Override
    protected void initview() {
        initFragment();
        mMainToolbar.setTitle("");
        setSupportActionBar(mMainToolbar);
        myDB_login_beanDao = BaseApp.getInstance().getDaoSession().getMyDB_Login_BeanDao();
        List<MyDB_Login_Bean> myDB_login_beans = myDB_login_beanDao.loadAll();    //查出数据库中的值  从数具库中找出name
        for (MyDB_Login_Bean myDB_login_bean : myDB_login_beans) {
            username = myDB_login_bean.getUsername();   //数据库的值
            Log.d(TAG, "initview: " + username);
        }
        Intent intent = getIntent();     //友盟传过来的name   和header
        umeng_name = intent.getStringExtra(Content.UMENG_NAEM);
        header = intent.getStringExtra(Content.UMENG_HEADER);

        if (TextUtils.isEmpty(umeng_name)) {
            mMainToolbarTitle.setText(username);
            Glide.with(MainActivity.this).load(R.drawable.qqpander).bitmapTransform(new CropCircleTransformation(this)).placeholder(R.drawable.black_background).into(mMainToolbarImage);
        } else {
            mMainNa.setCheckedItem(R.id.ring_spack_item2);
            mMainToolbarTitle.setText(umeng_name);
            Glide.with(MainActivity.this).load(header).bitmapTransform(new CropCircleTransformation(this)).placeholder(R.drawable.black_background).into(mMainToolbarImage);
        }
        ShowDefaultFragment();
        initTabListener();


    }

    private void initTabListener() {
        if (umeng_name == null) {
            mMainTab.addTab(mMainTab.newTab().setText(titles.get(0)));
            mMainTab.addTab(mMainTab.newTab().setText(titles.get(1)));
            mMainTab.addTab(mMainTab.newTab().setText(titles.get(2)));
            mMainTab.addTab(mMainTab.newTab().setText(titles.get(3)));
        } else {
            mMainTab.addTab(mMainTab.newTab().setText(titles.get(0)));
            mMainTab.addTab(mMainTab.newTab().setText(titles.get(1)));
            mMainTab.addTab(mMainTab.newTab().setText(titles.get(2)));
        }
        mMainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                SwitchFragment(position);
                position1 = position;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void SwitchFragment(int position) {
        mMainToolbarTitle.setText(titles.get(position));
        mMainTab.getTabAt(position).select();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = fragments.get(position);
        if (!fragment.isAdded()) {
            transaction.add(R.id.main_container, fragment);
        }
        transaction.hide(fragments.get(position1));
        transaction.show(fragment);
        transaction.commit();
    }

    private void ShowDefaultFragment() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_container, fragments.get(0));
        transaction.commit();
    }

    @Override
    protected void initother() {
        View headerView = mMainNa.getHeaderView(0);
        ImageView ring_spack_image = headerView.findViewById(R.id.ring_spack_head_image);
        TextView ring_spack_name = headerView.findViewById(R.id.ring_spack_head_name);
        if (TextUtils.isEmpty(umeng_name)) {
            ring_spack_name.setText(username);
            Glide.with(MainActivity.this).load(R.drawable.qqpander).bitmapTransform(new CropCircleTransformation(this)).placeholder(R.drawable.black_background).into(ring_spack_image);
        } else {
            ring_spack_name.setText(umeng_name);
            Glide.with(MainActivity.this).load(header).bitmapTransform(new CropCircleTransformation(this)).placeholder(R.drawable.black_background).into(ring_spack_image);
        }

        mMainNa.setItemIconTintList(null);  //结局menu图片加载不出问题
    }

    @Override
    protected void initClick() {
        // 点击头像  打开抽屉
        mMainToolbarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainDl.openDrawer(Gravity.LEFT);
            }
        });

        mMainNa.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                itemId = item.getItemId();
                item.setChecked(true);   //添加可以选择
                if (TextUtils.isEmpty(umeng_name)) {
                    if (itemId == R.id.ring_spack_item1) {
                        FragmentTransaction transaction = manager.beginTransaction();
                        Fragment fragment = fragments.get(3);
                        mMainToolbarTitle.setText(R.string.ring_spack);
                        mMainTab.getTabAt(3).select();
                        transaction.hide(fragments.get(position1));
                        transaction.show(fragment);
                        transaction.commit();
                    }
                } else if (itemId == R.id.ring_spack_item1) {
                    ToastUtil.ShowToast("没有该权限");
                    item.setChecked(false);
                }
                getRing_spak();
                return false;
            }
        });
    }


    private void getRing_spak() {
        switch (itemId) {
            case R.id.ring_spack_item2:
                SwitchFragment(0);
                break;
            case R.id.ring_spack_item3:
               startActivity(new Intent(MainActivity.this,BackActivity.class));
                break;
            case R.id.ring_spack_item4:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                UIModeUtil.changeModeUI(MainActivity.this);
                    }
                });

                break;
            case R.id.ring_spack_item5:
               startActivity(new Intent(MainActivity.this,SettingActivity.class));
                break;
            case R.id.ring_spack_item6:
                EMClient.getInstance().logout(true, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        // TODO Auto-generated method stub
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.ShowToast("退出登录成功");
                            }
                        });
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }

                    @Override
                    public void onProgress(int progress, String status) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.ShowToast("退出登录失败");
                            }
                        });
                    }
                });
                break;
        }
        mMainDl.closeDrawer(Gravity.LEFT);
    }

    private void initFragment() {
        if (TextUtils.isEmpty(umeng_name)) {   //当友盟的值为空的时候   有聊天
            fragments.add(new Video_Fragment());
            fragments.add(new Map_Fragment());
            fragments.add(new ComPass_Fragment());
            fragments.add(new Ring_spack_Fragment());
            titles.add(R.string.short_video);
            titles.add(R.string.map);
            titles.add(R.string.compass);
            titles.add(R.string.linkman);
        } else {
            fragments.add(new Video_Fragment());
            fragments.add(new Map_Fragment());
            fragments.add(new ComPass_Fragment());
            titles = new ArrayList<>();
            titles.add(R.string.short_video);
            titles.add(R.string.map);
            titles.add(R.string.compass);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(2, 2, 0, "呼叫联系人");
        menu.add(2, 3, 0, "紧急联系人");
        menu.add(2, 4, 0, "添加好友");
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("MissingPermission")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 2:
                String str = null;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri parse = Uri.parse("tel:" + str);
                intent.setData(parse);
                startActivity(intent);
                break;
            case 3:
                Intent intent1 = new Intent(Intent.ACTION_CALL);
                Uri parse1 = Uri.parse("tel:" + "17735703907");
                intent1.setData(parse1);
                startActivity(intent1);
                break;
            case 4:
                final View inflate = View.inflate(MainActivity.this, R.layout.friends, null);
                friends_sure = inflate.findViewById(R.id.friends_sure);
                friends_cancel = inflate.findViewById(R.id.friends_cancel);

                popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.showAtLocation(mMainContainer, Gravity.CENTER, 0, 0);
                friends_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //同意好友请求
                                try {
                                    EditText fridens_et = inflate.findViewById(R.id.fridens_et);
                                    final   String et_name = fridens_et.getText().toString().trim();
                                    //参数为要添加的好友的username和添加理由
                                    Log.d(TAG, "要添加的功能1: "+et_name);
                                    EMClient.getInstance().contactManager().addContact(et_name,"你好我是"+username);
                                    EMClient.getInstance().contactManager().acceptInvitation(et_name);
                                    Log.d(TAG, "要添加的功能2: "+et_name);
                                    popupWindow.dismiss();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                });

                getcancel();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getcancel() {
        friends_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }


}
