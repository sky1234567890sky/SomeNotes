���ݿ���ɾ�Ĳ����

// xml �ļ�


//1.��ҳ���ļ�
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
    tools:context="com.example.sqlite3.MainActivity" >

    <Button
        android:id="@+id/bt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="��ʼ�����ݿ�" />

    <Button
        android:id="@+id/btget"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="����" />

    <Button
        android:id="@+id/btadd"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="���" />

    <Button
        android:id="@+id/btdel"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="ɾ��" />

    <Button
        android:id="@+id/btup"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="�޸�" />

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="" />

</LinearLayout>

//2.������ݵ��ļ�
item_add.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:paddingBottom="20dp"
        android:text="������ݵ�ҳ��"
        android:textSize="25sp" />

    <EditText
        android:id="@+id/add_name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="������Ҫ��ӵ�ѧ��������" />

    <EditText
        android:id="@+id/add_age"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="������Ҫ��ӵ�ѧ��������" />

    <Button
        android:id="@+id/bt_add"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="ȷ��" />

</LinearLayout>


//3.ɾ�����ݵ��ļ�
item_del.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:paddingBottom="20dp"
        android:text="����ɾ�����ݵ�ҳ��"
        android:textSize="25sp" />

    <EditText
        android:id="@+id/del_id"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="������Ҫɾ�����ݵ�idֵ" />

    <Button
        android:id="@+id/bt_del"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="ȷ��ɾ��" />

</LinearLayout>


item_update.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:paddingBottom="20dp"
        android:text="�����޸����ݵ�ҳ��"
        android:textSize="25sp" />

    <EditText
        android:id="@+id/up_name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="������Ҫ�޸ĳɵ�����" />

    <EditText
        android:id="@+id/up_age"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="������Ҫ�޸ĳɵ�����" />

    <EditText
        android:id="@+id/where_id"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="������Ҫ�޸ĵ�������idֵ" />

    <Button
        android:id="@+id/bt_update"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="ȷ���޸�" />

</LinearLayout>


// java �ļ�

Mydb.java

public class Mydb extends SQLiteOpenHelper {

	public Mydb(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		// �������sql���
		String sql = "create table stu(id integer primary key autoincrement,name text,age text)";

		// ִ��sql���
		db.execSQL(sql);

		// ������� ��ʼ��������
		ContentValues values = new ContentValues();
		values.put("name", "����");
		values.put("age", "22");

		// �����ݲ��뵽����
		db.insert("stu", null, values);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}



MainActivity.java

public class MainActivity extends Activity implements OnClickListener {

	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ȡ�ؼ�����
		Button bt = (Button) findViewById(R.id.bt);
		Button btget = (Button) findViewById(R.id.btget);
		Button btadd = (Button) findViewById(R.id.btadd);
		Button btup = (Button) findViewById(R.id.btup);
		Button btdel = (Button) findViewById(R.id.btdel);
		tv = (TextView) findViewById(R.id.tv);

		// ���ü���
		bt.setOnClickListener(this);
		btget.setOnClickListener(this);
		btadd.setOnClickListener(this);
		btup.setOnClickListener(this);
		btdel.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		// ��ʼ������
		case R.id.bt:

			// ����һ�����ݿ�
			Mydb mydb = new Mydb(MainActivity.this, "Person.db", null, 1);
			SQLiteDatabase db = mydb.getWritableDatabase();

			break;

		// �鿴����
		case R.id.btget:

			String s = "";

			// ʹ��ָ�������ݿ�
			Mydb mydb2 = new Mydb(MainActivity.this, "Person.db", null, 1);
			SQLiteDatabase db1 = mydb2.getWritableDatabase();

			// ��д��ѯ���
			String sql = "SELECT * FROM stu";
			Cursor query = db1.rawQuery(sql, null);

			// ѭ���ж���û����һ������
			while (query.moveToNext()) {

				// ��ȡ���� ͨ�������� ��ȡ�е��±꣬��ͨ���±��ö�Ӧ��ֵ
				int id = query.getInt(query.getColumnIndex("id"));
				String name = query.getString(query.getColumnIndex("name"));
				String age = query.getString(query.getColumnIndex("age"));

				Log.i("tag", "id===>" + id + "    name===>" + name + "    age===>" + age);

				// ��ȡ���ݣ���������������
				s += "id===>" + id + "    name===>" + name + "    age===>" + age + " ��" + "\r\n";

			}
			tv.setText(s);

			break;

