����������ݣ�����ӵ��б��У�����һ���޸�ҳ��

// xml�ļ�

1.activity_main.xml	(��ҳ��)

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.huoqu_intentmessage.MainActivity" >

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="��������"
        android:textSize="30sp" />

    <ListView
        android:id="@+id/lv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >
    </ListView>

</LinearLayout>

2.activity_up.xml (�޸�ҳ��)

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.huoqu_intentmessage.UpActivity" >

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="�޸���Ϣ"
        android:textSize="30sp" />

    <ImageView
        android:id="@+id/iv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/ic_launcher" />

    <EditText
        android:id="@+id/etname"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <EditText
        android:id="@+id/etdiscription"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <EditText
        android:id="@+id/etprice"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <Button
        android:id="@+id/btok"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="ȷ���޸�" />

</LinearLayout>

3.item.xml (�б���ҳ��)

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal" >

    <ImageView
        android:id="@+id/ivi"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/ic_launcher" />

    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical" >

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="horizontal" >

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="���֣�"
                android:textSize="20sp" />

            <TextView
                android:id="@+id/tvname"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="1"
                android:textSize="20sp" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="horizontal" >

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="������Ϣ��"
                android:textSize="20sp" />

            <TextView
                android:id="@+id/tvdiscrip"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="С�쳵"
                android:textSize="20sp" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="20dp"
                android:text="�۸�"
                android:textSize="20sp" />

            <TextView
                android:id="@+id/tvprice"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="1233"
                android:textSize="20sp" />
        </LinearLayout>
    </LinearLayout>

</LinearLayout>

// java�ļ�

1.Demo.java

public class Demo {

	ArrayList<User> infos;

	public ArrayList<User> getInfos() {
		return infos;
	}

	public void setInfos(ArrayList<User> infos) {
		this.infos = infos;
	}

}

2.User.java

public class User implements Serializable {

	String name;
	String discription;
	String price;
	String img;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}

3.MainActivity.java

public class MainActivity extends Activity implements OnItemLongClickListener, OnItemClickListener {

	private ListView lv;
	private ArrayList<User> infos;
	private MyAdapter adapter;
	// �Զ���һ���±�����������洢�±�
	private int index = 0;

	// newһ��Handler��
	Handler handler = new Handler() {

		// ��д������Ϣ�ķ���
		public void handleMessage(android.os.Message msg) {

			// ��������
			infos = (ArrayList<User>) msg.obj;

			// ����������
			adapter = new MyAdapter(MainActivity.this, infos);

			// ��������
			lv.setAdapter(adapter);

		};

	};
	private AlertDialog create;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ȡ�б�Ķ���
		lv = (ListView) findViewById(R.id.lv);

		// �������󣬽�������
		getHttpData();

		// ��Ŀ��������
		lv.setOnItemLongClickListener(this);

		// ��Ŀ�������
		lv.setOnItemClickListener(this);

