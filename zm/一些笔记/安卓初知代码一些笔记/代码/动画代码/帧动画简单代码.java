帧动画

// xml 文件

anim.xml

/* 1. 创建文件夹drawable，并在drawable文件夹中创建一个xml文件
注意：xml根标签必须是：<animation-list> <animation-list/>
里面的属性有：android:oneshot 设置图片是否只运行一次 */

<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="false" >

    <item
        android:drawable="@drawable/a"
        android:duration="80"/>
    <item
        android:drawable="@drawable/b"
        android:duration="80"/>
    <item
        android:drawable="@drawable/c"
        android:duration="80"/>
    <item
        android:drawable="@drawable/d"
        android:duration="80"/>
    <item
        android:drawable="@drawable/e"
        android:duration="80"/>
    <item
        android:drawable="@drawable/f"
        android:duration="80"/>
    <item
        android:drawable="@drawable/g"
        android:duration="80"/>
    <item
        android:drawable="@drawable/h"
        android:duration="80"/>
    <item
        android:drawable="@drawable/i"
        android:duration="80"/>
    <item
        android:drawable="@drawable/j"
        android:duration="80"/>
    <item
        android:drawable="@drawable/k"
        android:duration="80"/>

</animation-list>


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
    tools:context="com.example.animation.MainActivity" >

    <Button
        android:id="@+id/bt_start"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="开始动画" />

    <Button
        android:id="@+id/bt_end"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="结束动画" />

    <ImageView
        android:id="@+id/iv"
        android:layout_width="300dp"
        android:layout_height="100dp"
        android:layout_gravity="center"
        android:background="@drawable/item" />

</LinearLayout>


// java文件

MainActivity.java

public class MainActivity extends Activity implements OnClickListener {

	private Button bt_start;
	private Button bt_end;
	private ImageView iv;
	private AnimationDrawable animal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 调用方法
		bindView();

		// 使用 AnimationDrawable类，调用start() 和 stop() 方法
		animal = (AnimationDrawable) iv.getBackground();

	}

	private void bindView() {

		// 获取所有的控件对象
		bt_start = (Button) findViewById(R.id.bt_start);
		bt_end = (Button) findViewById(R.id.bt_end);
		iv = (ImageView) findViewById(R.id.iv);

		// 设置按钮的点击事件
		bt_start.setOnClickListener(this);
		bt_end.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		
		// 开始按钮
		case R.id.bt_start:

			animal.start();

			break;

		// 结束按钮	
		case R.id.bt_end:

			animal.stop();

			break;

		default:
			break;
		}

	}

}





