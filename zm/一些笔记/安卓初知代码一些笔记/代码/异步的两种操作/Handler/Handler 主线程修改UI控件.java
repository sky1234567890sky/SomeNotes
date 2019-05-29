Handler ���߳��޸�UI�ؼ�

// xml�����ļ�
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

// java�ļ�
MainActivity.java

public class MainActivity extends Activity {

	/*
	 * 1. ���߳� ��UI�̣߳����ܽ��к�ʱ����(5s)
	 * 
	 * 2. ���̲߳����޸�UI
	 */

	/*
	 * 1.�� ���̣߳�UI���� new Handler ���ҽ��ܶ��󣨳�Աλ�ã�дһ������ handlerMessage(Message msg)
	 */

	// ����Handler ͬʱ ���� ��Ϣ���� ����Looper ��Ϣ�� --��handlerMessage(android.os.Message
	// msg)
	Handler h = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			// ����ת��
			String ss = (String) msg.obj;

			// ���߳��޸�UI
			tv.setText(ss);
		}

	};
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.tv);

		// ����һ�����߳�
		new Thread() {
			public void run() {

				// ����ʱ����
				String s = "����";

				// ���͵����� ͨ��Message���з�װ
				Message message = new Message();
				// ����ת�� ��s ������ Message��
				message.obj = s;

				// Handler �Ķ��� ���� ���ͷ���
				h.sendMessage(message);

			};
		}.start();

	}

}


