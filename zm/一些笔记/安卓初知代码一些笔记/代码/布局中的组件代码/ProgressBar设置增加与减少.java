ProgressBar ���Ӻͼ��ٲ���ʾ��������������������ʾ

// ��������ˮƽ�Ľ�����
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
		android:text="ˮƽ������"
		>
	
	<RelativeLayout
		android:layout_width="match_parent"
		android:layout_height="wrap_parent"
		>
		
		// ���������ˮƽ������
		<ProgressBar
			android:id="+@id/pb"
			android:layout_width="250dp"
			android:layout_height="wrap_parent"
			style="@android:style/widget.ProgressBar.Horizontal"
			android:max="100"
			android:progress="20"
			android:secondaryProgress="30"
			>
		
		// �������ʾ�������ĵ�ǰ����ֵ
		<TextView 
			android:id="@+id/tv"
			android:paddingLeft="20dp"
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"
			android:text="20"
			android:layout_toRightOf="@id/pb"
			/>
		
	</RelativeLayout>	
	
	<!-- ���������ư�ť  -->
    <Button 
        android:id="@+id/bt_zengjia"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="���ӽ���"
        />
    
    <Button 
        android:id="@+id/bt_jianshao"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="���ٽ���"
        />
    
    <Button 
        android:id="@+id/bt_yincang"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="��������ʾ������"/>
	
	
</LinearLayout>	


// java�ļ�
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

		// ͨ��idֵ��ȡ���ؼ��Ķ���
		pb = (ProgressBar) findViewById(R.id.pb);
		zenjia = (Button) findViewById(R.id.bt_zengjia);
		jianshao = (Button) findViewById(R.id.bt_jianshao);
		yincang = (Button) findViewById(R.id.bt_yincang);
		tv = (TextView) findViewById(R.id.tv);

		// ���ؼ����ü���
		zenjia.setOnClickListener(this);
		jianshao.setOnClickListener(this);
		yincang.setOnClickListener(this);

		// �������������ķ�Χ������
		int max = pb.getMax();
		Log.v(TAG, "ˮƽ�����������ֵ�ǣ�" + max);
		// ���ؽ���ֵ
		int progress = pb.getProgress();
		Log.v(TAG, "ˮƽ�������ĵ�ǰ�����ǣ�" + progress);
		// ����ˮƽ�������Ľ���ֵ
		pb.setProgress(50);
		// ��ô�Ҫ����ֵ
		int progress2 = pb.getSecondaryProgress();
		Log.v(TAG, "ˮƽ�������Ĵν����ǣ�" + progress2);

	}

	// ���õ���¼�
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.bt_zengjia:
			int progress = pb.getProgress(); // ��ȡ��ǰ����ֵ
			progress += 10; // ÿ��һ������10����
			pb.setProgress(progress); // �������ý���ֵ
			if (progress<=100) {
				tv.setText(progress + "");
			}

			break;

		case R.id.bt_jianshao:
			pb.incrementProgressBy(-10); // ÿ��һ�μ���10����ֵ
			int progress2 = pb.getProgress();
			if (progress2>=0) {
				tv.setText(progress2 + "");
			} 
			progress2 -= 10;

			break;

		case R.id.bt_yincang:
			if (pb.getVisibility() == View.GONE) { // ����������
				pb.setVisibility(View.VISIBLE);// ����Ϊ��ʾ
			} else {
				pb.setVisibility(View.GONE); // ����Ϊ����
			}

			break;

		default:
			break;
		}

	}

}








