���裺
	1. ����������������һ����ť�����
	2. ������һ���Զ���Ĳ��֣�����EditText�����
	3. java�ļ���
			ͨ��idֵ��ȡ�������еİ�ť�ؼ��Ķ���
			ͨ����ť�Ķ������õ����������
			
			�����������
				��ȡҪ��ӵ��Ӳ��ֵĶ���ͨ��getLayoutInflater().inflate()��
				ͨ���Ӳ��ֵĶ�����÷����������ָ���ؼ��Ķ���(findViewById)
				ʵ����AlertDialog�������öԻ������Ϣ
				�����ʾ����������show()
				
xml����

1.activity_main.xml
	
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.lianxi.demo4.MainActivity" >

	<Button 
	    android:id="@+id/bt"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="���ҵ���"
	    />

</LinearLayout>	
				
2.dialog_item.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="�˺ţ�" />

    <EditText
        android:id="@+id/et_user"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="�������˺�" />

</LinearLayout>

3.MainActivity.java	

public class MainActivity extends Activity implements OnLongClickListener {

	private final String Tag = "MainActivity";
	private Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt = (Button) findViewById(R.id.bt);

		bt.setOnLongClickListener(this);

	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.bt:

			View in = getLayoutInflater().inflate(R.layout.dialog_item, null);
			final EditText et = (EditText) in.findViewById(R.id.et_user);

			Dialog dialog = new AlertDialog.Builder(MainActivity.this).setTitle("�Ի���").setView(in)
					.setPositiveButton("�ύ", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String string = et.getText().toString();
							Log.v(Tag, "���������Ϊ��" + string);

						}
					}).create();
			dialog.show();

			break;

		default:
			break;
		}

		return false;
	}

}			