		// �������
		case R.id.btadd:

			AlertDialog_add();

			break;

		// �޸�����
		case R.id.btup:

			Update();

			break;

		// ɾ������
		case R.id.btdel:

			delete();

			break;

		default:
			break;
		}

	}

	// ������ݣ������Ի�������Ҫ��ӵ����ݣ�
	public void AlertDialog_add() {

		// ���õĶԻ���Ĳ��� �� �ؼ�����
		View add = getLayoutInflater().inflate(R.layout.item_add, null);
		final EditText add_name = (EditText) add.findViewById(R.id.add_name);
		final EditText add_age = (EditText) add.findViewById(R.id.add_age);
		Button bt_add = (Button) add.findViewById(R.id.bt_add);

		// �����Ի�����������
		final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setView(add).create();
		dialog.show();

		// ��������ȷ�ϰ�ť����ȡҳ������ݣ����� �������
		bt_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// ��ȡҪ��ӵ�����
				String name = add_name.getText().toString();
				String age = add_age.getText().toString();

				// ʹ��ָ�������ݿ�
				Mydb mydb3 = new Mydb(MainActivity.this, "Person.db", null, 1);
				SQLiteDatabase db2 = mydb3.getWritableDatabase();

				// �����洢��Ҫ ��ӵ�����
				ContentValues values = new ContentValues();
				values.put("name", name);
				values.put("age", age);

				// �������� values��stu����
				db2.insert("stu", null, values);

				// �ر����ݿ�
				db2.close();

				Toast.makeText(MainActivity.this, "�������ݳɹ�", Toast.LENGTH_SHORT).show();

				// �رնԻ���
				dialog.dismiss();

			}
		});

	}

	// �޸����ݣ������Ի��������޸ĵ����ݣ����������ݣ�
	public void Update() {

		// ���õĶԻ���Ĳ��� �� �ؼ�����
		View Update = getLayoutInflater().inflate(R.layout.item_update, null);
		final EditText et_name = (EditText) Update.findViewById(R.id.up_name);
		final EditText et_age = (EditText) Update.findViewById(R.id.up_age);
		final EditText where_id = (EditText) Update.findViewById(R.id.where_id);
		Button bt = (Button) Update.findViewById(R.id.bt_update);

		// �����Ի�����������
		final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setView(Update).create();
		dialog.show();

		// ��������ȷ�ϰ�ť����ȡҳ������ݣ�����ֵ�� �޸ĵ����
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// ��ȡҪ�޸ĳɵ����ݣ�������idֵ
				String name = et_name.getText().toString();
				String age = et_age.getText().toString();
				String id = where_id.getText().toString();

				// ʹ��ָ�������ݿ�
				Mydb mydb4 = new Mydb(MainActivity.this, "Person.db", null, 1);
				SQLiteDatabase db3 = mydb4.getWritableDatabase();

				// ��д�޸����ݵ� sql ���
				String up = "UPDATE stu set name = '" + name + "' ,age = '" + age + "' WHERE id = '" + id + "'";

				db3.execSQL(up);

				db3.close();

				Toast.makeText(MainActivity.this, "�޸ĳɹ�", Toast.LENGTH_SHORT).show();

				dialog.dismiss();

			}
		});

	}

	// ɾ�����ݣ�����Ҫɾ����������idֵ��
	public void delete() {

		// ���õĶԻ���Ĳ��� �� �ؼ��Ķ���
		View delete = getLayoutInflater().inflate(R.layout.item_del, null);
		final EditText del_id = (EditText) delete.findViewById(R.id.del_id);
		Button bt = (Button) delete.findViewById(R.id.bt_del);

		// �����Ի�����������
		final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setView(delete).create();
		dialog.show();

		// ��������ȷ�ϰ�ť����ȡҳ���ֵ������ֵ�� ɾ�����
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// ��ȡҪɾ�����ݵ�idֵ
				String id = del_id.getText().toString();

				// ʹ��ָ�������ݿ�
				Mydb mydb5 = new Mydb(MainActivity.this, "Person.db", null, 1);
				SQLiteDatabase db4 = mydb5.getWritableDatabase();

				// ����ɾ�����ݵ����
				String del = "DELETE FROM stu WHERE id = '" + id + "'";

				db4.execSQL(del);

				db4.close();

				Toast.makeText(MainActivity.this, "ɾ���ɹ�", Toast.LENGTH_SHORT).show();

				dialog.dismiss();

			}
		});

	}

}

