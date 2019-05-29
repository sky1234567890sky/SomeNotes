ProgressBar 增加和减少并显示出来，在设置隐藏与显示

// 布局设置水平的进度条
	activity_main.xml
	
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.ktang.demo3.MainActivity" >

	<TextView
		android:layout_width="match_parent"
		android:layout_height="wrap_parent"
		android:text="水平进度条"
		>
	
	<RelativeLayout
		android:layout_width="match_parent"
		android:layout_height="wrap_parent"
		>
		
		// 改组件设置水平进度条
		<ProgressBar
			android:id="+@id/pb"
			android:layout_width="250dp"
			android:layout_height="wrap_parent"
			style="@android:style/widget.ProgressBar.Horizontal"
			android:max="100"
			android:progress="20"
			android:secondaryProgress="30"
			>
		
		// 该组件显示进度条的当前进度值
		<TextView 
			android:id="@+id/tv"
			android:paddingLeft="20dp"
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"
			android:text="20"
			android:layout_toRightOf="@id/pb"
			/>
		
	</RelativeLayout>	
	
	<!-- 进度条控制按钮  -->
    <Button 
        android:id="@+id/bt_zengjia"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="增加进度"
        />
    
    <Button 
        android:id="@+id/bt_jianshao"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="减少进度"
        />
    
    <Button 
        android:id="@+id/bt_yincang"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="隐藏与显示进度条"/>
	
	
</LinearLayout>	


// java文件
(MainActivity.java)

public class MainActivity extends Activity implements OnClickListener {

	private final String TAG = "MainActivity";
	private ProgressBar pb;
	private Button zenjia, jianshao, yincang;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 通过id值获取到控件的对象
		pb = (ProgressBar) findViewById(R.id.pb);
		zenjia = (Button) findViewById(R.id.bt_zengjia);
		jianshao = (Button) findViewById(R.id.bt_jianshao);
		yincang = (Button) findViewById(R.id.bt_yincang);
		tv = (TextView) findViewById(R.id.tv);

		// 给控件设置监听
		zenjia.setOnClickListener(this);
		jianshao.setOnClickListener(this);
		yincang.setOnClickListener(this);

		// 获得这个进度条的范围的上限
		int max = pb.getMax();
		Log.v(TAG, "水平进度条的最大值是：" + max);
		// 返回进度值
		int progress = pb.getProgress();
		Log.v(TAG, "水平进度条的当前进度是：" + progress);
		// 设置水平进度条的进度值
		pb.setProgress(50);
		// 获得次要进度值
		int progress2 = pb.getSecondaryProgress();
		Log.v(TAG, "水平进度条的次进度是：" + progress2);

	}

	// 设置点击事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.bt_zengjia:
			int progress = pb.getProgress(); // 获取当前进度值
			progress += 10; // 每点一次增加10进度
			pb.setProgress(progress); // 重新设置进度值
			if (progress<=100) {
				tv.setText(progress + "");
			}

			break;

		case R.id.bt_jianshao:
			pb.incrementProgressBy(-10); // 每点一次减少10进度值
			int progress2 = pb.getProgress();
			if (progress2>=0) {
				tv.setText(progress2 + "");
			} 
			progress2 -= 10;

			break;

		case R.id.bt_yincang:
			if (pb.getVisibility() == View.GONE) { // 进度是隐藏
				pb.setVisibility(View.VISIBLE);// 设置为显示
			} else {
				pb.setVisibility(View.GONE); // 设置为隐藏
			}

			break;

		default:
			break;
		}

	}

}








