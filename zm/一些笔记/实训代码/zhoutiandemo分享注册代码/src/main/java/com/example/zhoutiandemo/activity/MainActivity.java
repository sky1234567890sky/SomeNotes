package com.example.zhoutiandemo.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhoutiandemo.R;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private ImageView img;
    private TextView time;
    private int s = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        Combo();

    }

    private void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (s == 1) {
                    startActivity(new Intent(MainActivity.this, Main2Activity.class));
                    finish();
                } else {
                    s--;
                    time.setText(s + "");
                    initData();
                }
            }
        }, 1000);
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        time = (TextView) findViewById(R.id.time);
    }

    public void Combo() {
        PropertyValuesHolder alpha1 = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
        PropertyValuesHolder scaleX1 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);
        PropertyValuesHolder rotation1 = PropertyValuesHolder.ofFloat("rotation", 0.0f, 360f);
        PropertyValuesHolder translationX1 = PropertyValuesHolder.ofFloat("translationX",200f);
        PropertyValuesHolder translationY1 = PropertyValuesHolder.ofFloat("translationY",200f);
        PropertyValuesHolder translationX2 = PropertyValuesHolder.ofFloat("translationX",0f);
        PropertyValuesHolder translationY2 = PropertyValuesHolder.ofFloat("translationY",0f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(img, alpha1, scaleX1, scaleY, rotation1, translationX1, translationY1);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(img, alpha1, scaleX1, scaleY, rotation1, translationX2, translationY2);
        //可以直接执行,不过不能拼接动画，这是组合动画
        //ObjectAnimator.ofPropertyValuesHolder(mimg, alpha1, scaleX1, scaleY, rotation1, translationX1, translationY1).setDuration(3000).start();
        //实例化AnimatorSet
        AnimatorSet animatorSet = new AnimatorSet();
        //使用play方法把两个动画拼接起来
        animatorSet.play(objectAnimator1).after(objectAnimator);
        //时间
        animatorSet.setDuration(5000);
        //开始执行
        animatorSet.start();

    }

}
