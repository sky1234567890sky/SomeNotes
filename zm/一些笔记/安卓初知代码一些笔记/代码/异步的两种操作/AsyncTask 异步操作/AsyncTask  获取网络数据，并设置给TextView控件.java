AsyncTask	获取网络数据，并设置给TextView控件

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
    tools:context="com.example.asynctask.MainActivity" >

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hello_world"
        android:textSize="30sp" />

</RelativeLayout>


// java 文件

MyTask.java

// params 是请求的网址  的数据类型
// progress  网络加载的进度值  的数据类型
// Result	是请求之后的结果  的数据类型

// 如果请求的是json  Result
// 如果请求的是图片	Drawable

public class MyTask extends AsyncTask<String, Integer, String> {

	TextView tv;

	public MyTask(TextView tv) {
		super();
		this.tv = tv;
	}

	// doInBackground 子线程
	@Override
	protected String doInBackground(String... params) {

		String s = "";
		// String... params 可变参
		// 可变参 实际上是一个 数组

		// 耗时操作 网络请求
		try {
			URL url = new URL(params[0]);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			if (con.getResponseCode() == 200) {

				InputStream in = con.getInputStream();

				byte[] by = new byte[1024];
				int len = 0;
				while ((len = in.read(by)) != -1) {

					s += new String(by, 0, len);

				}

			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.i("tag", s);

		return s;
	}

	// 主线程
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		// 已经得到了 网络请求的数据 在主线程
		// 我个能更新UI 把数据显示在控件上
		tv.setText(result);

	}

}

MainActivity.java

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView tv = (TextView) findViewById(R.id.tv);

		// 创建异步任务
		MyTask task = new MyTask(tv);

		// 启动任务:ip地址必须是主机的ip
		task.execute("http://192.168.1.10:8080/anzhuochu_jpg-json/a.txt");

	}

}