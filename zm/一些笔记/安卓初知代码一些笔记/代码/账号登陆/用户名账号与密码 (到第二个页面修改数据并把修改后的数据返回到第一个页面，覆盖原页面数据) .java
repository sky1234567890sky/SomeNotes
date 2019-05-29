用户名账号与密码 (到第二个页面修改数据并把修改后的数据返回到第一个页面，覆盖原页面数据) 

步骤：

	xml布局：
	//在第一个页面布局文件中创建一个ListView的布局文件
	//创建一个item布局（设计要存入ListView中的格式）
	//在第二页面的布局文件中创建两个可以存入数据并可修改的EditText组件
		

	第一个页面的java文件：
	1.在java文件中获取ListView的对象
	2.创建要添加的数据
	3.创建自定义的适配器
	4.把适配器放入到指定的ListView中
	5.使用长按监听，跳转到第二个页面，并把数据传过去
	6.重写onActivityResult()方法，接收来自第二个页面的数据

	
	实例化的对象(User)：需要实现序列化类(Serializable)
	1.创建两个指定要存储数据的属性
	2.一直快捷键下去
	
	
	自定义的适配器java文件：
	1.继承 ArrayAdapter<User> 适配器
	2.设置一个有参方法 public MyAdapter(Context context,int resource,List<User> objects)
	3.重写一个方法 getView(int position,View convertView,ViewGroup parent)
	4.在getView()方法中 使用指定的item布局，获取指定布局的所有组件，
				 然后通过索引获取指定的item，并给以上组件赋值，返回给容器
	
	
	
	第二个页面的java文件：
	1.接收来自第一个页面的数据，
	2.并显示到EditText中	(获取指定的对象，并设置数据)
	3.监听按钮，把EditText中修改后的数据返回到第一个页面中
	
	

第一个页面的布局文件：
	
	<ListView
        android:id="@+id/lv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
		
		
条目布局文件：	

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
		
		
第二个页面的布局文件：	

	<EditText
        android:id="@+id/et_user"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="请设置账号" />

    <EditText
        android:id="@+id/et_password"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="请设置密码" />
    
    
    <Button 
        android:id="@+id/bt"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="确认修改"/>


第一个页面的java文件：

public class MainActivity extends Activity {


	
	private ArrayList<User> list;
	private int index;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//创建一个ListView的布局文件
		//创建一个item布局（设计要存入ListView中的格式）
		//在第二页面的布局文件中创建两个可以存入数据并可修改的EditText组件
		

		//在java文件中获取ListView的对象
		ListView lv = (ListView) findViewById(R.id.lv);
		
		//创建要添加的数据
		list = new ArrayList<User>();
		list.add(new User("123", "456"));
		list.add(new User("123", "456"));
		list.add(new User("123", "456"));
		
		//创建自定义的适配器
		adapter = new MyAdapter(MainActivity.this, R.layout.item, list);
		
		//把适配器放入到指定的ListView中
		lv.setAdapter(adapter);
		
		//使用长按监听，跳转到第二个页面，并把数据创过去
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				index = position;
				
				
				//跳转到第二个页面的意图
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				//获得Bundle的对象，用来存储数据
				Bundle bundle = new Bundle();
				//存储指定的序列化的数据
				bundle.putSerializable("user", list.get(position));
				
				//把输入放到意图中
				intent.putExtras(bundle);
				
				//开始跳转页面，并携带数据的意图传过去
				startActivityForResult(intent, 10);
				
				return false;
			}
		});
		
	}
	
	//重写onActivityResult()方法，接收来自第二个页面的数据
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		//requestCode  请求码  resultCode  结果码
		if (requestCode==10 && resultCode ==20) {
			
			//获取携带过来的数据
			Bundle bundle = data.getExtras();
			User user = (User) bundle.getSerializable("user2");
			
			//把修改后的数据添加到指定的索引处，并使用适配器通知数据进行互换
			list.set(index, user);
			adapter.notifyDataSetChanged();
			
		}
		
	}		
		
		
实现类User的java文件：

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
	
	
自定义的适配器java文件：	

public class MyAdapter extends ArrayAdapter<User> {

	public MyAdapter(Context context, int resource, List<User> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
		Holder holder = null;
		
		//如果转换视图为空时
		if (convertView == null) {
			
			//获取Holder类的对象
			holder = new Holder();
			
			//获得要添加的布局的对象，赋值给转换布局
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
			
			//通过以上对象获得所有子组件对象，并赋值给 holder类中对应的属性中
			holder.tv1 = (TextView) convertView.findViewById(R.id.tv1);
			holder.tv2 = (TextView) convertView.findViewById(R.id.tv2);
			
			//把Holder对象存入到 convertView 转换视图中
			convertView.setTag(holder);
			
		}else {
			//如果转换布局不为null时，获取指定的布局
			holder = (Holder) convertView.getTag();
		}
		
		//通过指定的索引获取数据
		User user = getItem(position); 
		
		//并分配给布局中指定的组件
		holder.tv1.setText(user.getName());
		holder.tv2.setText(user.getPassword());;
		
		return convertView;
	}
	
	public class Holder{
		TextView tv1;
		TextView tv2;
	}
	
}


第二个页面的java文件：

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		// 接收来自第一个页面的数据，
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		User user = (User) bundle.getSerializable("user");

		// 并显示到EditText中
		final EditText etuser = (EditText) findViewById(R.id.et_user);
		final EditText etpassword = (EditText) findViewById(R.id.et_password);
		etuser.setText(user.getName());
		etpassword.setText(user.getPassword());

		// 监听按钮，把EditText中修改后的数据返回到第一个页面中
		Button bt = (Button) findViewById(R.id.bt);

		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// 先把user对象获取到，并放入意图中
				String user = etuser.getText().toString();
				String password = etpassword.getText().toString();
				User user2 = new User(user, password);

				Bundle bundle2 = new Bundle();
				bundle2.putSerializable("user2", user2);
				Intent data = new Intent();
				data.putExtras(bundle2);

				// 设置返回数据
				setResult(20, data);

				// 结束此页面
				finish();
			}
		});
	}

}


	
	