页面跳转并携带数据返回添加到ListView中

// 布局文件 (Main 第一个页面的布局)

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
	    ></ListView>
	    

    
</LinearLayout>

// 布局文件 (Second 第二个页面的布局,用于添加数据的)

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
        android:id="@+id/et"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:hint="请输入要添加到ListView中的数据"/>

    <Button 
        android:id="@+id/bt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/et"
        android:layout_alignRight="@id/et"
        android:text="添加"/>

</RelativeLayout>


// 条目文件 (item 设置字体大小的,一开始添加的数据)

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

        <TextView 
        android:id="@+id/tv"
        android:layout_width="match_parent"
    	android:layout_height="match_parent"
    	android:textSize="35sp"
        />

</RelativeLayout>


// java文件  (Main  第一个布局修改的)

public class MainActivity extends Activity {

	private ArrayList<String> list;
	private MyAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		//显示一个ListView 如果长按点击,则跳转到第二个页面
		//第二个页面,有一个EditText,输入数据以后,点击按钮添加
		//自动把EditText中的字符串添加的第一个页面的ListView中
		
		
		
		//创建ListView中要显示的数据
		ArrayList<String> list = new ArrayList<String>();
		list.add("孙悟空");
		list.add("白骨精");
		list.add("猪悟能");
		list.add("沙和尚");
		list.add("白龙马");
		list.add("唐僧");
		//创建自定义Adapter对象
		adapter = new MyAdapter(MainActivity.this, R.layout.item, list);
		
		//获取ListView对象
		ListView lv = (ListView) findViewById(R.id.lv);
		
		//把适配器添加进入ListView
		lv.setAdapter(adapter);
		
		//长监听跳转到第二个页面,并请求数据
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// 跳转到第二个页面,请求数据
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				startActivityForResult(intent, 10);
				return false;
			}
		});
		
		
	}
	
	//重写方法,接收数据
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==10 && resultCode==20) {
			//获取意图携带回来的数据
			String string = data.getStringExtra("mes");
			//把此数据添加进ListView中
			adapter.add(string);
		}
	
	}

}


// java文件 (MyAdapter 适配器)

public class MyAdapter extends ArrayAdapter<String> {

	public MyAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		//定义Holder类
		Holder holder = null;
		//如果该数据为空
		if (convertView == null) {'
			//获取指定布局的对象,item
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent,false);
			
			holder = new Holder();
			//在指定布局中获取指定的组件对象
			holder.tv = (TextView) convertView.findViewById(R.id.tv);
			//给该布局赋值
			convertView.setTag(holder);
		}else {
			//给布局获取值 赋值 给holder类中
			holder = (Holder) convertView.getTag();
		}
		
		//获得条目显示的数据
		String item = getItem(position);
		//给该组件设置数据
		holder.tv.setText(item);
		
		//反回会布局
		return convertView;

	}
	//自定义类,用于优化数据,不重复使用item布局中的TextView类
	class Holder{
		TextView tv;
	}
	
}


// java文件 (SecondActivity  第二个布局修改)

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		//监听按钮,并把EditText中的内容返回给第一个页面
		Button bt = (Button) findViewById(R.id.bt);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//获取EditText中的数据
				EditText et = (EditText) findViewById(R.id.et);
				String string = et.getText().toString();
				//创建携带数据的意图
				Intent intent = new Intent();
				intent.putExtra("mes", string);
				setResult(20, intent);
				
				//结束此页面跳转到上一个页面
				finish();
				
			}
		});
		
		
	}


}




