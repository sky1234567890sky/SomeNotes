Handler

// 布局代码
(activity_main.xml)

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.linxi.MainActivity" >

    <ImageView
        android:id="@+id/iv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true" />


</RelativeLayout>


// java代码 
(MainActivity.java)

一 ： 在主线程中接收处理消息
public class MainActivity extends Activity {

	// 创建要变化的图片数组，根据图片的id值
	int[] Image = { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g,
			R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k };
	
	// 设置索引值变量
	int a = 0;
			
	// 实例化 Handler对象，	并使用handlerMessage方法，在方法里设置变化即可	
	Handler handler = new Handler(){
		
		public void handlerMessage(android.os.Message msg){
			
			if (msg.what == 10){
				iv.setImageResource(Image[a]);
				a++;
				if (a == Image.length){
					a = 0;
				}
				
			} 	
			
		}
		
		
	}
	
	
	// 创建私有的组件对象
	private ImageView iv;
	
	// 创造方法onCreate
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		// 通过id值获得ImageView的对象
		iv = (ImageView)findViewById(R.id.iv);
	
		// 创建线程，每两秒发送一个消息
		new Thread(){
			
			public void run(){
				
				while(true){
					
					handler.sendEmptyMessage(10);
					try{
						sleep(200);
					} catch (InterruptedException e){
						
						e.printStackTrace();
						
					}
					
				}
				
			};
			
		}.start();
		
	
	}
	
	
}

二：在新建的子线程中接收处理消息

// 每隔200毫秒更改ImageView中的图片
public class MainActivity extends Activity {

	// 设置要变化的图片的数组
	int[] Image = { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g,
			R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k };
	// 设置索引变量
	int a = 0;

	// 实例化Handler对象，并调用handleMessage()方法，在其中设置接收的消息，并设置图片的变化值
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 10) {
				iv.setImageResource(msg.getData().getInt("image"));
			}
		};
	};

	// 设置ImageView组件 私有的全局变量
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 通过id值获得ImageView的对象
		iv = (ImageView) findViewById(R.id.iv);

		// 创建多线程
		new Thread() {
			//调用run()方法
			public void run() {
				// 死循环
				while (true) {
					// 实例化消息对象
					Message msg = new Message();
					// 设置要发送的信息值
					msg.what = 10;
					// 实例化一个容器
					Bundle bundle = new Bundle();
					// 给容器设置要放入的数据值
					bundle.putInt("image", Image[a]);
					a++;
					if (a == Image.length) {
						a = 0;
					}
					// 设置要发送的数据
					msg.setData(bundle);

					// 发送信息
					handler.sendMessage(msg);
					try {
						sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();

	}

}

