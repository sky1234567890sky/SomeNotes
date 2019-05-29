SharedPreferences 存储数据

// xml文件
activity_main.xml

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.sp.MainActivity" >

    <Button
        android:id="@+id/bt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hello_world" />

    <Button
        android:id="@+id/btget"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="get" />

</LinearLayout>

// java文件

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.bt).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// sp   首选项
				
				// 创建一个  文件    xml   手机  APP    存值方式   键值对    map  
				// user.xml
				SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);

				// 存 文件 对文件编辑
				Editor edit = sp.edit(); // 获取 文件的编辑器
				// map
				edit.putString("1", "abc");
				edit.putString("2", "11111");

				// 别忘了 提交 ctrl + s
				edit.commit();

				// name 文件的名字 mode 模式 权限

				// 文件操作 （存 取）

			}
		});

		findViewById(R.id.btget).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// 存了什么就取什么
				// 获得操作 对象
				SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);

				// 取 存了什么就取什么
				String string = sp.getString("1", "");

				String string2 = sp.getString("2", "");

				Log.i("tag", "string ===>" + string);
				Log.i("tag", "string2 ===>" + string2);
			}
		});

	}

}


