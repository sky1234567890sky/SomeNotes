Handler 主线程修改UI控件

// xml布局文件
activity_main.xml

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.handler.MainActivity" >

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="25sp" />

</RelativeLayout>

// java文件
MainActivity.java

public class MainActivity extends Activity {

	/*
	 * 1. 主线程 （UI线程）不能进行耗时操作(5s)
	 * 
	 * 2. 子线程不能修改UI
	 */

	/*
	 * 1.在 主线程（UI）中 new Handler 并且接受对象（成员位置）写一个方法 handlerMessage(Message msg)
	 */

	// 创建Handler 同时 创建 消息队列 创建Looper 消息泵 --》handlerMessage(android.os.Message
	// msg)
	Handler h = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			// 向下转型
			String ss = (String) msg.obj;

			// 主线程修改UI
			tv.setText(ss);
		}

	};
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.tv);

		// 创建一个子线程
		new Thread() {
			public void run() {

				// 做耗时操作
				String s = "郭磊";

				// 发送的数据 通过Message进行封装
				Message message = new Message();
				// 向上转型 把s 保存在 Message中
				message.obj = s;

				// Handler 的对象 调用 发送方法
				h.sendMessage(message);

			};
		}.start();

	}

}


