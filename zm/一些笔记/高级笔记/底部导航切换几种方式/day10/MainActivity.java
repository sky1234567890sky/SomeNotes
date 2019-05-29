package com.jiyun.day10;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ABCDFragment.OnFragmentInteractionListener, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private TextView title;
    private RadioGroup rg;
    private ViewPager content;
    private ArrayList<Fragment> list;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        rg = (RadioGroup) findViewById(R.id.rg);
        content = (ViewPager) findViewById(R.id.content);

        list = new ArrayList<>();
        list.add(ABCDFragment.newInstance("首页","20"));
        list.add(ABCDFragment.newInstance("发现","30"));
        list.add(ABCDFragment.newInstance("消息","40"));
        list.add(ABCDFragment.newInstance("我的","50"));

        adapter = new MyAdapter(getSupportFragmentManager(),list);
        content.setAdapter(adapter);

        //设置监听
        content.addOnPageChangeListener(this);
        rg.setOnCheckedChangeListener(this);

        //设置默认选中
        content.setCurrentItem(0);
        rg.check(R.id.rb1);
    }

    //接口回调
    @Override
    public void onFragmentInteraction(String uri) {
        Toast.makeText(this,uri,Toast.LENGTH_SHORT).show();
    }

    /**
     * ViewPager滑动监听事件
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rg.check(R.id.rb1);
                break;
            case 1:
                rg.check(R.id.rb2);
                break;
            case 2:
                rg.check(R.id.rb3);
                break;
            case 3:
                rg.check(R.id.rb4);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 单选按钮监听事件
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb1:
                content.setCurrentItem(0);
                title.setText("首页");
                break;
            case R.id.rb2:
                content.setCurrentItem(1);
                title.setText("发现");
                break;
            case R.id.rb3:
                content.setCurrentItem(2);
                title.setText("消息");
                break;
            case R.id.rb4:
                content.setCurrentItem(3);
                title.setText("我的");
                break;
        }

    }
}
