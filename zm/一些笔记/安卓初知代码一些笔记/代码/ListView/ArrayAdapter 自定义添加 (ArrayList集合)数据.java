//ArrayAdapter 自定义添加 (ArrayList集合)数据


//实体类 (Animal.java)

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

// 主布局文件(activity_Main.xml)

 <!-- 布局文件 -->
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
        android:text="动物说:" />
    
     <ListView
         android:id="@+id/lv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        />
	    

    
</LinearLayout>


// 条目文件 (item.xml)

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
    	android:text="狗说"
        />
    <TextView 
        android:id="@+id/tv2"
        android:layout_width="wrap_content"
    	android:layout_height="wrap_content"
    	android:layout_toRightOf="@id/iv"
    	android:layout_alignBottom="@id/iv"
    	android:textSize="30sp"
    	android:text="你是狗么"
        />

</RelativeLayout>

// java适配器(MyAdapter)继承ArrayAdapter

public class MyAdapter extends ArrayAdapter<Animal> {

	public MyAdapter(Context context, int resource, ArrayList<Animal> list) {
		super(context, resource, list);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//如果convertView为空,则给他指定子布局
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

// java代码(Main)

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//先来显示的数据
        ArrayList<Animal> list = new ArrayList<Animal>();
        list.add(new Animal(R.drawable.a, "狗说:", "你是狗么"));
        list.add(new Animal(R.drawable.b, "猪说:", "你是猪么"));
        list.add(new Animal(R.drawable.c, "鸭说:", "你是鸭么"));
        list.add(new Animal(R.drawable.d, "鸡说:", "你是鸡么"));
        //创建Adapter对象
        MyAdapter adapter = new MyAdapter(MainActivity.this, R.layout.item, list);	
        //获取ListView对象
        ListView lv = (ListView) findViewById(R.id.lv);
        //把Adapter添加进入ListView
        lv.setAdapter(adapter);
	}


}

