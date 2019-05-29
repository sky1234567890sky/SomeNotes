����json�ļ������뵽ListView�б���

// xml �ļ�
activity_main.xml	���б��ļ���

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.handler_list.MainActivity" >

    <ListView
        android:id="@+id/lv"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>


item.xml  �������ļ���

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <TextView
        android:id="@+id/tv_name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="25sp" />


    <TextView
        android:id="@+id/tv_age"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="25sp" />

</LinearLayout>

// ��ҳ����Դ����ַ
	
	��ַ��http:192.168.1.10:8080/anzhuochu/users.json
	
	��Դ��
{
	"users":[
		{
			"name":"С��",
			"age":"12"
		},
		{
			"name":"С��",
			"age":"12"
		},
		{
			"name":"С��",
			"age":"12"
		}
	
	]
}
	
// ʵ����
Demo.java

public class Demo {

	ArrayList<User> users;

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

}
	
User.java

public class User {

	String name;
	String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}

// ������
MyAdapter.java

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
			holder = new ViewHolder();

			convertView = LayoutInflater.from(context).inflate(R.layout.item, null);

			holder.tvname = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tvage = (TextView) convertView.findViewById(R.id.tv_age);

			convertView.setTag(holder);

		} else {

			holder = (ViewHolder) convertView.getTag();

		}

		holder.tvname.setText(list.get(position).getName());
		holder.tvage.setText(list.get(position).getAge());

		return convertView;
	}

	class ViewHolder {

		TextView tvname;
		TextView tvage;

	}

}


// ����
MainActivity.java

public class MainActivity extends Activity {

	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			// ��������
			ArrayList<User> list = (ArrayList<User>) msg.obj;

			// ����������
			MyAdapter adapter = new MyAdapter(MainActivity.this, list);

			// ��������
			lv.setAdapter(adapter);

		};

	};

	// �����ַ http:192.168.1.10:8080/anzhuochu/users.txt

	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lv = (ListView) findViewById(R.id.lv);

		getThread();

	}

	// ����һ�����߳�
	public void getThread() {
		// TODO Auto-generated method stub

		new Thread() {

			public void run() {

				// ���ú�ʱ����
				URL url;
				// ��ȡ�����е�����
				try {
					// ����Ҫ���ʵ�����ĵ�ַ
					URL url = new URL("http://192.168.1.10:8080/anzhuochu/users.json");
					HttpURLConnection open = (HttpURLConnection) url.openConnection();

					// �жϸ���ҳ�Ƿ�ɷ���
					if (open.getResponseCode() == 200) {

						// ����һ���������ַ���
						StringBuffer sb = new StringBuffer();
						// ���������
						InputStream is = open.getInputStream();
						// ��ȡ����������
						BufferedReader br = new BufferedReader(new InputStreamReader(is));

						// ����һ������Ϊnull
						String len = null;

						// ����ȡһ�в�Ϊnullʱ
						while ((len = br.readLine()) != null) {

							// ƴ���ַ���
							sb.append(len);

						}

						// Log��ӡ
						Log.i("tag", "====>" + sb.toString());

						// �������
						Gson gson = new Gson();
						Demo fromJson = gson.fromJson(sb.toString(), Demo.class);
						ArrayList<User> users = fromJson.getUsers();

						// �������� ����Message����
						Message message = new Message();

						// ��������
						message.obj = users;

						// ��������
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

}





