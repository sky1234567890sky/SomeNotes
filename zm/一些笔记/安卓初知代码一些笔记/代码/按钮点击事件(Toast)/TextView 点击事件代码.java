    // 布局文件 
	
<LinearLayout
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   android:orientation="vertical"
   xmlns:android="http://schemas.android.com/apk/res/android">
    

    <TextView 
        android:id="@+id/tv1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="这是一个文本"/>
    
</LinearLayout>

	//java代码
	
package com.example.lianxi1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   
        //获取id对象
        final TextView tv = (TextView) findViewById(R.id.tv1);
        //通过对象设置属性
        tv.setTextSize(50);
        
        //监听对象
        tv.setOnClickListener(new OnClickListener() {
			
        	
			@Override
			//使用监听的方法
			public void onClick(View arg0) {
				//通过点击布局获取log信息
				Log.i("message", "点击成功");
				//点击事件
				Toast.makeText(MainActivity.this, "提示信息", Toast.LENGTH_SHORT).show();
			}
		});
    
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
	
	