AsyncTask 下载图片

// xml布局
activity_main.xml

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.day11httpurlconnection.MainActivity" >

    <Button
        android:id="@+id/bt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:text="下载图片" />

    <ImageView
        android:id="@+id/iv"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>

// java文件

异步操作：AsyncTask 这是一个抽象类
	使用过程：
	自定义一个类，继承此类，并重写两个方法
	T doInBackground() 此方法在多线程中运行，运行的结果由自己定义
	onPostExecute(T t)	当doInBackground()执行结束以后，主线程会立马调用onPostExecute(),在此方法中就可以对UI控件进行修改
	
	Params, T doInBackground(Params)后台程序运行需要哪些数据，就定义成什么类型
	Progress, 	Void
	Result	doInBackground()方法返回值的类型，也是要交给onPostExecute()方法的数据的类型




MainActivity.java

public class MainActivity extends Activity implements OnClickListener {

	private ImageView iv;
	private Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv = (ImageView) findViewById(R.id.iv);
		bt = (Button) findViewById(R.id.bt);

		// 监听按钮，下载图片
		bt.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		/*
		 * My my = new My(); // 启动线程 my.execute(R.drawable.ab);
		 */
		new My().execute();
	}

	class My extends AsyncTask<Void, Void, Integer> {

		@Override
		protected Integer doInBackground(Void... params) {
			// TODO Auto-generated method stub

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return R.drawable.ab;
		}

		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			iv.setImageResource(result);

		}

	}

	/*
	 * class My extends AsyncTask<Integer, Void, Integer> {
	 * 
	 * @Override protected Integer doInBackground(Integer... params) { // TODO
	 * Auto-generated method stub
	 * 
	 * // 延时8秒后返回要更新的图片id try { Thread.sleep(8000); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return params[0]; }
	 * 
	 * @Override protected void onPostExecute(Integer result) { // TODO
	 * Auto-generated method stub super.onPostExecute(result); //
	 * 根据doInBackground返回的值确定要显示的图片 iv.setImageResource(result);
	 * 
	 * }
	 * 
	 * }
	 */

}


