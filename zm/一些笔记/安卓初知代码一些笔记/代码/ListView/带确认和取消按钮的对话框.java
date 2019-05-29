// ArrayAdapter �Զ������ (ArrayList����)���� �� ���ó������������Ի��� ����ȷ�Ϻ�ȡ����ť�ĶԻ���


//ʵ���� (Animal.java)

package com.example.lianxi1;

public class Animal {
	private int image;
	private String name;
	private String say;

	public Animal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Animal(int image, String name, String say) {
		super();
		this.image = image;
		this.name = name;
		this.say = say;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSay() {
		return say;
	}

	public void setSay(String say) {
		this.say = say;
	}

	@Override
	public String toString() {
		return "Animal [image=" + image + ", name=" + name + ", say=" + say
				+ "]";
	}

}

// �������ļ�(activity_Main.xml)

 <!-- �����ļ� -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingLeft="20dp"
    android:paddingRight="20dp" >


    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        android:text="����˵:" />
    
     <ListView
         android:id="@+id/lv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        />
	    

    
</LinearLayout>


// ��Ŀ�ļ� (item.xml)

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >
   
    <ImageView 
        android:id="@+id/iv"
        android:layout_width="80dp"
    	android:layout_height="80dp"
    	android:src="@drawable/ic_launcher"
        />
    <TextView 
        android:id="@+id/tv1"
        android:layout_width="wrap_content"
    	android:layout_height="wrap_content"
    	android:layout_toRightOf="@id/iv"
    	android:textStyle="bold"
    	android:textSize="40sp"
    	android:text="��˵"
        />
    <TextView 
        android:id="@+id/tv2"
        android:layout_width="wrap_content"
    	android:layout_height="wrap_content"
    	android:layout_toRightOf="@id/iv"
    	android:layout_alignBottom="@id/iv"
    	android:textSize="30sp"
    	android:text="���ǹ�ô"
        />

</RelativeLayout>

// java������(MyAdapter)�̳�ArrayAdapter


public class MyAdapter extends ArrayAdapter<Animal> {

	public MyAdapter(Context context, int resource, ArrayList<Animal> list) {
		super(context, resource, list);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//���convertViewΪ��,�����ָ���Ӳ���
		Holder holder= null;
		if(convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent,false);
			holder = new Holder();
			holder.iv = (ImageView) convertView.findViewById(R.id.iv);
			holder.tv1 = (TextView) convertView.findViewById(R.id.tv1);
			holder.tv2 = (TextView) convertView.findViewById(R.id.tv2);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		Animal animal = getItem(position);
		holder.iv.setImageResource(animal.getImage());
		holder.tv1.setText(animal.getName());
		holder.tv2.setText(animal.getSay());
		
		return convertView;
	}
	
	class Holder{
		ImageView iv;
		TextView tv1;
		TextView tv2;
	}
	
	
}

// java����(Main)

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //������ʾ������
        final ArrayList<Animal> list = new ArrayList<Animal>();
        list.add(new Animal(R.drawable.a, "��˵:", "����˯��!"));
        list.add(new Animal(R.drawable.b, "ţ˵:", "���ţ!"));
        list.add(new Animal(R.drawable.c, "Ѽ˵:", "�����Ѽ��,����Ӳ!"));
        list.add(new Animal(R.drawable.d, "��˵:", "��������!"));
        //�����Զ���Adapter����
        final MyAdapter adapter = new MyAdapter(MainActivity.this, R.layout.item, list); 
        //��ȡListView����
        ListView lv = (ListView) findViewById(R.id.lv);
        //��Adapter�������ý�ListView������
        lv.setAdapter(adapter);
        
        //��ListView���м���,�������ĳһ����Ŀ,����Զ������Ի���
        lv.setOnItemLongClickListener(new OnItemLongClickListener() {
        	//�������ListView������д˷���
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				//����һ���Ի���
				AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
									.setTitle("��Ҫ��ʾ")	//���öԻ������
									.setMessage("��ó�ҩ��")	//���öԻ�����Ϣ
									.setIcon(R.drawable.a)		//���Ի����һ��ͼ��
									//���Ի������һ�������İ�ť ���Դ˰�ť����
									.setPositiveButton("OK", new OnClickListener() {
										//�Ի���ť���������д˷���,�����ڴ˼���������ɾ��ѡ�е�ListView��Ŀ
										@Override
										public void onClick(DialogInterface arg0, int arg1) {
											// TODO Auto-generated method stub
							
											Toast.makeText(MainActivity.this, "���ھ�ȥ��ҩ", Toast.LENGTH_SHORT).show();
										}
									})
									//���Ի�������һ�������İ�ť
									.setNegativeButton("NO", new OnClickListener() {
										
										@Override
										public void onClick(DialogInterface arg0, int arg1) {
											// TODO Auto-generated method stub
											Toast.makeText(MainActivity.this, "��������!", Toast.LENGTH_SHORT).show();
										}
									})
									.create();	//ǰ��Ķ��Ǵ����ľ�̬�ڲ���AlertDialog.Builder����,ֻ��create��Żᴴ��AlertDialog����
				
				//���öԻ���show����,�Ի��������ʾ
				dialog.show();
				//�˴���ListView�ĳ���������ֵ,����Ƿ���false,������ж̼���,�̼�����Ȼ����Ӧ,�����true,��֪ͨ�̼�������Ӧ��
				return false;
			}
		});
    }

}



