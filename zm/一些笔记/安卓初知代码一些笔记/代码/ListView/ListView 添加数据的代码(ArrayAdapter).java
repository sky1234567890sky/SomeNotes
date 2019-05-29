// ListView 布局文件

 <!-- 布局文件 -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingLeft="20dp"
    android:paddingRight="20dp" >

	<ListView 
	    android:id="@+id/lv"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content">
	    
	</ListView>

    
</LinearLayout>


// TextView 布局文件

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >
    
	<TextView 
	    android:id="@+id/tv"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:textColor="#ff0000"
	    android:background="@drawable/cache_clear"
	    android:textSize="25sp"
	    android:text="这是一个文本"/>
    
    
</LinearLayout>


// java文件

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		//获取ListView对象 和  TextView对象
		ListView lv = (ListView) findViewById(R.id.lv);
		TextView tv = (TextView) findViewById(R.id.tv);
		
		//创建要放入ListView中的显示的数据
		String [] s = {"郭磊","石佳","张娜娜"};
		
		//创建ListView适配器ListViewAdapter(是一个接口)
		//context, 上下文
		//resource, 使用的xml文件
		//textViewResourceId, 使用的组件
		//objects	存入的数据
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.text, R.id.tv, s);
		
		lv.setAdapter(adapter);
		
		
		
	}

}


