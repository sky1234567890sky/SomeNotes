AsyncTask	��ȡ�������ݣ������ø�TextView�ؼ�

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
    tools:context="com.example.asynctask.MainActivity" >

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hello_world"
        android:textSize="30sp" />

</RelativeLayout>


// java �ļ�

MyTask.java

// params ���������ַ  ����������
// progress  ������صĽ���ֵ  ����������
// Result	������֮��Ľ��  ����������

// ����������json  Result
// ����������ͼƬ	Drawable

public class MyTask extends AsyncTask<String, Integer, String> {

	TextView tv;

	public MyTask(TextView tv) {
		super();
		this.tv = tv;
	}

	// doInBackground ���߳�
	@Override
	protected String doInBackground(String... params) {

		String s = "";
		// String... params �ɱ��
		// �ɱ�� ʵ������һ�� ����

		// ��ʱ���� ��������
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

	// ���߳�
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		// �Ѿ��õ��� ������������� �����߳�
		// �Ҹ��ܸ���UI ��������ʾ�ڿؼ���
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

		// �����첽����
		MyTask task = new MyTask(tv);

		// ��������:ip��ַ������������ip
		task.execute("http://192.168.1.10:8080/anzhuochu_jpg-json/a.txt");

	}

}