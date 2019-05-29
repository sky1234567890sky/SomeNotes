Handler

// ���ִ���
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


// java���� 
(MainActivity.java)

һ �� �����߳��н��մ�����Ϣ
public class MainActivity extends Activity {

	// ����Ҫ�仯��ͼƬ���飬����ͼƬ��idֵ
	int[] Image = { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g,
			R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k };
	
	// ��������ֵ����
	int a = 0;
			
	// ʵ���� Handler����	��ʹ��handlerMessage�������ڷ��������ñ仯����	
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
	
	
	// ����˽�е��������
	private ImageView iv;
	
	// ���췽��onCreate
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		// ͨ��idֵ���ImageView�Ķ���
		iv = (ImageView)findViewById(R.id.iv);
	
		// �����̣߳�ÿ���뷢��һ����Ϣ
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

�������½������߳��н��մ�����Ϣ

// ÿ��200�������ImageView�е�ͼƬ
public class MainActivity extends Activity {

	// ����Ҫ�仯��ͼƬ������
	int[] Image = { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g,
			R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k };
	// ������������
	int a = 0;

	// ʵ����Handler���󣬲�����handleMessage()���������������ý��յ���Ϣ��������ͼƬ�ı仯ֵ
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 10) {
				iv.setImageResource(msg.getData().getInt("image"));
			}
		};
	};

	// ����ImageView��� ˽�е�ȫ�ֱ���
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ͨ��idֵ���ImageView�Ķ���
		iv = (ImageView) findViewById(R.id.iv);

		// �������߳�
		new Thread() {
			//����run()����
			public void run() {
				// ��ѭ��
				while (true) {
					// ʵ������Ϣ����
					Message msg = new Message();
					// ����Ҫ���͵���Ϣֵ
					msg.what = 10;
					// ʵ����һ������
					Bundle bundle = new Bundle();
					// ����������Ҫ���������ֵ
					bundle.putInt("image", Image[a]);
					a++;
					if (a == Image.length) {
						a = 0;
					}
					// ����Ҫ���͵�����
					msg.setData(bundle);

					// ������Ϣ
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

