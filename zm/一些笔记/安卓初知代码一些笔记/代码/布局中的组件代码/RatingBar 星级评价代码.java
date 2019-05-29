RatingBar �Ǽ�����

// xml�ļ� (activity_main.xml)

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



// java�ļ� (MainActivity.java)

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

		//ˮƽ������ͨ��id��ȡ����
		final ProgressBar  pb = (ProgressBar) findViewById(R.id.pb);
		
		//ʹ���߳�
		new Thread(){
			//���÷���
			public void run() {
				//ѭ�����ý���ֵ
			for (int i = pb.getProgress(); i <= pb.getMax(); i++) {
				pb.setProgress(i);
				try {
					//ÿ��ֹͣ��ʱ��Ϊ500ms
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
				
			};
			//��ʼ�߳�
		}.start();
		

		//ͨ��id��ȡ����
		final SeekBar sb = (SeekBar) findViewById(R.id.sb);
		final ImageView iv = (ImageView) findViewById(R.id.iv);
		//����SeekBar�Ķ�
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				//ֹͣ�϶�
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				//ֹͣ�϶�
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				// TODO Auto-generated method stub
				//��ֵ�ı䶯
			
				//��ȡ����ֵ
				int progress2 = sb.getProgress();
				//����ͼƬģ��ֵ
				iv.setImageAlpha(progress2);
			}
		});
		
		
		final RatingBar rb = (RatingBar) findViewById(R.id.rb);
		
		rb.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				// TODO Auto-generated method stub
				
				Toast.makeText(MainActivity.this, "����������:"+(int) (rating*1)+"��", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		
		
	}

}