		// ����һ������
		create = new AlertDialog.Builder(this).setTitle("�Ƿ�ɾ��").setPositiveButton("ȷ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

				// ɾ�� ���� ���� ������������ļ��ϣ�
				infos.remove(index);
				// ˢ��������
				adapter.notifyDataSetChanged();

			}
		}).setNegativeButton("ȡ��", null).create();

	}

	public void getHttpData() {

		// ����һ�����߳�
		new Thread() {

			// ��дrun()����
			public void run() {

				// �������󣬽�������
				try {

					URL url = new URL("http://192.168.1.10:8080/anzhuochu_jpg-json/day12.txt");
					// ת��ΪHttpURLConnection��
					HttpURLConnection open = (HttpURLConnection) url.openConnection();

					// �ж����������Ƿ�Ϊ200
					if (open.getResponseCode() == 200) {

						// ����һ���ַ����������������ڴ�������������
						StringBuffer sb = new StringBuffer();

						// ͨ��������󣬻�ȡ������
						InputStream is = open.getInputStream();

						// ת��Ϊ�ַ���ȡ������
						BufferedReader br = new BufferedReader(new InputStreamReader(is));

						// ����һ����ֵ
						String len = null;

						// ѭ����ȡÿһ�У��ж��Ƿ�Ϊnull
						while ((len = br.readLine()) != null) {

							// �����Ϊ�գ����뵽�ַ�����������
							sb.append(len);

						}

						// ��ӡLog��Ϣ
						Log.i("tag", "===>" + sb.toString());

						// �������
						Gson gson = new Gson();
						Demo fromJson = gson.fromJson(sb.toString(), Demo.class);
						ArrayList<User> infos = fromJson.getInfos();

						// ���͵����� ��ͨ�� Message ���з�װ��
						Message message = new Message();
						// �����ݴ洢����Ϣ��
						message.obj = infos;
						// ������Ϣ
						handler.sendMessage(message);
					}

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			};

		}.start();

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

		// �������ɾ�����ݣ�Ҫ�������� ��Ҫ�±�

		// ��¼�������Ŀ���±�
		index = position;

		// ��ʾ����
		create.show();

		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		// �����������ȡ�����Ŀ������
		User user = infos.get(position);

		// ��¼ �������Ŀ��С��
		index = position;

		// ��ת ��ֵ ���µĽ�����ʾ �µĽ�������޸�����
		Intent intent = new Intent(MainActivity.this, UpActivity.class);
		intent.putExtra("u", user);

		// ���ش�ֵ�ķ�ʽ ��ת
		startActivityForResult(intent, 10);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		// �ж������룬����Ӧ���Ƿ����
		if (requestCode == 10 && resultCode == 20) {

			// ���ջش���ֵ
			User user = (User) data.getSerializableExtra("u");

			// �޸������������е����� user.set(mark,user)
			infos.set(index, user);

			// ˢ��������
			adapter.notifyDataSetChanged();

		}

	}

}

4.MyAdapter.java

public class MyAdapter extends BaseAdapter {

	Context context;
	ArrayList<User> list;

	public MyAdapter(Context context, ArrayList<User> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder = null;
		if (convertView == null) {

			convertView = LayoutInflater.from(context).inflate(R.layout.item, null);

			holder = new ViewHolder();
			holder.tvprice = (TextView) convertView.findViewById(R.id.tvprice);
			holder.tvname = (TextView) convertView.findViewById(R.id.tvname);
			holder.tvdiscrip = (TextView) convertView.findViewById(R.id.tvdiscrip);
			holder.iv = (ImageView) convertView.findViewById(R.id.ivi);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvprice.setText(list.get(position).getPrice());
		holder.tvname.setText(list.get(position).getName());
		holder.tvdiscrip.setText(list.get(position).getDiscription());

		// ���� ͼƬ ʹ�ÿ�ܼ���ͼƬ ������ ����
		Picasso.with(context).load(list.get(position).getImg()).into(holder.iv);
		return convertView;
	}

	class ViewHolder {

		TextView tvname;
		TextView tvprice;
		TextView tvdiscrip;
		ImageView iv;

	}

}

5.UpActivity.java

public class UpActivity extends Activity implements OnClickListener {

	private EditText etname, etprice, etdiscription;
	private ImageView iv;
	private Button bt;
	private User user;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_up);

		// ��ȡ�����Ŀ ��ת �������� ����
		intent = getIntent();
		user = (User) intent.getSerializableExtra("u");

		// ��ǰ �����ϵĿؼ�
		etname = (EditText) findViewById(R.id.etname);
		etprice = (EditText) findViewById(R.id.etprice);
		etdiscription = (EditText) findViewById(R.id.etdiscription);
		iv = (ImageView) findViewById(R.id.iv);

		// ���� �������� ͼƬ ��ͼƬ���Ǵ������� ���������� ��ַ��
		Picasso.with(this).load(user.getImg()).into(iv);

		// ��ʾ������������
		etname.setText(user.getName());
		etprice.setText(user.getPrice());
		etdiscription.setText(user.getDiscription());

		// �޸İ�ť�Ŀؼ�����
		bt = (Button) findViewById(R.id.btok);

		// ����޸İ�ť�ĵ������
		bt.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		// ���ȷ�ϰ�ť��ʱ�� Ҫ��ȡ�������޸ĺõ����� �����浽��ͼ(user)��
		String name = etname.getText().toString();
		String price = etprice.getText().toString();
		String discription = etdiscription.getText().toString();

		// �޸ĺã����浽user������
		user.setName(name);
		user.setPrice(price);
		user.setDiscription(discription);

		// ��user���浽 intent ��
		intent.putExtra("u", user);

		// ���ý������Ӧ��
		setResult(20, intent);

		// �رյ�ǰҳ��
		finish();

	}

}


