

// �����ļ� (Main����)

 <!-- �����ļ� -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingLeft="20dp"
    android:paddingRight="20dp" >


	<TextView 
	    android:id="@+id/tv"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="@string/hello_world"/>
	
	<Button 
	    android:id="@+id/bt"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="��ת"/>
	    

    
</LinearLayout>


// �����ļ� (Second �β���)

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.lianxi1.SecondActivity" >

    <EditText 
        android:id="@+id/et_2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:hint="���������һ��ҳ�������"/>

    <Button 
        android:id="@+id/bt_2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/et_2"
        android:layout_alignRight="@id/et_2"
        android:text="��ת"/>

</RelativeLayout>


// java�ļ� (���ļ� MainActivity)

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//�ɴ�ҳ����ת��SecondActivity,��SecondActivity����������,�����ش�Activity
		//�ڴ˽����Second�е�������ʾ������
		
		
		//������ť,����second
		Button bt = (Button) findViewById(R.id.bt);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//�����ڶ���ҳ��
				//��ͼ
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				//�����ڶ���ҳ��
				//startActivity(intent)
				//��Ҫ�ӵڶ���ҳ�淵������
				startActivityForResult(intent, 10);
				
			}
		});
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==10 && resultCode==20) {
			//��ȡ��ͼЯ������������
			String string = data.getStringExtra("mes");
			TextView tv = (TextView) findViewById(R.id.tv);
			tv.setText(string);
		}
	
	}

}


//java�ļ� (SecondActivity ��һ����תҳ����޸�)

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		

		//������ť
		Button bt2 = (Button) findViewById(R.id.bt_2);
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				EditText et2 = (EditText) findViewById(R.id.et_2);
				String string = et2.getText().toString();
				Intent intent = new Intent();
				intent.putExtra("mes", string);
				setResult(20, intent);
				//�����˽���
				finish();
			}
		});
		
	}

}



