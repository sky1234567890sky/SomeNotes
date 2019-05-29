// SeekBar �϶���


 <!-- �����ļ� -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingLeft="20dp"
    android:paddingRight="20dp" >


    <ImageView 
        android:id="@+id/tv"
        android:layout_width="150dp"
        android:layout_height="150dp"
        android:layout_gravity="center"
        android:scaleType="fitStart"
        android:src="@drawable/cache_clear"/>
    
    
    <SeekBar 
        android:id="@+id/td"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:max="200"
        android:progress="30"
        android:secondaryProgress="50"/>
    
</LinearLayout>


// java����

package com.example.lianxi1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//ͨ������id�ķ������id�Ķ���
		final SeekBar td = (SeekBar) findViewById(R.id.td);
		final ImageView tv = (ImageView) findViewById(R.id.tv);
	
		//ʹ�ü���,(�Ķ�)
		td.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// �϶�ֹͣ�ĸĶ�
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// �϶����еĸĶ�
				
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// ��ֵ�ĸĶ�(�ֶ���)
				
				//��ȡ��ǰ�϶����Ľ���ֵ
				int progress = td.getProgress();
				
				//����ͼƬ�ļ���ģ����
				tv.setImageAlpha(progress);
				
			}
		});
	
	}

}