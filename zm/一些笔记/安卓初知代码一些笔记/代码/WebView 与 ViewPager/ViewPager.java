ViewPager

使用示例1：最简单用法

关键部分代码：android.support.v4.view.ViewPager 组件名

1.首先是每个View的布局，一式三份，另外两个View一样：

必须有主布局的一个组件id：

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.viewpager.MainActivity" >

    <android.support.v4.view.ViewPager
        android:id="@+id/vp"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        />

</RelativeLayout>


布局文件代码(tu1.xml),(tu2.xml),(tu3.xml)：

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >
    
	<ImageView 
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:src="@drawable/one"
	    />
     
</LinearLayout>


2.然后编写一个自定义个的PagerAdapter：

自定义的PagerAdapter 的java代码(MyPagerAdapter)：

public class MyPagerAdapter extends PagerAdapter {
	
	// 设置ArrayList集合，泛型是View布局
	private ArrayList<View> list;

	public MyPagerAdapter(ArrayList<View> list) {
		super();
		this.list = list;
	}

	@Override
	public int getCount() { // 有效视图的数量
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) { // 判断arg0与arg1是否是同一个对象
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		container.addView(list.get(position));

		return list.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {

		container.removeView(list.get(position));
	}

}


3.接着到Activity了，和以前学的ListView非常类似：

设置的java文件(MainActivity)：

public class MainActivity extends Activity {

	private ViewPager vp;
	private View tu1;
	private View tu2;
	private View tu3;
	private View tu4;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		vp = (ViewPager) findViewById(R.id.vp);
		
		// 获取其他布局文件
		// LayoutInflater from = LayoutInflater.from(MainActivity.this);
		LayoutInflater li = getLayoutInflater();
		tu1 = li.inflate(R.layout.tu1, null,false);
		tu2 = li.inflate(R.layout.tu2, null,false);
		tu3 = li.inflate(R.layout.tu3, null,false);
		tu4 = li.inflate(R.layout.tu4, null,false);

		// 创建要获取的布局
		ArrayList<View> list = new ArrayList<View>();
		list.add(tu1);
		list.add(tu2);
		list.add(tu3);
		list.add(tu4);

		// 创建PagerAdapter
		MyPagerAdapter adapter = new MyPagerAdapter(list);

		// 绑定适配器与PagerView
		vp.setAdapter(adapter);

	}

}
  