解析json文件，存入到ListView列表中

// xml 文件
activity_main.xml	（列表文件）

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


item.xml  （布局文件）

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

// 网页的资源及地址
	
	地址：http:192.168.1.10:8080/anzhuochu/users.json
	
	资源：
{
	"users":[
		{
			"name":"小明",
			"age":"12"
		},
		{
			"name":"小李",
			"age":"12"
		},
		{
			"name":"小刚",
			"age":"12"
		}
	
	]
}
	
// 实现类
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

// 适配器
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


// 运行
MainActivity.java

public class MainActivity extends Activity {

	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			// 接收数据
			ArrayList<User> list = (ArrayList<User>) msg.obj;

			// 创建适配器
			MyAdapter adapter = new MyAdapter(MainActivity.this, list);

			// 绑定适配器
			lv.setAdapter(adapter);

		};

	};

	// 网络地址 http:192.168.1.10:8080/anzhuochu/users.txt

	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lv = (ListView) findViewById(R.id.lv);

		getThread();

	}

	// 创建一个子线程
	public void getThread() {
		// TODO Auto-generated method stub

		new Thread() {

			public void run() {

				// 设置耗时操作
				URL url;
				// 获取网略中的数据
				try {
					// 设置要访问的网络的地址
					URL url = new URL("http://192.168.1.10:8080/anzhuochu/users.json");
					HttpURLConnection open = (HttpURLConnection) url.openConnection();

					// 判断该网页是否可访问
					if (open.getResponseCode() == 200) {

						// 创建一个缓冲区字符串
						StringBuffer sb = new StringBuffer();
						// 获得输入流
						InputStream is = open.getInputStream();
						// 获取缓冲输入流
						BufferedReader br = new BufferedReader(new InputStreamReader(is));

						// 定义一个变量为null
						String len = null;

						// 当读取一行不为null时
						while ((len = br.readLine()) != null) {

							// 拼接字符串
							sb.append(len);

						}

						// Log打印
						Log.i("tag", "====>" + sb.toString());

						// 网络解析
						Gson gson = new Gson();
						Demo fromJson = gson.fromJson(sb.toString(), Demo.class);
						ArrayList<User> users = fromJson.getUsers();

						// 发送数据 调用Message对象
						Message message = new Message();

						// 存入数据
						message.obj = users;

						// 发送数据
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





