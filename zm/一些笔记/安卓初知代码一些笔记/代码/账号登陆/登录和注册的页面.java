登录和注册的页面

/*

	图一为登录页面，点击注册按钮进入图二页面，输入账号与密码，点击注册按钮返回图一，
将账号密码显示在框内，点击登录按钮，调到图三，显示账号与密码

要求：

	1. 图一登录页面，输入用户名和密码完成登录
	2. 图二注册页面，点击注册按钮将账号与密码回传到第一个登录的页面
	3. 要求判断账号为11位数字，密码为6-12位任意字符
	4. 登录成功后跳至图三，将账号与密码显示到页面上
	
*/


步骤：
	
	一. 先创建三个布局页面：
		登录界面、注册界面、登录成功的界面
		
	二. 登录页面的java文件：
	
		1.获取该布局所有组件的id对象
		2.创建 跳转注册页面的方法 和 跳转登陆成功的页面的方法 与 接收注册页面返回的数据的方法
	
	三. 注册页面的java文件：
		
		1.获取该布局所有组件的id对象
		2.创建注册按钮的点击监听方法
			监听携带携带数据返回到登陆页面
		3.关闭此页面

	四. 登陆成功页面的java文件：
		
		1.获取该布局所有组件的id对象
		2.创建并调用显示数据的方法
			显示数据的方法：
			
				获取intent携带数据的字符串
				然后给TextView组件设置数据


				
三个布局文件：	

	登陆界面布局：

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
            android:text="用户名"/>
        <EditText 
            android:layout_weight="2"
            android:id="@+id/et_User"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:hint="请输入11位数字用户名"/>
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
            android:text="密码"/>
        <EditText 
            android:layout_weight="2"
            android:id="@+id/et_Password"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:hint="请输入6-12位数字密码"/>
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
            android:text="注册"/>
        <Button 
            android:id="@+id/bt_Denlu"
            android:layout_weight="1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="登录"/>
    </LinearLayout>
				
</LinearLayout>

	注册界面布局：

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
            android:text="用户名" />

        <EditText
            android:id="@+id/et_User"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="2"
            android:hint="请输入11位数字用户名" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center" >

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="密码" />

        <EditText
            android:id="@+id/et_Password"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="2"
            android:hint="请输入6-12位数字密码" />
    </LinearLayout>

    <Button
        android:id="@+id/bt_join"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:text="注册" />

</LinearLayout>	
	
	
	登陆成功界面布局：
	
<LinearLayout>

    <TextView
        android:id="@+id/tv_User "
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="账号: " />

    <TextView
        android:id="@+id/tv_Password"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="密码: " />

</LinearLayout>	


登录页面的java文件：

public class MainActivity extends Activity {

	private EditText etUser;
	private EditText etPassword;
	private Button btJoin;
	private Button btDenlu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取第一个布局的所有组件的对象
		etUser = (EditText) findViewById(R.id.et_User);
		etPassword = (EditText) findViewById(R.id.et_Password);
		btJoin = (Button) findViewById(R.id.bt_join);
		btDenlu = (Button) findViewById(R.id.bt_Denlu);

		jianTingZhuCe();// 就跳转到了注册页面内
		denlu(); // 跳转到登陆后的页面
	}

	// 监听注册按钮,如果点击此按钮则跳转到注册页面,并期待有数据从注册页面回传值
	public void jianTingZhuCe() {
		btJoin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// 创建跳转到注册页面的意图
				Intent intent = new Intent(MainActivity.this, Tu2Activity.class);

				// 开始跳转并设置请求码
				startActivityForResult(intent, 10);

			}
		});
	}

	// requestCode 响应码 resultCode 请求码

	// 如果注册页面携带数据返回值我们接受数据,并把数据放入两个EditText中
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		// 如果请求码和响应码分别与设置的值对应
		if (10 == requestCode && resultCode == 20) {

			// 从注册页面返回的数据在data中，通过data调用StringExtra(String name)方法 获取对应的数据值
			String user = data.getStringExtra("user");
			String password = data.getStringExtra("password");

			// 把返回的数据值分别设置给对应的EditText中
			etPassword.setText(password);
			etUser.setText(user);

		}
	}

	// 监听登录按钮,如果点击登录按钮,则意图携带数据跳转页面tu3
	public void denlu() {
		// 监听登录按钮
		btDenlu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// 获取两个EditText中的数据
				String user = etUser.getText().toString();
				String password = etPassword.getText().toString();

				// 需要意图携带数据返回给第一个(上一个 登录的)页面
				Intent intent = new Intent(MainActivity.this, Tu3Activity.class);

				// 设置要携带的数据
				intent.putExtra("user", user);
				intent.putExtra("password", password);

				// 启动第三个页面
				startActivity(intent);

			}
		});
	}

}

注册页面的java文件：

public class Tu2Activity extends Activity {

	private EditText etUser;
	private EditText etPassword;
	private Button btJoin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tu2);

		// 通过id获取控件对象
		etUser = (EditText) findViewById(R.id.et_User);
		etPassword = (EditText) findViewById(R.id.et_Password);
		btJoin = (Button) findViewById(R.id.bt_join);

		// 调用注册监听的方法
		jianTing();

	}

	// 监听注册按钮,携带数据跳转到登陆页面
	public void jianTing() {

		btJoin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 获取两个EditText中的数据
				String user = etUser.getText().toString();
				String password = etPassword.getText().toString();

				// 需要意图携带数据返回给第一个(上一个)页面
				Intent intent = new Intent();
				intent.putExtra("user", user);
				intent.putExtra("password", password);

				// 返回数据
				setResult(20, intent);
				finish(); // 关闭此页面

			}
		});
	}

}

登陆成功页面的java文件：

public class Tu3Activity extends Activity {

	private TextView tvUser;
	private TextView tvPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tu3);

		// 通过id获取所有的控件对象
		tvUser = (TextView) findViewById(R.id.tv_User);
		tvPassword = (TextView) findViewById(R.id.tv_Password);

		// 调用显示的方法
		xianShi();

	}

	// 接收来自登录页面的账号与密码,并显示在两个TextView中
	public void xianShi() {
		// 首先获取原来TextView中的字符串
		String user = tvUser.getText().toString();
		String password = tvPassword.getText().toString();

		// 获取intent携带过来的字符串
		Intent intent = getIntent();
		user = user + intent.getStringExtra("user");
		password = password + intent.getStringExtra("password");

		// 字符串拼接,并重新设置给TextView
		tvUser.setText(user);
		tvPassword.setText(password);
	}
}


