// ListView �����ļ�

 <!-- �����ļ� -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingLeft="20dp"
    android:paddingRight="20dp" >

	<ListView 
	    android:id="@+id/lv"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content">
	    
	</ListView>

    
</LinearLayout>


// TextView �����ļ�

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >
    
	<TextView 
	    android:id="@+id/tv"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:textColor="#ff0000"
	    android:background="@drawable/cache_clear"
	    android:textSize="25sp"
	    android:text="����һ���ı�"/>
    
    
</LinearLayout>


// java�ļ�

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		//��ȡListView���� ��  TextView����
		ListView lv = (ListView) findViewById(R.id.lv);
		TextView tv = (TextView) findViewById(R.id.tv);
		
		//����Ҫ����ListView�е���ʾ������
		String [] s = {"����","ʯ��","������"};
		
		//����ListView������ListViewAdapter(��һ���ӿ�)
		//context, ������
		//resource, ʹ�õ�xml�ļ�
		//textViewResourceId, ʹ�õ����
		//objects	���������
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.text, R.id.tv, s);
		
		lv.setAdapter(adapter);
		
		
		
	}

}


