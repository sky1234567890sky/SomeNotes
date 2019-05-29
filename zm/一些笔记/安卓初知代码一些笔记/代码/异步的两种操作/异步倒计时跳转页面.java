异步倒计时跳转页面

// xml 文件
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
        android:text="五"
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
            android:text="添    加"
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



// java 文件

MainActivity.java

public class MainActivity extends Activity {

	// 定义一个数组
	String s[] = new String[] { "五", "四", "三", "二", "一" };
	private int i = 0;

	// 使用Handler 类 做异步处理
	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			switch (msg.what) { // what 标识 区分
			case 1:

				if (i < 4) {

					i++;

					tv.setText(s[i]);

				} else {

					// 取消(关闭)线程
					timer.cancel();
					// 跳转到第二个页面
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

		// 获取控件对象
		tv = (TextView) findViewById(R.id.tv);

		// 给控件设置值，根据指定下标获取数组中的值
		tv.setText(s[i]);

		// 创建任务
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// 子线程不能进行UI调试

				// 发送一个空消息
				handler.sendEmptyMessage(1); // what 标识 区分

			}
		};

		// 创建启动任务的对象
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




