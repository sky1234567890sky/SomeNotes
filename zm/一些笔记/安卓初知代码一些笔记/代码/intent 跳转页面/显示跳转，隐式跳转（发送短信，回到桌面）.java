显示跳转和隐式跳转,发送短信,回到桌面


// xml 文件

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
    tools:context="com.lianxi.demo5.MainActivity" >

    <Button
        android:id="@+id/jump_bt1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="点击跳转到第二页面" />

    <Button 
        android:id="@+id/jump_bt2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="隐式跳转-通过action匹配"
        />

    <Button 
        android:id="@+id/jump_bt3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="隐式跳转-打开发送短信页面"
        />
    
    <Button 
        android:id="@+id/jump_bt4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="隐式跳转-通过category匹配"
        />
    
</LinearLayout>

2.activity_two.xml(第二个页面)

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.lianxi.demo5.TwoActivity" >

    <TextView
        android:id="@+id/tv1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="关闭页面" />

</RelativeLayout>


3.activity_three(第三个页面)

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.lianxi.demo5.ThreeActivity" >

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hello_world" />

</RelativeLayout>


4.AndroidManifest.xml(清单文件)

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.lianxi.demo5"
    android:versionCode="1"
    android:versionName="1.0" >

    <!-- 添加应用权限 -->
    <uses-permission android:name="android.permission.SEND_SMS" />

    <uses-sdk
        android:minSdkVersion="19"
        android:targetSdkVersion="21" />

    <application
        android:allowBackup="true"
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/AppTheme" >

        <!-- 声明activity -->

        <activity
            android:name=".MainActivity"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <!-- 注册的第二个activity的页面声明 -->

        <activity
            android:name=".TwoActivity"
            android:label="@string/title_activity_two" >
        </activity>

        <!-- 第三个页面的声明，定义隐式跳转方式启动该activity -->

        <activity
            android:name="com.lianxi.demo5.ThreeActivity"
            android:label="@string/title_activity_three" >
            <intent-filter>
                <action android:name="customer_action_here" />
                <!-- 隐式跳转配置-必须写actegory -->
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
    </application>

</manifest>



// java 文件

MainActivity.java

public class MainActivity extends Activity implements OnClickListener {

	private Button bt1, bt2, bt3, bt4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 1. 通过id获得控件对象
		bt1 = (Button) findViewById(R.id.jump_bt1);
		bt2 = (Button) findViewById(R.id.jump_bt2);
		bt3 = (Button) findViewById(R.id.jump_bt3);
		bt4 = (Button) findViewById(R.id.jump_bt4);

		// 2. 给控件设置监听事件
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);

	}

	/*
	 * 点击事件触发时调用的方法
	 */

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) { // 通过id判断触发的控件

		case R.id.jump_bt1: // 界面间显示跳转

			// 创建一个意图，用来跳转页面时传递数据
			Intent intent = new Intent(MainActivity.this, TwoActivity.class);
			// 启动意图，跳转到第二个页面
			startActivity(intent);

			break;

		case R.id.jump_bt2:// 隐式跳转

			// 创建一个意图，用来跳转页面时传递数据
			Intent intent2 = new Intent();
			// 设置intent的动作为清单中指定的action
			intent2.setAction("customer_action_here");
			// 启动意图
			startActivity(intent2);

			break;

		case R.id.jump_bt3: // 隐式跳转-发送短信
			// 创建一个发送短信的地址
			Uri uri = Uri.parse("smsto:15934531976");
			// 创建一个意图，用来跳转页面时传递数据
			Intent intent3 = new Intent(Intent.ACTION_SENDTO, uri);
			
			
			// 启动意图
			startActivity(intent3);

			break;

		case R.id.jump_bt4: // 隐式跳转-通过catgory匹配-回到桌面

			// 创建一个意图，用来跳转页面时传递数据
			Intent intent4 = new Intent();
			// 匹配回到桌面的action属性
			intent4.setAction(Intent.ACTION_MAIN);
			// 添加Category属性
			intent4.addCategory(Intent.CATEGORY_HOME);
			// 启动意图
			startActivity(intent4);
			break;

		default:
			break;
		}

	}

}


TwoActivity.java

public class TwoActivity extends Activity implements OnClickListener {

	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);

		tv = (TextView) findViewById(R.id.tv1);

		tv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		// 关闭当前页面
		finish();

	}

}

ThreeActivity.java

public class ThreeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_three);
	}

}

