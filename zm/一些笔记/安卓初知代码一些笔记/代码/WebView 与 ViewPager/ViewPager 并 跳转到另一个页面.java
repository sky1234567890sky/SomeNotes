ViewPager 并 跳转到另一个页面

// xml文件
1.activity_main.xml

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
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</RelativeLayout>

2.vpitem1.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical" >

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="1"
        android:textSize="30sp" />

</LinearLayout>

3.vpitem.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical" >

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="2"
        android:textSize="30sp" />

</LinearLayout>

4.vpitem3.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical" >

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="3"
        android:textSize="30sp" />

    <Button
        android:id="@+id/bt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="立即体验" />

</LinearLayout>

5.activity_login.xml（跳转到的页面）

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.viewpager.LoginActivity" >

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="登陸界面" />

</RelativeLayout>



// java文件
1.MainActivity.java

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 实例化一个集合，用于存储布局文件
		ArrayList<View> list = new ArrayList<View>();

		// 获取要进行滑动的页面
		View v = LayoutInflater.from(this).inflate(R.layout.vpitem1, null);
		View v2 = LayoutInflater.from(this).inflate(R.layout.vpitem2, null);
		View v3 = LayoutInflater.from(this).inflate(R.layout.vpitem3, null);

		// 获取第三个布局的按钮控件对象
		Button bt = (Button) v3.findViewById(R.id.bt);

		// 把页面放入到集合中
		list.add(v);
		list.add(v2);
		list.add(v3);

		// 1.找控件对象
		ViewPager vp = (ViewPager) findViewById(R.id.vp);

		// 2 创建适配器
		Myvp my = new Myvp(list);

		// 3.绑定适配器
		vp.setAdapter(my);

		// 设置点击监听
		bt.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		// 进行页面跳转
		Intent intent = new Intent(MainActivity.this, LoginActivity.class);
		startActivity(intent);

	}

}

2.Myvp.java （自定义适配器）

public class Myvp extends PagerAdapter {

	ArrayList<View> list;

	public Myvp(ArrayList<View> list) {
		super();
		this.list = list;
	}

	// 控制加载的数量
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	// 下标和View 对应关系
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub

		View view = list.get(position);

		container.addView(view);

		// 返回你要看到的布局
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		// super.destroyItem(container, position, object); 这个不需要

		// 让旧的布局滑走了
		container.removeView(list.get(position));
	}

}

3.LoginActivity.java （另一个布局）

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
}





