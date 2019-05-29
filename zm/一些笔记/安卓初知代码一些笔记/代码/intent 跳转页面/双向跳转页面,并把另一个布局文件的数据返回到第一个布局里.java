

// 布局文件 (Main布局)

 <!-- 布局文件 -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingLeft="20dp"
    android:paddingRight="20dp" >


	<TextView 
	    android:id="@+id/tv"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="@string/hello_world"/>
	
	<Button 
	    android:id="@+id/bt"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="跳转"/>
	    

    
</LinearLayout>


// 布局文件 (Second 次布局)

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.lianxi1.SecondActivity" >

    <EditText 
        android:id="@+id/et_2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:hint="请输入给上一个页面的数据"/>

    <Button 
        android:id="@+id/bt_2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/et_2"
        android:layout_alignRight="@id/et_2"
        android:text="跳转"/>

</RelativeLayout>


// java文件 (主文件 MainActivity)

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//由此页面跳转到SecondActivity,在SecondActivity中输入数据,并返回此Activity
		//在此界面把Second中的数据显示到界面
		
		
		//监听按钮,调到second
		Button bt = (Button) findViewById(R.id.bt);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//跳到第二个页面
				//意图
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				//启动第二个页面
				//startActivity(intent)
				//需要从第二个页面返回数据
				startActivityForResult(intent, 10);
				
			}
		});
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==10 && resultCode==20) {
			//获取意图携带回来的数据
			String string = data.getStringExtra("mes");
			TextView tv = (TextView) findViewById(R.id.tv);
			tv.setText(string);
		}
	
	}

}


//java文件 (SecondActivity 另一个跳转页面的修改)

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		

		//监听按钮
		Button bt2 = (Button) findViewById(R.id.bt_2);
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				EditText et2 = (EditText) findViewById(R.id.et_2);
				String string = et2.getText().toString();
				Intent intent = new Intent();
				intent.putExtra("mes", string);
				setResult(20, intent);
				//结束此界面
				finish();
			}
		});
		
	}

}



