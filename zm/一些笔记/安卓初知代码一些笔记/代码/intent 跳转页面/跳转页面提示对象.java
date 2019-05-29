
//实现类
package com.example.lianxi1;

import java.io.Serializable;

public class Student implements Serializable {

	private String name;
	private int age;
	private String sex;

	public Student() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Student(String name, int age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
}



//布局文件
activity_main.xml 

 <!-- 布局文件 -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingLeft="20dp"
    android:paddingRight="20dp" >


	<EditText 
	    android:id="@+id/tv"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:hint="请输入数据"/>

	<Button 
	    android:id="@+id/bt"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="跳转"/>
	    

    
</LinearLayout>


activity_second.xml 跳转后的页面布局文件

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.lianxi1.SecondActivity" >

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hello_world" />

</RelativeLayout>


// MainActivity.java	java文件(主 开始页面的修改)


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button bt = (Button) findViewById(R.id.bt);
		// 监听
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				//跳转之前先要把输入框中的文本拿出来
				TextView tv = (TextView) findViewById(R.id.tv);
				String string = tv.getText().toString();
				
				//想携带数据,先创建携带数据的容器
				Bundle bundle = new Bundle();
				//基本数据类型传值
				bundle.putString("mes", "abcd");
				bundle.putInt("age", 22);
				
				Student student = new Student("郭磊",22,"男");
				bundle.putSerializable("stu", student);
				
				//创建意图
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				intent.putExtras(bundle);
				
				
				
				
//				//意图携带数据跳转,只能传递基本数据类型与字符串,不能传递引用数据类型
//				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//				intent.putExtra("mes", string);
				startActivity(intent);
				
				
				
			}
		});
	}

}


// SecondActivity.java		java文件(次 跳转后页面的修改)

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		//获取页面跳转的意图,并取出携带的数据
		Intent intent = getIntent();
//		String string = intent.getStringExtra("mes");
		Bundle bundle = intent.getExtras();
		String string = bundle.getString("mes");
		Student student = (Student) bundle.getSerializable("stu");
		Toast.makeText(SecondActivity.this, student.toString(), Toast.LENGTH_SHORT).show();
		
		
		//得到textView,并重新赋值
		TextView tv = (TextView) findViewById(R.id.tv);
		tv.setText(string);
		tv.setText(student);
		
	}


}





