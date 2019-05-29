    <!-- 布局文件  -->
	
	
<LinearLayout
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   android:orientation="vertical"
   xmlns:android="http://schemas.android.com/apk/res/android">
    

    <Button 
        android:id="@+id/anniu"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="按钮"/>
    
</LinearLayout>




   <!-- java文件  -->


package com.example.lianxi1;

//Log 就是程序运行中的过程,全部记录下来,方便后续程序的维护和升级,并附带提交bug分析bug作用
//    以后编写的Android程序不能再打印到控制台了,而程序运行调试需要使用LogCat

//Android中的Log分为5个等级
// i 应用所有步骤都会记录并存入日志文件                               绿色
// v 
// e 错误等级,即程序运行过程中出现的error与运行错误         红色
// w warning 警告等级								   黄色
// d debug等级									   紫色

// Toast 吐司  提示信息
// context,	上下文 
// text, 信息
// duration 持续时间

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        //通过指定的方法获得布局文件的id对象
        Button bt = (Button) findViewById(R.id.anniu);
        
        //监听对象,点击事件
        bt.setOnClickListener(new OnClickListener() {
			
			//设置按钮的点击事件
			public void onClick(View arg0) {
				
				Log.i("message", "点击成功");
				
				Toast.makeText(MainActivity.this, "提示信息", Toast.LENGTH_SHORT).show();

			}
		});
    
    }


}