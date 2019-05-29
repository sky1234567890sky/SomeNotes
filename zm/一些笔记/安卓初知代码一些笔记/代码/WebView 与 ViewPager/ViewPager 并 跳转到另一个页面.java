ViewPager �� ��ת����һ��ҳ��

// xml�ļ�
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
        android:text="��������" />

</LinearLayout>

5.activity_login.xml����ת����ҳ�棩

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
        android:text="��ꑽ���" />

</RelativeLayout>



// java�ļ�
1.MainActivity.java

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ʵ����һ�����ϣ����ڴ洢�����ļ�
		ArrayList<View> list = new ArrayList<View>();

		// ��ȡҪ���л�����ҳ��
		View v = LayoutInflater.from(this).inflate(R.layout.vpitem1, null);
		View v2 = LayoutInflater.from(this).inflate(R.layout.vpitem2, null);
		View v3 = LayoutInflater.from(this).inflate(R.layout.vpitem3, null);

		// ��ȡ���������ֵİ�ť�ؼ�����
		Button bt = (Button) v3.findViewById(R.id.bt);

		// ��ҳ����뵽������
		list.add(v);
		list.add(v2);
		list.add(v3);

		// 1.�ҿؼ�����
		ViewPager vp = (ViewPager) findViewById(R.id.vp);

		// 2 ����������
		Myvp my = new Myvp(list);

		// 3.��������
		vp.setAdapter(my);

		// ���õ������
		bt.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		// ����ҳ����ת
		Intent intent = new Intent(MainActivity.this, LoginActivity.class);
		startActivity(intent);

	}

}

2.Myvp.java ���Զ�����������

public class Myvp extends PagerAdapter {

	ArrayList<View> list;

	public Myvp(ArrayList<View> list) {
		super();
		this.list = list;
	}

	// ���Ƽ��ص�����
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	// �±��View ��Ӧ��ϵ
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

		// ������Ҫ�����Ĳ���
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		// super.destroyItem(container, position, object); �������Ҫ

		// �þɵĲ��ֻ�����
		container.removeView(list.get(position));
	}

}

3.LoginActivity.java ����һ�����֣�

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
}





