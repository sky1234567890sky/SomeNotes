package com.jy.day11popupwindow;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 弹出popupwindow
     */
    private Button mShowPop;
    private PopupWindow pw;
    /**
     * 关闭popupwindow
     */
    private Button mClosePop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initPop();

    }

    private void initPop() {
        //加载pop的布局
        View view = View.inflate(this, R.layout.pop_layout, null);
        pw = new PopupWindow(view, 200, 400);
        //pw的宽和高和屏幕一样
//        pw = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //点击pw关闭自己
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });
        //设置在pw外部点击关闭pw
        pw.setBackgroundDrawable(new ColorDrawable());
        pw.setOutsideTouchable(true);

        //添加动画样式
        pw.setAnimationStyle(R.style.pwAnim);


    }

    private void initView() {
        mShowPop = (Button) findViewById(R.id.showPop);
        mShowPop.setOnClickListener(this);
        mClosePop = (Button) findViewById(R.id.closePop);
        mClosePop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.showPop:
                //第一种显示  在button下显示，无偏移
//                pw.showAsDropDown(mShowPop);
                //第二种  在button下显示，有偏移
//                pw.showAsDropDown(mShowPop,100,-100);
                //第三种  在屏幕中显示，有偏移
                pw.showAtLocation(mShowPop, Gravity.CENTER, 200, 200);
                break;
            case R.id.closePop:
                pw.dismiss();//关闭pw
                break;
        }
    }
}
