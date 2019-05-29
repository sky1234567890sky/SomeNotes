记住密码的代码

// xml文件
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
        android:hint="请输入账号" />

    <EditText
        android:id="@+id/etpaw"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="请输入密码" />

    <CheckBox
        android:id="@+id/cb"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="记住密码" />

    <Button
        android:id="@+id/btdenglu"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="登录" />

</LinearLayout>

// java文件
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

		// 调用获取控件对象的方法
		getView();

		// 获取app中的数据的类 SharedPreferences
		SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);

		// 判断保存的勾选的状态的值
		boolean iscb = sp.getBoolean("iscb", false);

		if (iscb) {

			// 保存过
			// 要显示 密码和账号 勾选状态
			String sp_name = sp.getString("n", "");
			String sp_paw = sp.getString("p", "");

			// 显示 设置到控件上
			etname.setText(sp_name);
			etpaw.setText(sp_paw);

		} else {

			// 没有选中
			// 显示账号
			String sp_name = sp.getString("n", "");

			// 显示到控件上
			etname.setText(sp_name);

		}
		cb.setChecked(iscb); // 是true 是false 都需要设置

		btdenglu.setOnClickListener(this);

	}

	// 获取控件对象
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
		 * 1.点击登录按钮时，去处界面上的 勾选状态， 进行判断
		 * 
		 * 2.如果选中，获取界面上的账号与密码，保存起来
		 * 
		 * 3.如果没有选中，把状态保存
		 */

		// 取出界面上的勾选状态
		SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
		Editor edit = sp.edit();
		boolean checked = cb.isChecked();

		// 判断勾选状态
		if (checked) {

			// 如果选中，获取账号与密码
			String name = etname.getText().toString();
			String paw = etpaw.getText().toString();

			// 把账号与密码放入到edit中
			edit.putString("n", name);
			edit.putString("p", paw);

		}
		// 把选中装填放入到edit中
		edit.putBoolean("iscb", checked);

		// 提交数据
		edit.commit();
	}

}

