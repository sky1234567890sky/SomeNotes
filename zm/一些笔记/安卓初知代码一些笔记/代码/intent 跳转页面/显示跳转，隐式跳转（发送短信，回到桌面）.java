��ʾ��ת����ʽ��ת,���Ͷ���,�ص�����


// xml �ļ�

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
    tools:context="com.lianxi.demo5.MainActivity" >

    <Button
        android:id="@+id/jump_bt1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="�����ת���ڶ�ҳ��" />

    <Button 
        android:id="@+id/jump_bt2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="��ʽ��ת-ͨ��actionƥ��"
        />

    <Button 
        android:id="@+id/jump_bt3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="��ʽ��ת-�򿪷��Ͷ���ҳ��"
        />
    
    <Button 
        android:id="@+id/jump_bt4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="��ʽ��ת-ͨ��categoryƥ��"
        />
    
</LinearLayout>

2.activity_two.xml(�ڶ���ҳ��)

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
        android:text="�ر�ҳ��" />

</RelativeLayout>


3.activity_three(������ҳ��)

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


4.AndroidManifest.xml(�嵥�ļ�)

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.lianxi.demo5"
    android:versionCode="1"
    android:versionName="1.0" >

    <!-- ���Ӧ��Ȩ�� -->
    <uses-permission android:name="android.permission.SEND_SMS" />

    <uses-sdk
        android:minSdkVersion="19"
        android:targetSdkVersion="21" />

    <application
        android:allowBackup="true"
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/AppTheme" >

        <!-- ����activity -->

        <activity
            android:name=".MainActivity"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <!-- ע��ĵڶ���activity��ҳ������ -->

        <activity
            android:name=".TwoActivity"
            android:label="@string/title_activity_two" >
        </activity>

        <!-- ������ҳ���������������ʽ��ת��ʽ������activity -->

        <activity
            android:name="com.lianxi.demo5.ThreeActivity"
            android:label="@string/title_activity_three" >
            <intent-filter>
                <action android:name="customer_action_here" />
                <!-- ��ʽ��ת����-����дactegory -->
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
    </application>

</manifest>



// java �ļ�

MainActivity.java

public class MainActivity extends Activity implements OnClickListener {

	private Button bt1, bt2, bt3, bt4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 1. ͨ��id��ÿؼ�����
		bt1 = (Button) findViewById(R.id.jump_bt1);
		bt2 = (Button) findViewById(R.id.jump_bt2);
		bt3 = (Button) findViewById(R.id.jump_bt3);
		bt4 = (Button) findViewById(R.id.jump_bt4);

		// 2. ���ؼ����ü����¼�
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);

	}

	/*
	 * ����¼�����ʱ���õķ���
	 */

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) { // ͨ��id�жϴ����Ŀؼ�

		case R.id.jump_bt1: // �������ʾ��ת

			// ����һ����ͼ��������תҳ��ʱ��������
			Intent intent = new Intent(MainActivity.this, TwoActivity.class);
			// ������ͼ����ת���ڶ���ҳ��
			startActivity(intent);

			break;

		case R.id.jump_bt2:// ��ʽ��ת

			// ����һ����ͼ��������תҳ��ʱ��������
			Intent intent2 = new Intent();
			// ����intent�Ķ���Ϊ�嵥��ָ����action
			intent2.setAction("customer_action_here");
			// ������ͼ
			startActivity(intent2);

			break;

		case R.id.jump_bt3: // ��ʽ��ת-���Ͷ���
			// ����һ�����Ͷ��ŵĵ�ַ
			Uri uri = Uri.parse("smsto:15934531976");
			// ����һ����ͼ��������תҳ��ʱ��������
			Intent intent3 = new Intent(Intent.ACTION_SENDTO, uri);
			
			
			// ������ͼ
			startActivity(intent3);

			break;

		case R.id.jump_bt4: // ��ʽ��ת-ͨ��catgoryƥ��-�ص�����

			// ����һ����ͼ��������תҳ��ʱ��������
			Intent intent4 = new Intent();
			// ƥ��ص������action����
			intent4.setAction(Intent.ACTION_MAIN);
			// ���Category����
			intent4.addCategory(Intent.CATEGORY_HOME);
			// ������ͼ
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

		// �رյ�ǰҳ��
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

