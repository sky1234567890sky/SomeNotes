��¼��ע���ҳ��

/*

	ͼһΪ��¼ҳ�棬���ע�ᰴť����ͼ��ҳ�棬�����˺������룬���ע�ᰴť����ͼһ��
���˺�������ʾ�ڿ��ڣ������¼��ť������ͼ������ʾ�˺�������

Ҫ��

	1. ͼһ��¼ҳ�棬�����û�����������ɵ�¼
	2. ͼ��ע��ҳ�棬���ע�ᰴť���˺�������ش�����һ����¼��ҳ��
	3. Ҫ���ж��˺�Ϊ11λ���֣�����Ϊ6-12λ�����ַ�
	4. ��¼�ɹ�������ͼ�������˺���������ʾ��ҳ����
	
*/


���裺
	
	һ. �ȴ�����������ҳ�棺
		��¼���桢ע����桢��¼�ɹ��Ľ���
		
	��. ��¼ҳ���java�ļ���
	
		1.��ȡ�ò������������id����
		2.���� ��תע��ҳ��ķ��� �� ��ת��½�ɹ���ҳ��ķ��� �� ����ע��ҳ�淵�ص����ݵķ���
	
	��. ע��ҳ���java�ļ���
		
		1.��ȡ�ò������������id����
		2.����ע�ᰴť�ĵ����������
			����Я��Я�����ݷ��ص���½ҳ��
		3.�رմ�ҳ��

	��. ��½�ɹ�ҳ���java�ļ���
		
		1.��ȡ�ò������������id����
		2.������������ʾ���ݵķ���
			��ʾ���ݵķ�����
			
				��ȡintentЯ�����ݵ��ַ���
				Ȼ���TextView�����������


				
���������ļ���	

	��½���沼�֣�

<LinearLayout>
	
	<LinearLayout 
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        >
        <TextView 
            android:layout_weight="1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="�û���"/>
        <EditText 
            android:layout_weight="2"
            android:id="@+id/et_User"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:hint="������11λ�����û���"/>
    </LinearLayout>
    
    
    <LinearLayout 
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        >
        <TextView 
            android:layout_weight="1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="����"/>
        <EditText 
            android:layout_weight="2"
            android:id="@+id/et_Password"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:hint="������6-12λ��������"/>
    </LinearLayout>
    

    <LinearLayout 
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        >
        <Button 
            android:id="@+id/bt_join"
            android:layout_weight="1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="ע��"/>
        <Button 
            android:id="@+id/bt_Denlu"
            android:layout_weight="1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="��¼"/>
    </LinearLayout>
				
</LinearLayout>

	ע����沼�֣�

<LinearLayout>

	<ImageView
        android:layout_width="match_parent"
        android:layout_height="120dp"
        android:scaleType="centerCrop"
        android:src="@drawable/timg" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center" >

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="�û���" />

        <EditText
            android:id="@+id/et_User"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="2"
            android:hint="������11λ�����û���" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center" >

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="����" />

        <EditText
            android:id="@+id/et_Password"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="2"
            android:hint="������6-12λ��������" />
    </LinearLayout>

    <Button
        android:id="@+id/bt_join"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:text="ע��" />

</LinearLayout>	
	
	
	��½�ɹ����沼�֣�
	
<LinearLayout>

    <TextView
        android:id="@+id/tv_User "
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="�˺�: " />

    <TextView
        android:id="@+id/tv_Password"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="����: " />

</LinearLayout>	


��¼ҳ���java�ļ���

public class MainActivity extends Activity {

	private EditText etUser;
	private EditText etPassword;
	private Button btJoin;
	private Button btDenlu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ȡ��һ�����ֵ���������Ķ���
		etUser = (EditText) findViewById(R.id.et_User);
		etPassword = (EditText) findViewById(R.id.et_Password);
		btJoin = (Button) findViewById(R.id.bt_join);
		btDenlu = (Button) findViewById(R.id.bt_Denlu);

