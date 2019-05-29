AsyncTask ����ͼƬ

// xml����
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
        android:text="����ͼƬ" />

    <ImageView
        android:id="@+id/iv"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>

// java�ļ�

�첽������AsyncTask ����һ��������
	ʹ�ù��̣�
	�Զ���һ���࣬�̳д��࣬����д��������
	T doInBackground() �˷����ڶ��߳������У����еĽ�����Լ�����
	onPostExecute(T t)	��doInBackground()ִ�н����Ժ����̻߳��������onPostExecute(),�ڴ˷����оͿ��Զ�UI�ؼ������޸�
	
	Params, T doInBackground(Params)��̨����������Ҫ��Щ���ݣ��Ͷ����ʲô����
	Progress, 	Void
	Result	doInBackground()��������ֵ�����ͣ�Ҳ��Ҫ����onPostExecute()���������ݵ�����




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

		// ������ť������ͼƬ
		bt.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		/*
		 * My my = new My(); // �����߳� my.execute(R.drawable.ab);
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
	 * // ��ʱ8��󷵻�Ҫ���µ�ͼƬid try { Thread.sleep(8000); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return params[0]; }
	 * 
	 * @Override protected void onPostExecute(Integer result) { // TODO
	 * Auto-generated method stub super.onPostExecute(result); //
	 * ����doInBackground���ص�ֵȷ��Ҫ��ʾ��ͼƬ iv.setImageResource(result);
	 * 
	 * }
	 * 
	 * }
	 */

}


