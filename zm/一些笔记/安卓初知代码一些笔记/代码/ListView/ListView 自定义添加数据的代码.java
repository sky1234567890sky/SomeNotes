����:
1.��xml�ļ��ж���ListView��ǩ
2.����һ������item,�����д���ListView��Ҫ��ʾ����Ŀ����
3.����һ����̳�BaseAdapter,��ʵ�ֳ������е��ĸ����󷽷�
	����Ҫ��ListView����ʾ�����ݴ��ݸ���������
	BaseAdapter�еĳ��󷽷�
		public int getCount()	//�˷������ؾ�����ListView�����˶��ٸ���Ŀ
		public Object getItem(int arg0)	//��������������Ҫ��ָ��λ����ʾ�����ݶ���
		public Long getItemId(int arg0)	//�˷��� ������������id
		
		//ÿ����һ����Ŀ�ͻ����һ���������,�ڴ˷�����ָ��������Ŀ����ʽ,
		//����Ҫ��ʾ����Ӧ������ӵ���Ӧ����Ŀ�ؼ���
		//postion ��ʾ��һ�ν�Ҫ���Ƶڼ�����Ŀ
		//contextView Ĭ��ֵΪnull,���Ǹ������¸�ֵ,����ָ����Ŀ��ʽ
		//����ȡ��Ŀ�����пؼ�,����Ӧ���Ը�ֵ		
		public View getView(int position,View contextView,ViewGroup parent)
4.�����Զ���Adapter����
5.���Զ���Adapter������ӽ���ListView������

//ListView �����ļ�

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


//item �����ļ�(��Ŀ)

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >
    
	<TextView 
	    android:id="@+id/tv"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:textSize="50sp"
	    android:text="dafdsl"
	    />
	
</LinearLayout>


//java�ļ� ������(MyAdapter)

public class MyAdapter extends BaseAdapter {

/*��д�ķ���
   getCount��������ListView�б�ĳ���
   getItem(int position)����ÿ��item��ͼ��Ӧ��ʵ�������
   getItemId(int position) ����ÿ��item��ͼ��Ӧ������
   getView(int position, View convertView, ViewGroup parent) ����ÿ��item��ͼ*/

	
	
	private Context context;
	private ArrayList<String> list;
	
	
	
	public MyAdapter(Context context, ArrayList<String> list) {
		super();
		this.context = context;
		this.list = list;
	}

	//�˷��� ����ֵ����ListViewҪ�������ٸ��������б�
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	//�˷��� �������������ListView�е�arg0����ʾ���б�
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	//�˷��� ������������id
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

    @Override
	//ÿ����һ����Ŀ�ͻ����һ���������,�ڴ˷�����ָ��������Ŀ����ʽ,
    //����Ҫ��ʾ����Ӧ������ӵ���Ӧ����Ŀ�ؼ���
    //postion ��ʾ��һ�ν�Ҫ���Ƶڼ�����Ŀ
    //contextView Ĭ��ֵΪnull,���Ǹ������¸�ֵ,����ָ����Ŀ��ʽ
	//����ȡ��Ŀ�����пؼ�,����Ӧ���Ը�ֵ
	public View getView(int position, View contextView, ViewGroup parent) {
		// TODO Auto-generated method stub
    	
    	// ��ȡָ������ֵ������
		String string = list.get(position);

		if (contextView == null) {
			// ͨ��LayoutInflater ��� from ���� �� ʹ�� inflate()�����õ�ָ���Ĳ���
			// �õ�ListView��Ҫ��ʾ����Ŀ�Ĳ���
			LayoutInflater from = LayoutInflater.from(context);
			contextView = from.inflate(R.layout.item, null);
			// ��Ҫ��ʾ����Ŀ������ ���ָ�������
			Temp.tv = (TextView) contextView.findViewById(R.id.tv);
		}


		// ������ֵ
		Temp.tv.setText(string);

		// ���ز���
		return contextView;
	}

	//��̬�ڲ���,��֤��һֱ���Ҵ˶���(�Ż�)
	static class Temp {
		static TextView tv;
	}

}


//java�ļ�(MainActivity)


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//��ȡListView�Ķ���
		ListView lv = (ListView) findViewById(R.id.lv);
		
		//����Ҫ��ʾ������
		ArrayList<String> list = new ArrayList<String>();
		list.add("����");
		list.add("ʯ��");
		list.add("������");
		//����Adapter����
		MyAdapter adapter = new MyAdapter(MainActivity.this, list);
		
		//��Adapter������ӵ���ͼListView��
		lv.setAdapter(adapter);
		
		//ListView�ļ���(��Ŀ)
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "Adapter"+arg1, Toast.LENGTH_SHORT).show();
			}
		}); 
		
	}


}


