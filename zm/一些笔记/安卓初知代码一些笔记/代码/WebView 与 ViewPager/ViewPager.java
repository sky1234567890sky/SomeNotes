ViewPager

ʹ��ʾ��1������÷�

�ؼ����ִ��룺android.support.v4.view.ViewPager �����

1.������ÿ��View�Ĳ��֣�һʽ���ݣ���������Viewһ����

�����������ֵ�һ�����id��

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


�����ļ�����(tu1.xml),(tu2.xml),(tu3.xml)��

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


2.Ȼ���дһ���Զ������PagerAdapter��

�Զ����PagerAdapter ��java����(MyPagerAdapter)��

public class MyPagerAdapter extends PagerAdapter {
	
	// ����ArrayList���ϣ�������View����
	private ArrayList<View> list;

	public MyPagerAdapter(ArrayList<View> list) {
		super();
		this.list = list;
	}

	@Override
	public int getCount() { // ��Ч��ͼ������
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) { // �ж�arg0��arg1�Ƿ���ͬһ������
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


3.���ŵ�Activity�ˣ�����ǰѧ��ListView�ǳ����ƣ�

���õ�java�ļ�(MainActivity)��

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
		
		// ��ȡ���������ļ�
		// LayoutInflater from = LayoutInflater.from(MainActivity.this);
		LayoutInflater li = getLayoutInflater();
		tu1 = li.inflate(R.layout.tu1, null,false);
		tu2 = li.inflate(R.layout.tu2, null,false);
		tu3 = li.inflate(R.layout.tu3, null,false);
		tu4 = li.inflate(R.layout.tu4, null,false);

		// ����Ҫ��ȡ�Ĳ���
		ArrayList<View> list = new ArrayList<View>();
		list.add(tu1);
		list.add(tu2);
		list.add(tu3);
		list.add(tu4);

		// ����PagerAdapter
		MyPagerAdapter adapter = new MyPagerAdapter(list);

		// ����������PagerView
		vp.setAdapter(adapter);

	}

}
  