		jianTingZhuCe();// ����ת����ע��ҳ����
		denlu(); // ��ת����½���ҳ��
	}

	// ����ע�ᰴť,�������˰�ť����ת��ע��ҳ��,���ڴ������ݴ�ע��ҳ��ش�ֵ
	public void jianTingZhuCe() {
		btJoin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// ������ת��ע��ҳ�����ͼ
				Intent intent = new Intent(MainActivity.this, Tu2Activity.class);

				// ��ʼ��ת������������
				startActivityForResult(intent, 10);

			}
		});
	}

	// requestCode ��Ӧ�� resultCode ������

	// ���ע��ҳ��Я�����ݷ���ֵ���ǽ�������,�������ݷ�������EditText��
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		// ������������Ӧ��ֱ������õ�ֵ��Ӧ
		if (10 == requestCode && resultCode == 20) {

			// ��ע��ҳ�淵�ص�������data�У�ͨ��data����StringExtra(String name)���� ��ȡ��Ӧ������ֵ
			String user = data.getStringExtra("user");
			String password = data.getStringExtra("password");

			// �ѷ��ص�����ֵ�ֱ����ø���Ӧ��EditText��
			etPassword.setText(password);
			etUser.setText(user);

		}
	}

	// ������¼��ť,��������¼��ť,����ͼЯ��������תҳ��tu3
	public void denlu() {
		// ������¼��ť
		btDenlu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// ��ȡ����EditText�е�����
				String user = etUser.getText().toString();
				String password = etPassword.getText().toString();

				// ��Ҫ��ͼЯ�����ݷ��ظ���һ��(��һ�� ��¼��)ҳ��
				Intent intent = new Intent(MainActivity.this, Tu3Activity.class);

				// ����ҪЯ��������
				intent.putExtra("user", user);
				intent.putExtra("password", password);

				// ����������ҳ��
				startActivity(intent);

			}
		});
	}

}

ע��ҳ���java�ļ���

public class Tu2Activity extends Activity {

	private EditText etUser;
	private EditText etPassword;
	private Button btJoin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tu2);

		// ͨ��id��ȡ�ؼ�����
		etUser = (EditText) findViewById(R.id.et_User);
		etPassword = (EditText) findViewById(R.id.et_Password);
		btJoin = (Button) findViewById(R.id.bt_join);

		// ����ע������ķ���
		jianTing();

	}

	// ����ע�ᰴť,Я��������ת����½ҳ��
	public void jianTing() {

		btJoin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��ȡ����EditText�е�����
				String user = etUser.getText().toString();
				String password = etPassword.getText().toString();

				// ��Ҫ��ͼЯ�����ݷ��ظ���һ��(��һ��)ҳ��
				Intent intent = new Intent();
				intent.putExtra("user", user);
				intent.putExtra("password", password);

				// ��������
				setResult(20, intent);
				finish(); // �رմ�ҳ��

			}
		});
	}

}

��½�ɹ�ҳ���java�ļ���

public class Tu3Activity extends Activity {

	private TextView tvUser;
	private TextView tvPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tu3);

		// ͨ��id��ȡ���еĿؼ�����
		tvUser = (TextView) findViewById(R.id.tv_User);
		tvPassword = (TextView) findViewById(R.id.tv_Password);

		// ������ʾ�ķ���
		xianShi();

	}

	// �������Ե�¼ҳ����˺�������,����ʾ������TextView��
	public void xianShi() {
		// ���Ȼ�ȡԭ��TextView�е��ַ���
		String user = tvUser.getText().toString();
		String password = tvPassword.getText().toString();

		// ��ȡintentЯ���������ַ���
		Intent intent = getIntent();
		user = user + intent.getStringExtra("user");
		password = password + intent.getStringExtra("password");

		// �ַ���ƴ��,���������ø�TextView
		tvUser.setText(user);
		tvPassword.setText(password);
	}
}


