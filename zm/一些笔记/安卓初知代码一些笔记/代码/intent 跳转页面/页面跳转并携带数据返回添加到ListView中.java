ҳ����ת��Я�����ݷ�����ӵ�ListView��

// �����ļ� (Main ��һ��ҳ��Ĳ���)

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
	    ></ListView>
	    

    
</LinearLayout>

// �����ļ� (Second �ڶ���ҳ��Ĳ���,����������ݵ�)

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
        android:id="@+id/et"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:hint="������Ҫ��ӵ�ListView�е�����"/>

    <Button 
        android:id="@+id/bt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/et"
        android:layout_alignRight="@id/et"
        android:text="���"/>

</RelativeLayout>


// ��Ŀ�ļ� (item ���������С��,һ��ʼ��ӵ�����)

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

        <TextView 
        android:id="@+id/tv"
        android:layout_width="match_parent"
    	android:layout_height="match_parent"
    	android:textSize="35sp"
        />

</RelativeLayout>


// java�ļ�  (Main  ��һ�������޸ĵ�)

public class MainActivity extends Activity {

	private ArrayList<String> list;
	private MyAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		//��ʾһ��ListView ����������,����ת���ڶ���ҳ��
		//�ڶ���ҳ��,��һ��EditText,���������Ժ�,�����ť���
		//�Զ���EditText�е��ַ�����ӵĵ�һ��ҳ���ListView��
		
		
		
		//����ListView��Ҫ��ʾ������
		ArrayList<String> list = new ArrayList<String>();
		list.add("�����");
		list.add("�׹Ǿ�");
		list.add("������");
		list.add("ɳ����");
		list.add("������");
		list.add("��ɮ");
		//�����Զ���Adapter����
		adapter = new MyAdapter(MainActivity.this, R.layout.item, list);
		
		//��ȡListView����
		ListView lv = (ListView) findViewById(R.id.lv);
		
		//����������ӽ���ListView
		lv.setAdapter(adapter);
		
		//��������ת���ڶ���ҳ��,����������
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// ��ת���ڶ���ҳ��,��������
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				startActivityForResult(intent, 10);
				return false;
			}
		});
		
		
	}
	
	//��д����,��������
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==10 && resultCode==20) {
			//��ȡ��ͼЯ������������
			String string = data.getStringExtra("mes");
			//�Ѵ�������ӽ�ListView��
			adapter.add(string);
		}
	
	}

}


// java�ļ� (MyAdapter ������)

public class MyAdapter extends ArrayAdapter<String> {

	public MyAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		//����Holder��
		Holder holder = null;
		//���������Ϊ��
		if (convertView == null) {'
			//��ȡָ�����ֵĶ���,item
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent,false);
			
			holder = new Holder();
			//��ָ�������л�ȡָ�����������
			holder.tv = (TextView) convertView.findViewById(R.id.tv);
			//���ò��ָ�ֵ
			convertView.setTag(holder);
		}else {
			//�����ֻ�ȡֵ ��ֵ ��holder����
			holder = (Holder) convertView.getTag();
		}
		
		//�����Ŀ��ʾ������
		String item = getItem(position);
		//���������������
		holder.tv.setText(item);
		
		//���ػ᲼��
		return convertView;

	}
	//�Զ�����,�����Ż�����,���ظ�ʹ��item�����е�TextView��
	class Holder{
		TextView tv;
	}
	
}


// java�ļ� (SecondActivity  �ڶ��������޸�)

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		//������ť,����EditText�е����ݷ��ظ���һ��ҳ��
		Button bt = (Button) findViewById(R.id.bt);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//��ȡEditText�е�����
				EditText et = (EditText) findViewById(R.id.et);
				String string = et.getText().toString();
				//����Я�����ݵ���ͼ
				Intent intent = new Intent();
				intent.putExtra("mes", string);
				setResult(20, intent);
				
				//������ҳ����ת����һ��ҳ��
				finish();
				
			}
		});
		
		
	}


}




