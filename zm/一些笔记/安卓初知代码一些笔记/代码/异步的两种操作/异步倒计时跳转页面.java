�첽����ʱ��תҳ��

// xml �ļ�
activity_main.xml

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.yuekao4.MainActivity" >

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="��"
        android:textSize="30sp" />

</RelativeLayout>

activity_list.xml

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.yuekao4.ListActivity" >

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:text="��    ��"
            android:textSize="20sp" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentRight="true"
            android:text="+"
            android:textSize="30sp"
            android:textStyle="bold" />
    </RelativeLayout>

    <ListView
        android:id="@+id/lv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >
    </ListView>

</LinearLayout>



// java �ļ�

MainActivity.java

public class MainActivity extends Activity {

	// ����һ������
	String s[] = new String[] { "��", "��", "��", "��", "һ" };
	private int i = 0;

	// ʹ��Handler �� ���첽����
	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			switch (msg.what) { // what ��ʶ ����
			case 1:

				if (i < 4) {

					i++;

					tv.setText(s[i]);

				} else {

					// ȡ��(�ر�)�߳�
					timer.cancel();
					// ��ת���ڶ���ҳ��
					Intent intent = new Intent(MainActivity.this, ListActivity.class);
					startActivity(intent);

				}

				break;

			default:
				break;
			}

		};

	};
	private TextView tv;
	private Timer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ȡ�ؼ�����
		tv = (TextView) findViewById(R.id.tv);

		// ���ؼ�����ֵ������ָ���±��ȡ�����е�ֵ
		tv.setText(s[i]);

		// ��������
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// ���̲߳��ܽ���UI����

				// ����һ������Ϣ
				handler.sendEmptyMessage(1); // what ��ʶ ����

			}
		};

		// ������������Ķ���
		timer = new Timer();
		timer.schedule(task, 1000, 1000);

	}

}

ListActivity.java

public class ListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
	}

}




