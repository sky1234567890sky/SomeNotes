�첽�����������ݵ�--����ʱ�����ж���Ч����

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
	String[] s = new String[] { "һ", "��", "��", "��", "��" };

	// ʹ��Handler�� ���첽����
	Handler handler = new Handler() {

		// ���ý������ݵķ���
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

		// ��ȡ�ؼ�����
		tv = (TextView) findViewById(R.id.tv);

		// ���ؼ�����ֵ������ָ���±��ȡ�����е�ֵ
		tv.setText(s[i]);

		// ��������
		TimerTask task = new TimerTask() {

			@Override
			public void run() { // ���̲߳����޸�UI
				// TODO Auto-generated method stub

				/*
				 * Message msg = new Message(); msg.obj = ""; Я������ msg.what = 1;
				 * Я����ʶ
				 * 
				 * handler.sendMessage(msg);
				 */

				// ����Ϣ
				handler.sendEmptyMessage(1); // what ��ʶ ����

			}
		};

		// ������������Ķ���
		timer = new Timer();
		timer.schedule(task, 1000, 1000);

		// timer.cancel(); // ȡ��

	}

}