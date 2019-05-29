

//activity_main.xml �����ļ�

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


// activity_second.xml ��ת���ҳ�沼���ļ�
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
				
				//��ͼЯ��������ת
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				intent.putExtra("mes", string);
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
		String string = intent.getStringExtra("mes");
		
		//�õ�textView,�����¸�ֵ
		TextView tv = (TextView) findViewById(R.id.tv);
		tv.setText(string);
		
	}


}





