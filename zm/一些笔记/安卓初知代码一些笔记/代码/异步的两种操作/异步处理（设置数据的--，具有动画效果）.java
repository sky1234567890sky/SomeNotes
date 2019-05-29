异步处理（设置数据的--倒计时，具有动画效果）

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
    tools:context="com.example.task.MainActivity" >

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="@string/hello_world"
        android:textSize="30sp" />

</RelativeLayout>

// MainActivity.java

public class MainActivity extends Activity {

	private TextView tv;
	private int i = 4;
	String[] s = new String[] { "一", "二", "三", "四", "五" };

	// 使用Handler类 做异步任务
	Handler handler = new Handler() {

		// 调用接收数据的方法
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case 1:

				if (i > 0) {

					i--;
					tv.setText(s[i]);

				} else {
					timer.cancel();
				}

				break;

			default:
				break;
			}

		};

	};
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
			public void run() { // 子线程不能修改UI
				// TODO Auto-generated method stub

				/*
				 * Message msg = new Message(); msg.obj = ""; 携带数据 msg.what = 1;
				 * 携带标识
				 * 
				 * handler.sendMessage(msg);
				 */

				// 空消息
				handler.sendEmptyMessage(1); // what 标识 区分

			}
		};

		// 创建启动任务的对象
		timer = new Timer();
		timer.schedule(task, 1000, 1000);

		// timer.cancel(); // 取消

	}

}