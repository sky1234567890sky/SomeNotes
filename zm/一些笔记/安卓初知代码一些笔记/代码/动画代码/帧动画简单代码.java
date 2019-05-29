֡����

// xml �ļ�

anim.xml

/* 1. �����ļ���drawable������drawable�ļ����д���һ��xml�ļ�
ע�⣺xml����ǩ�����ǣ�<animation-list> <animation-list/>
����������У�android:oneshot ����ͼƬ�Ƿ�ֻ����һ�� */

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
        android:text="��ʼ����" />

    <Button
        android:id="@+id/bt_end"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="��������" />

    <ImageView
        android:id="@+id/iv"
        android:layout_width="300dp"
        android:layout_height="100dp"
        android:layout_gravity="center"
        android:background="@drawable/item" />

</LinearLayout>


// java�ļ�

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

		// ���÷���
		bindView();

		// ʹ�� AnimationDrawable�࣬����start() �� stop() ����
		animal = (AnimationDrawable) iv.getBackground();

	}

	private void bindView() {

		// ��ȡ���еĿؼ�����
		bt_start = (Button) findViewById(R.id.bt_start);
		bt_end = (Button) findViewById(R.id.bt_end);
		iv = (ImageView) findViewById(R.id.iv);

		// ���ð�ť�ĵ���¼�
		bt_start.setOnClickListener(this);
		bt_end.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		
		// ��ʼ��ť
		case R.id.bt_start:

			animal.start();

			break;

		// ������ť	
		case R.id.bt_end:

			animal.stop();

			break;

		default:
			break;
		}

	}

}





