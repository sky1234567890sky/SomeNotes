package com.jiyun.demo2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ABCDFragment.OnFragmentInteractionListener, RadioGroup.OnCheckedChangeListener {

    private TextView title;
    private RadioGroup rg;
    private ABCDFragment aFragment;
    private ABCDFragment bFragment;
    private ABCDFragment cFragment;
    private ABCDFragment dFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        rg = (RadioGroup) findViewById(R.id.rg);

        rg.setOnCheckedChangeListener(this);
        rg.check(R.id.rb1);
    }

    /**
     * 接口回调
     *
     * @param uri
     */
    @Override
    public void onFragmentInteraction(String uri) {
        Toast.makeText(this, uri, Toast.LENGTH_SHORT).show();
    }

    /**
     * 单选按钮组监听
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (aFragment!=null){
            fragmentTransaction.hide(aFragment);
        }
        if (bFragment!=null){
            fragmentTransaction.hide(bFragment);
        }
        if (cFragment!=null){
            fragmentTransaction.hide(cFragment);
        }
        if (dFragment!=null){
            fragmentTransaction.hide(dFragment);
        }
        switch (checkedId) {
            case R.id.rb1:
                if (aFragment == null){
                    aFragment = ABCDFragment.newInstance("首页","20");
                    fragmentTransaction.add(R.id.content,aFragment);
                }else{
                    fragmentTransaction.show(aFragment);
                }
                title.setText("首页");
                break;
            case R.id.rb2:
                if (bFragment == null){
                    bFragment = ABCDFragment.newInstance("发现","30");
                    fragmentTransaction.add(R.id.content,bFragment);
                }else{
                    fragmentTransaction.show(bFragment);
                }
                title.setText("发现");
                break;
            case R.id.rb3:
                if (cFragment == null){
                    cFragment = ABCDFragment.newInstance("消息","40");
                    fragmentTransaction.add(R.id.content,cFragment);
                }else{
                    fragmentTransaction.show(cFragment);
                }
                title.setText("消息");
                break;
            case R.id.rb4:
                if (dFragment == null){
                    dFragment = ABCDFragment.newInstance("我的","50");
                    fragmentTransaction.add(R.id.content,dFragment);
                }else{
                    fragmentTransaction.show(dFragment);
                }
                title.setText("我的");
                break;
        }
        fragmentTransaction.commit();
    }
}
