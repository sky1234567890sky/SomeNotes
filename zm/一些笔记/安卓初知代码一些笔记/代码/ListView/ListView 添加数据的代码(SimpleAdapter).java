// ListView文件

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
	    android:layout_height="wrap_content"
	    />
	    

    
</LinearLayout>


// 布局文件 (item 条目)

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >
    

    <ImageView 
        android:id="@+id/iv1"
        android:layout_width="70dp"
        android:layout_height="70dp"
        android:src="@drawable/cache_clear"/>
    
    <TextView
        android:id="@+id/tv1" 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="这是第一个文本"
        android:textSize="30dp"
        android:layout_toRightOf="@id/iv1"
        />
    
    <TextView
        android:id="@+id/tv2" 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="这是第二个文本"
        android:textSize="30dp"
        android:layout_toRightOf="@id/iv1"
        android:layout_alignBottom="@id/iv1"
        />
    
</RelativeLayout>


//java文件

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//获取ListView对象
		ListView lv = (ListView) findViewById(R.id.lv);
		
		//创建在ListView找那个显示的数据
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("src", R.drawable.cache_clear);
		map1.put("text1", "这是第一个文本");
		map1.put("text2", "我叫郭磊");
		list.add(map1);
		
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("src", R.drawable.cache_clear);
		map2.put("text1", "这是第一个文本");
		map2.put("text2", "我叫石佳");
		list.add(map2);
		
		HashMap<String, Object> map3 = new HashMap<String, Object>();
		map3.put("src", R.drawable.cache_clear);
		map3.put("text1", "这是第一个文本");
		map3.put("text2", "我叫张娜娜");		
		list.add(map3);
		
		
		String [] from = {"src","text1","text2"};
		int [] to = {R.id.lv,R.id.tv1,R.id.tv2};
		
		//创建适配器对象
		
		//context,	上下文 
		//data,	使用的数据
		//resource, 使用的布局文件
		//from,	集合键值的数组集
		//to 与键值对应的位置
		SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, list, R.layout.item, from, to);
		
		
		//把适配器添加进入ListView对象中
		lv.setAdapter(adapter);
		
	}


}



