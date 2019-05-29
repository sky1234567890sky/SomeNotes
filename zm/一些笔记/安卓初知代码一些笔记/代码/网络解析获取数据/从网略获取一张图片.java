从网略获取一张图片

// xml文件
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
    tools:context="com.example.day11httpurlconnection.MainActivity" >

    <Button
        android:id="@+id/bt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:text="下载图片" />

    <ImageView
        android:id="@+id/iv"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>


// java文件

public class MainActivity extends Activity {

	private ImageView iv;
	private Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		bt = (Button) findViewById(R.id.bt);

		// 监听按钮,下载图片
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new My().execute("http://192.168.1.10:8080/anzhuochu/ab.jpg");
			}
		});

	}

	class My extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			// 使用HTTPURLConnection获取一张图片
			try {
				HttpURLConnection con = (HttpURLConnection) new URL(params[0]).openConnection();
				// 设置get/post请求方式,如果不写则默认是get请求
				con.setRequestMethod("POST");
				// 接收服务器发送过来的数据 getResponseCode()接收服务器发送过来的状态码(响应码)
				if (200 == con.getResponseCode()) {
					// 通过字节输入流接收服务器发送的响应正文
					InputStream is = con.getInputStream(); // 获取输入流
					Bitmap bitmap = BitmapFactory.decodeStream(is);// 获取到网络传递过来的照片
					return bitmap;
				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (result == null) {
				// 如果从网络未获取到了照片,给显示一个机器人
				iv.setImageResource(R.drawable.ic_launcher);
			} else {
				// 如果从网络获取到了照片,则把照片显示出来
				iv.setImageBitmap(result);
			}
		}
	}

}




