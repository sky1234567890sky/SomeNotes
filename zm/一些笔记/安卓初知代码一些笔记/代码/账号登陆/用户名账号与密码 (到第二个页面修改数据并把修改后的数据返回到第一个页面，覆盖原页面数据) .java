�û����˺������� (���ڶ���ҳ���޸����ݲ����޸ĺ�����ݷ��ص���һ��ҳ�棬����ԭҳ������) 

���裺

	xml���֣�
	//�ڵ�һ��ҳ�沼���ļ��д���һ��ListView�Ĳ����ļ�
	//����һ��item���֣����Ҫ����ListView�еĸ�ʽ��
	//�ڵڶ�ҳ��Ĳ����ļ��д����������Դ������ݲ����޸ĵ�EditText���
		

	��һ��ҳ���java�ļ���
	1.��java�ļ��л�ȡListView�Ķ���
	2.����Ҫ��ӵ�����
	3.�����Զ����������
	4.�����������뵽ָ����ListView��
	5.ʹ�ó�����������ת���ڶ���ҳ�棬�������ݴ���ȥ
	6.��дonActivityResult()�������������Եڶ���ҳ�������

	
	ʵ�����Ķ���(User)����Ҫʵ�����л���(Serializable)
	1.��������ָ��Ҫ�洢���ݵ�����
	2.һֱ��ݼ���ȥ
	
	
	�Զ����������java�ļ���
	1.�̳� ArrayAdapter<User> ������
	2.����һ���вη��� public MyAdapter(Context context,int resource,List<User> objects)
	3.��дһ������ getView(int position,View convertView,ViewGroup parent)
	4.��getView()������ ʹ��ָ����item���֣���ȡָ�����ֵ����������
				 Ȼ��ͨ��������ȡָ����item���������������ֵ�����ظ�����
	
	
	
	�ڶ���ҳ���java�ļ���
	1.�������Ե�һ��ҳ������ݣ�
	2.����ʾ��EditText��	(��ȡָ���Ķ��󣬲���������)
	3.������ť����EditText���޸ĺ�����ݷ��ص���һ��ҳ����
	
	

��һ��ҳ��Ĳ����ļ���
	
	<ListView
        android:id="@+id/lv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
		
		
��Ŀ�����ļ���	

	<TextView 
	    android:id="@+id/tv1"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="@string/hello_world"/>
    
    <TextView 
	    android:id="@+id/tv2"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="@string/hello_world"/>
		
		
�ڶ���ҳ��Ĳ����ļ���	

	<EditText
        android:id="@+id/et_user"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="�������˺�" />

    <EditText
        android:id="@+id/et_password"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="����������" />
    
    
    <Button 
        android:id="@+id/bt"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="ȷ���޸�"/>


��һ��ҳ���java�ļ���

public class MainActivity extends Activity {


	
	private ArrayList<User> list;
	private int index;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//����һ��ListView�Ĳ����ļ�
		//����һ��item���֣����Ҫ����ListView�еĸ�ʽ��
		//�ڵڶ�ҳ��Ĳ����ļ��д����������Դ������ݲ����޸ĵ�EditText���
		

		//��java�ļ��л�ȡListView�Ķ���
		ListView lv = (ListView) findViewById(R.id.lv);
		
		//����Ҫ��ӵ�����
		list = new ArrayList<User>();
		list.add(new User("123", "456"));
		list.add(new User("123", "456"));
		list.add(new User("123", "456"));
		
		//�����Զ����������
		adapter = new MyAdapter(MainActivity.this, R.layout.item, list);
		
		//�����������뵽ָ����ListView��
		lv.setAdapter(adapter);
		
		//ʹ�ó�����������ת���ڶ���ҳ�棬�������ݴ���ȥ
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				index = position;
				
				
				//��ת���ڶ���ҳ�����ͼ
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				//���Bundle�Ķ��������洢����
				Bundle bundle = new Bundle();
				//�洢ָ�������л�������
				bundle.putSerializable("user", list.get(position));
				
				//������ŵ���ͼ��
				intent.putExtras(bundle);
				
				//��ʼ��תҳ�棬��Я�����ݵ���ͼ����ȥ
				startActivityForResult(intent, 10);
				
				return false;
			}
		});
		
	}
	
	//��дonActivityResult()�������������Եڶ���ҳ�������
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		//requestCode  ������  resultCode  �����
		if (requestCode==10 && resultCode ==20) {
			
			//��ȡЯ������������
			Bundle bundle = data.getExtras();
			User user = (User) bundle.getSerializable("user2");
			
			//���޸ĺ��������ӵ�ָ��������������ʹ��������֪ͨ���ݽ��л���
			list.set(index, user);
			adapter.notifyDataSetChanged();
			
		}
		
	}		
		
		
ʵ����User��java�ļ���

public class User implements Serializable {

	private String name;
	private String password;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + "]";
	}

}
	
	
�Զ����������java�ļ���	

public class MyAdapter extends ArrayAdapter<User> {

	public MyAdapter(Context context, int resource, List<User> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
		Holder holder = null;
		
		//���ת����ͼΪ��ʱ
		if (convertView == null) {
			
			//��ȡHolder��Ķ���
			holder = new Holder();
			
			//���Ҫ��ӵĲ��ֵĶ��󣬸�ֵ��ת������
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
			
			//ͨ�����϶�����������������󣬲���ֵ�� holder���ж�Ӧ��������
			holder.tv1 = (TextView) convertView.findViewById(R.id.tv1);
			holder.tv2 = (TextView) convertView.findViewById(R.id.tv2);
			
			//��Holder������뵽 convertView ת����ͼ��
			convertView.setTag(holder);
			
		}else {
			//���ת�����ֲ�Ϊnullʱ����ȡָ���Ĳ���
			holder = (Holder) convertView.getTag();
		}
		
		//ͨ��ָ����������ȡ����
		User user = getItem(position); 
		
		//�������������ָ�������
		holder.tv1.setText(user.getName());
		holder.tv2.setText(user.getPassword());;
		
		return convertView;
	}
	
	public class Holder{
		TextView tv1;
		TextView tv2;
	}
	
}


�ڶ���ҳ���java�ļ���

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		// �������Ե�һ��ҳ������ݣ�
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		User user = (User) bundle.getSerializable("user");

		// ����ʾ��EditText��
		final EditText etuser = (EditText) findViewById(R.id.et_user);
		final EditText etpassword = (EditText) findViewById(R.id.et_password);
		etuser.setText(user.getName());
		etpassword.setText(user.getPassword());

		// ������ť����EditText���޸ĺ�����ݷ��ص���һ��ҳ����
		Button bt = (Button) findViewById(R.id.bt);

		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// �Ȱ�user�����ȡ������������ͼ��
				String user = etuser.getText().toString();
				String password = etpassword.getText().toString();
				User user2 = new User(user, password);

				Bundle bundle2 = new Bundle();
				bundle2.putSerializable("user2", user2);
				Intent data = new Intent();
				data.putExtras(bundle2);

				// ���÷�������
				setResult(20, data);

				// ������ҳ��
				finish();
			}
		});
	}

}


	
	