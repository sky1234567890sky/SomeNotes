
//ʵ����
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



//�����ļ�
activity_main.xml 

 <!-- �����ļ� -->
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
	    android:hint="����������"/>

	<Button 
	    android:id="@+id/bt"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="��ת"/>
	    

    
</LinearLayout>


activity_second.xml ��ת���ҳ�沼���ļ�

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


// MainActivity.java	java�ļ�(�� ��ʼҳ����޸�)


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button bt = (Button) findViewById(R.id.bt);
		// ����
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				//��ת֮ǰ��Ҫ��������е��ı��ó���
				TextView tv = (TextView) findViewById(R.id.tv);
				String string = tv.getText().toString();
				
				//��Я������,�ȴ���Я�����ݵ�����
				Bundle bundle = new Bundle();
				//�����������ʹ�ֵ
				bundle.putString("mes", "abcd");
				bundle.putInt("age", 22);
				
				Student student = new Student("����",22,"��");
				bundle.putSerializable("stu", student);
				
				//������ͼ
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				intent.putExtras(bundle);
				
				
				
				
//				//��ͼЯ��������ת,ֻ�ܴ��ݻ��������������ַ���,���ܴ���������������
//				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//				intent.putExtra("mes", string);
				startActivity(intent);
				
				
				
			}
		});
	}

}


// SecondActivity.java		java�ļ�(�� ��ת��ҳ����޸�)

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		//��ȡҳ����ת����ͼ,��ȡ��Я��������
		Intent intent = getIntent();
//		String string = intent.getStringExtra("mes");
		Bundle bundle = intent.getExtras();
		String string = bundle.getString("mes");
		Student student = (Student) bundle.getSerializable("stu");
		Toast.makeText(SecondActivity.this, student.toString(), Toast.LENGTH_SHORT).show();
		
		
		//�õ�textView,�����¸�ֵ
		TextView tv = (TextView) findViewById(R.id.tv);
		tv.setText(string);
		tv.setText(student);
		
	}


}





