步骤:
1.在xml文件中定义ListView标签
2.创建一个布局item,布局中创建ListView中要显示的条目布局
3.创建一个类继承BaseAdapter,并实现抽象类中的四个抽象方法
	并把要在ListView中显示的数据传递给这个类对象
	BaseAdapter中的抽象方法
		public int getCount()	//此方法返回决定了ListView创建了多少个条目
		public Object getItem(int arg0)	//根据索引返回需要在指定位置显示的数据对象
		public Long getItemId(int arg0)	//此方法 根据索引返回id
		
		//每绘制一次条目就会调用一次这个方法,在此方法中指定绘制条目的样式,
		//并把要显示的相应数据添加到对应的条目控件中
		//postion 表示下一次将要绘制第几个条目
		//contextView 默认值为null,我们给它重新赋值,用来指定条目样式
		//并获取条目中所有控件,给响应属性赋值		
		public View getView(int position,View contextView,ViewGroup parent)
4.创建自定义Adapter对象
5.把自定义Adapter对象添加进入ListView对象中

//ListView 布局文件

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


//item 布局文件(条目)

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >
    
	<TextView 
	    android:id="@+id/tv"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:textSize="50sp"
	    android:text="dafdsl"
	    />
	
</LinearLayout>


//java文件 适配器(MyAdapter)

public class MyAdapter extends BaseAdapter {

/*重写的方法
   getCount（）返回ListView列表的长度
   getItem(int position)返回每个item视图对应的实体类对象
   getItemId(int position) 返回每个item视图对应的索引
   getView(int position, View convertView, ViewGroup parent) 返回每个item视图*/

	
	
	private Context context;
	private ArrayList<String> list;
	
	
	
	public MyAdapter(Context context, ArrayList<String> list) {
		super();
		this.context = context;
		this.list = list;
	}

	//此方法 返回值告诉ListView要创建多少各个长度列表
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	//此方法 会根据索引返回ListView中第arg0个显示的列表
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	//此方法 根据索引返回id
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

    @Override
	//每绘制一次条目就会调用一次这个方法,在此方法中指定绘制条目的样式,
    //并把要显示的相应数据添加到对应的条目控件中
    //postion 表示下一次将要绘制第几个条目
    //contextView 默认值为null,我们给它重新赋值,用来指定条目样式
	//并获取条目中所有控件,给响应属性赋值
	public View getView(int position, View contextView, ViewGroup parent) {
		// TODO Auto-generated method stub
    	
    	// 获取指定索引值的数据
		String string = list.get(position);

		if (contextView == null) {
			// 通过LayoutInflater 类的 from 方法 再 使用 inflate()方法得到指定的布局
			// 得到ListView中要显示的条目的布局
			LayoutInflater from = LayoutInflater.from(context);
			contextView = from.inflate(R.layout.item, null);
			// 从要显示的条目布局中 获得指定的组件
			Temp.tv = (TextView) contextView.findViewById(R.id.tv);
		}


		// 设置数值
		Temp.tv.setText(string);

		// 返回布局
		return contextView;
	}

	//静态内部类,保证不一直查找此对象(优化)
	static class Temp {
		static TextView tv;
	}

}


//java文件(MainActivity)


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//获取ListView的对象
		ListView lv = (ListView) findViewById(R.id.lv);
		
		//创建要显示的数据
		ArrayList<String> list = new ArrayList<String>();
		list.add("郭磊");
		list.add("石佳");
		list.add("张娜娜");
		//创建Adapter对象
		MyAdapter adapter = new MyAdapter(MainActivity.this, list);
		
		//把Adapter对象添加到视图ListView中
		lv.setAdapter(adapter);
		
		//ListView的监听(条目)
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "Adapter"+arg1, Toast.LENGTH_SHORT).show();
			}
		}); 
		
	}


}


