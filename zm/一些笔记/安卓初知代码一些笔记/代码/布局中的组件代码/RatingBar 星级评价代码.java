RatingBar 星级评价

// xml文件 (activity_main.xml)

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.demo1.MainActivity" 
    android:orientation="vertical">


    <ProgressBar 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        style="@android:style/Widget.ProgressBar"
        />
    
    <ProgressBar 
        android:id="@+id/pb"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        style="@android:style/Widget.ProgressBar.Horizontal"
       	android:max="100"
       	android:progress="30"
       	android:secondaryProgress="50"
        />
    
    
    
    <ImageView 
        android:id="@+id/iv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/weixin"
        android:layout_gravity="center"/>
    
    <SeekBar 
        android:id="@+id/sb"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:max="200"
        android:progress="30"
        android:secondaryProgress="60"/>
    
    <RatingBar 
        android:id="@+id/rb"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:numStars="5"
        android:rating="2"
        android:stepSize="1"
        />
    
    
    
</LinearLayout>



// java文件 (MainActivity.java)

package com.example.demo1;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//水平进度条通过id获取对象
		final ProgressBar  pb = (ProgressBar) findViewById(R.id.pb);
		
		//使用线程
		new Thread(){
			//调用方法
			public void run() {
				//循环设置进度值
			for (int i = pb.getProgress(); i <= pb.getMax(); i++) {
				pb.setProgress(i);
				try {
					//每次停止的时间为500ms
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
				
			};
			//开始线程
		}.start();
		

		//通过id获取对象
		final SeekBar sb = (SeekBar) findViewById(R.id.sb);
		final ImageView iv = (ImageView) findViewById(R.id.iv);
		//监听SeekBar改动
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				//停止拖动
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				//停止拖动
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				// TODO Auto-generated method stub
				//数值的变动
			
				//获取进度值
				int progress2 = sb.getProgress();
				//设置图片模糊值
				iv.setImageAlpha(progress2);
			}
		});
		
		
		final RatingBar rb = (RatingBar) findViewById(R.id.rb);
		
		rb.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				// TODO Auto-generated method stub
				
				Toast.makeText(MainActivity.this, "您的评价是:"+(int) (rating*1)+"星", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		
		
	}

}
