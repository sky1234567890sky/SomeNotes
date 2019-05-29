网络解析数据，并添加到列表中，设置一个修改页面

// xml文件

1.activity_main.xml	(主页面)

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
        android:text="汽车分享"
        android:textSize="30sp" />

    <ListView
        android:id="@+id/lv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >
    </ListView>

</LinearLayout>

2.activity_up.xml (修改页面)

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
        android:text="修改信息"
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
        android:text="确认修改" />

</LinearLayout>

3.item.xml (列表布局页面)

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
                android:text="名字："
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
                android:text="汽车信息："
                android:textSize="20sp" />

            <TextView
                android:id="@+id/tvdiscrip"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="小红车"
                android:textSize="20sp" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="20dp"
                android:text="价格："
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

// java文件

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
	// 自定义一个下标变量，用来存储下标
	private int index = 0;

	// new一个Handler类
	Handler handler = new Handler() {

		// 重写接收信息的方法
		public void handleMessage(android.os.Message msg) {

			// 接收数据
			infos = (ArrayList<User>) msg.obj;

			// 创建适配器
			adapter = new MyAdapter(MainActivity.this, infos);

			// 绑定适配器
			lv.setAdapter(adapter);

		};

	};
	private AlertDialog create;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取列表的对象
		lv = (ListView) findViewById(R.id.lv);

		// 网络请求，解析数据
		getHttpData();

		// 条目长按监听
		lv.setOnItemLongClickListener(this);

		// 条目点击监听
		lv.setOnItemClickListener(this);

		// 创建一个弹窗
		create = new AlertDialog.Builder(this).setTitle("是否删除").setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

				// 删除 操作 集合 （适配器里面的集合）
				infos.remove(index);
				// 刷新适配器
				adapter.notifyDataSetChanged();

			}
		}).setNegativeButton("取消", null).create();

	}

	public void getHttpData() {

		// 创建一个子线程
		new Thread() {

			// 重写run()方法
			public void run() {

				// 网络请求，解析数据
				try {

					URL url = new URL("http://192.168.1.10:8080/anzhuochu_jpg-json/day12.txt");
					// 转化为HttpURLConnection类
					HttpURLConnection open = (HttpURLConnection) url.openConnection();

					// 判断网络请求是否为200
					if (open.getResponseCode() == 200) {

						// 创建一个字符串缓冲区对象，用于存入解析后的数据
						StringBuffer sb = new StringBuffer();

						// 通过网络对象，获取输入流
						InputStream is = open.getInputStream();

						// 转换为字符读取缓冲流
						BufferedReader br = new BufferedReader(new InputStreamReader(is));

						// 定义一个空值
						String len = null;

						// 循环读取每一行，判断是否为null
						while ((len = br.readLine()) != null) {

							// 如果不为空，放入到字符串缓冲区中
							sb.append(len);

						}

						// 打印Log信息
						Log.i("tag", "===>" + sb.toString());

						// 网络解析
						Gson gson = new Gson();
						Demo fromJson = gson.fromJson(sb.toString(), Demo.class);
						ArrayList<User> infos = fromJson.getInfos();

						// 发送的数据 是通过 Message 进行封装的
						Message message = new Message();
						// 把数据存储到信息中
						message.obj = infos;
						// 发送信息
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

		// 长按点击删除数据，要操作集合 需要下标

		// 记录点击的条目的下标
		index = position;

		// 显示弹窗
		create.show();

		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		// 点击监听，获取点击条目的数据
		User user = infos.get(position);

		// 记录 点击的条目的小标
		index = position;

		// 跳转 传值 在新的界面显示 新的界面可以修改数据
		Intent intent = new Intent(MainActivity.this, UpActivity.class);
		intent.putExtra("u", user);

		// 待回传值的方式 跳转
		startActivityForResult(intent, 10);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		// 判断请求码，与响应码是否符合
		if (requestCode == 10 && resultCode == 20) {

			// 接收回传的值
			User user = (User) data.getSerializableExtra("u");

			// 修改适配器集合中的数据 user.set(mark,user)
			infos.set(index, user);

			// 刷新适配器
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

		// 加载 图片 使用框架加载图片 别忘了 导包
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

		// 获取点击条目 跳转 传过来的 数据
		intent = getIntent();
		user = (User) intent.getSerializableExtra("u");

		// 当前 界面上的控件
		etname = (EditText) findViewById(R.id.etname);
		etprice = (EditText) findViewById(R.id.etprice);
		etdiscription = (EditText) findViewById(R.id.etdiscription);
		iv = (ImageView) findViewById(R.id.iv);

		// 加载 传过来的 图片 （图片不是传过来的 传过来的是 网址）
		Picasso.with(this).load(user.getImg()).into(iv);

		// 显示传过来的数据
		etname.setText(user.getName());
		etprice.setText(user.getPrice());
		etdiscription.setText(user.getDiscription());

		// 修改按钮的控件对象
		bt = (Button) findViewById(R.id.btok);

		// 点击修改按钮的点击监听
		bt.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		// 点击确认按钮的时候 要获取界面上修改好的数据 并保存到意图(user)中
		String name = etname.getText().toString();
		String price = etprice.getText().toString();
		String discription = etdiscription.getText().toString();

		// 修改好，保存到user对象中
		user.setName(name);
		user.setPrice(price);
		user.setDiscription(discription);

		// 把user保存到 intent 上
		intent.putExtra("u", user);

		// 设置结果（响应）
		setResult(20, intent);

		// 关闭当前页面
		finish();

	}

}


