步骤：
	1. 在主布局中先设置一个按钮组件，
	2. 在设置一个自定义的布局（包含EditText组件）
	3. java文件中
			通过id值获取主布局中的按钮控件的对象，
			通过按钮的对象，设置点击监听方法
			
			点击监听方法
				获取要添加的子布局的对象（通过getLayoutInflater().inflate()）
				通过子布局的对象调用方法获得其中指定控件的对象(findViewById)
				实例化AlertDialog对象，设置对话框的信息
				最后显示出来，调用show()
				
xml布局

1.activity_main.xml
	
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.lianxi.demo4.MainActivity" >

	<Button 
	    android:id="@+id/bt"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="点我弹窗"
	    />

</LinearLayout>	
				
2.dialog_item.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="账号：" />

    <EditText
        android:id="@+id/et_user"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="请输入账号" />

</LinearLayout>

3.MainActivity.java	

public class MainActivity extends Activity implements OnLongClickListener {

	private final String Tag = "MainActivity";
	private Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt = (Button) findViewById(R.id.bt);

		bt.setOnLongClickListener(this);

	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.bt:

			View in = getLayoutInflater().inflate(R.layout.dialog_item, null);
			final EditText et = (EditText) in.findViewById(R.id.et_user);

			Dialog dialog = new AlertDialog.Builder(MainActivity.this).setTitle("对话框").setView(in)
					.setPositiveButton("提交", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String string = et.getText().toString();
							Log.v(Tag, "输入的内容为：" + string);

						}
					}).create();
			dialog.show();

			break;

		default:
			break;
		}

		return false;
	}

}			