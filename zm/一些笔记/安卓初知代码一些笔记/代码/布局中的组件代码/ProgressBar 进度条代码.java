//ProgressBar 动态进度条

 <!-- 布局文件 -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingLeft="20dp"
    android:paddingRight="20dp" >

    <ProgressBar 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        style="?android:attr/progressBarStyleInverse"/>
  
    <ProgressBar 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        style="?android:attr/progressBarStyleLarge"/>
    
    <ProgressBar 
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:max="100"
        android:progress="40"
        style="?android:attr/progressBarStyleHorizontal"/>
    
    <ProgressBar 
        android:id="@+id/jd"  
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        style="@android:style/Widget.ProgressBar.Horizontal"
        android:max="100"
        android:progress="40"
        android:secondaryProgress="60"
        android:indeterminate="false"/>
    
    
</LinearLayout>


// java代码

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
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//通过调用id的方法获得组件的对象
		final ProgressBar jd = (ProgressBar) findViewById(R.id.jd);
		
		//使用线程
		new Thread(){
			//使用线程的方法
			public void run() {
				// 用for循环设置进度条的进度
				// getProgress  已经缓存的进度   getMax 最大进度
				for (int i = jd.getProgress(); i < jd.getMax(); i++) {
					//循环设置当前的进度值
					jd.setProgress(i);
					
					//循环一次停止500ms
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			//开始使用线程,必须使用
		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

