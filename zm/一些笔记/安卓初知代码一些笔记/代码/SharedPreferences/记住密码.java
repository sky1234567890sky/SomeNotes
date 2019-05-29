��ס����Ĵ���

// xml�ļ�
activity_xml

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.jizhupwd.MainActivity" >

    <EditText
        android:id="@+id/etname"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="�������˺�" />

    <EditText
        android:id="@+id/etpaw"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="����������" />

    <CheckBox
        android:id="@+id/cb"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="��ס����" />

    <Button
        android:id="@+id/btdenglu"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="��¼" />

</LinearLayout>

// java�ļ�
MainActivity.java

public class MainActivity extends Activity implements OnClickListener {

	private EditText etname;
	private EditText etpaw;
	private CheckBox cb;
	private Button btdenglu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ���û�ȡ�ؼ�����ķ���
		getView();

		// ��ȡapp�е����ݵ��� SharedPreferences
		SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);

		// �жϱ���Ĺ�ѡ��״̬��ֵ
		boolean iscb = sp.getBoolean("iscb", false);

		if (iscb) {

			// �����
			// Ҫ��ʾ ������˺� ��ѡ״̬
			String sp_name = sp.getString("n", "");
			String sp_paw = sp.getString("p", "");

			// ��ʾ ���õ��ؼ���
			etname.setText(sp_name);
			etpaw.setText(sp_paw);

		} else {

			// û��ѡ��
			// ��ʾ�˺�
			String sp_name = sp.getString("n", "");

			// ��ʾ���ؼ���
			etname.setText(sp_name);

		}
		cb.setChecked(iscb); // ��true ��false ����Ҫ����

		btdenglu.setOnClickListener(this);

	}

	// ��ȡ�ؼ�����
	private void getView() {
		// TODO Auto-generated method stub

		etname = (EditText) findViewById(R.id.etname);
		etpaw = (EditText) findViewById(R.id.etpaw);
		cb = (CheckBox) findViewById(R.id.cb);
		btdenglu = (Button) findViewById(R.id.btdenglu);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		/*
		 * 1.�����¼��ťʱ��ȥ�������ϵ� ��ѡ״̬�� �����ж�
		 * 
		 * 2.���ѡ�У���ȡ�����ϵ��˺������룬��������
		 * 
		 * 3.���û��ѡ�У���״̬����
		 */

		// ȡ�������ϵĹ�ѡ״̬
		SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
		Editor edit = sp.edit();
		boolean checked = cb.isChecked();

		// �жϹ�ѡ״̬
		if (checked) {

			// ���ѡ�У���ȡ�˺�������
			String name = etname.getText().toString();
			String paw = etpaw.getText().toString();

			// ���˺���������뵽edit��
			edit.putString("n", name);
			edit.putString("p", paw);

		}
		// ��ѡ��װ����뵽edit��
		edit.putBoolean("iscb", checked);

		// �ύ����
		edit.commit();
	}

}

