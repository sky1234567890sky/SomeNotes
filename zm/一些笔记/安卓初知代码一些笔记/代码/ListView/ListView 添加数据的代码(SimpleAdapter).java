// ListView�ļ�

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
	    android:layout_height="wrap_content"
	    />
	    

    
</LinearLayout>


// �����ļ� (item ��Ŀ)

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >
    

    <ImageView 
        android:id="@+id/iv1"
        android:layout_width="70dp"
        android:layout_height="70dp"
        android:src="@drawable/cache_clear"/>
    
    <TextView
        android:id="@+id/tv1" 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="���ǵ�һ���ı�"
        android:textSize="30dp"
        android:layout_toRightOf="@id/iv1"
        />
    
    <TextView
        android:id="@+id/tv2" 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="���ǵڶ����ı�"
        android:textSize="30dp"
        android:layout_toRightOf="@id/iv1"
        android:layout_alignBottom="@id/iv1"
        />
    
</RelativeLayout>


//java�ļ�

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//��ȡListView����
		ListView lv = (ListView) findViewById(R.id.lv);
		
		//������ListView���Ǹ���ʾ������
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("src", R.drawable.cache_clear);
		map1.put("text1", "���ǵ�һ���ı�");
		map1.put("text2", "�ҽй���");
		list.add(map1);
		
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("src", R.drawable.cache_clear);
		map2.put("text1", "���ǵ�һ���ı�");
		map2.put("text2", "�ҽ�ʯ��");
		list.add(map2);
		
		HashMap<String, Object> map3 = new HashMap<String, Object>();
		map3.put("src", R.drawable.cache_clear);
		map3.put("text1", "���ǵ�һ���ı�");
		map3.put("text2", "�ҽ�������");		
		list.add(map3);
		
		
		String [] from = {"src","text1","text2"};
		int [] to = {R.id.lv,R.id.tv1,R.id.tv2};
		
		//��������������
		
		//context,	������ 
		//data,	ʹ�õ�����
		//resource, ʹ�õĲ����ļ�
		//from,	���ϼ�ֵ�����鼯
		//to ���ֵ��Ӧ��λ��
		SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, list, R.layout.item, from, to);
		
		
		//����������ӽ���ListView������
		lv.setAdapter(adapter);
		
	}


}



