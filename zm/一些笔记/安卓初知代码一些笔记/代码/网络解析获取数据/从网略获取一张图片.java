�����Ի�ȡһ��ͼƬ

// xml�ļ�
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
        android:text="����ͼƬ" />

    <ImageView
        android:id="@+id/iv"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>


// java�ļ�

public class MainActivity extends Activity {

	private ImageView iv;
	private Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		bt = (Button) findViewById(R.id.bt);

		// ������ť,����ͼƬ
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
			// ʹ��HTTPURLConnection��ȡһ��ͼƬ
			try {
				HttpURLConnection con = (HttpURLConnection) new URL(params[0]).openConnection();
				// ����get/post����ʽ,�����д��Ĭ����get����
				con.setRequestMethod("POST");
				// ���շ��������͹��������� getResponseCode()���շ��������͹�����״̬��(��Ӧ��)
				if (200 == con.getResponseCode()) {
					// ͨ���ֽ����������շ��������͵���Ӧ����
					InputStream is = con.getInputStream(); // ��ȡ������
					Bitmap bitmap = BitmapFactory.decodeStream(is);// ��ȡ�����紫�ݹ�������Ƭ
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
				// ���������δ��ȡ������Ƭ,����ʾһ��������
				iv.setImageResource(R.drawable.ic_launcher);
			} else {
				// ����������ȡ������Ƭ,�����Ƭ��ʾ����
				iv.setImageBitmap(result);
			}
		}
	}

}




