SharedPreferences �洢����

// xml�ļ�
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
    tools:context="com.example.sp.MainActivity" >

    <Button
        android:id="@+id/bt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hello_world" />

    <Button
        android:id="@+id/btget"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="get" />

</LinearLayout>

// java�ļ�

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.bt).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// sp   ��ѡ��
				
				// ����һ��  �ļ�    xml   �ֻ�  APP    ��ֵ��ʽ   ��ֵ��    map  
				// user.xml
				SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);

				// �� �ļ� ���ļ��༭
				Editor edit = sp.edit(); // ��ȡ �ļ��ı༭��
				// map
				edit.putString("1", "abc");
				edit.putString("2", "11111");

				// ������ �ύ ctrl + s
				edit.commit();

				// name �ļ������� mode ģʽ Ȩ��

				// �ļ����� ���� ȡ��

			}
		});

		findViewById(R.id.btget).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// ����ʲô��ȡʲô
				// ��ò��� ����
				SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);

				// ȡ ����ʲô��ȡʲô
				String string = sp.getString("1", "");

				String string2 = sp.getString("2", "");

				Log.i("tag", "string ===>" + string);
				Log.i("tag", "string2 ===>" + string2);
			}
		});

	}

}


