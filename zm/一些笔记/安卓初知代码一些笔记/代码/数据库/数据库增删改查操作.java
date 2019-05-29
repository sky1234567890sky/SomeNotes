数据库增删改查操作

// xml 文件


//1.主页面文件
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
        android:text="初始化数据库" />

    <Button
        android:id="@+id/btget"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="查找" />

    <Button
        android:id="@+id/btadd"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="添加" />

    <Button
        android:id="@+id/btdel"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="删除" />

    <Button
        android:id="@+id/btup"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="修改" />

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="" />

</LinearLayout>

//2.添加数据的文件
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
        android:text="添加数据的页面"
        android:textSize="25sp" />

    <EditText
        android:id="@+id/add_name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="请输入要添加的学生的名字" />

    <EditText
        android:id="@+id/add_age"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="请输入要添加的学生的年龄" />

    <Button
        android:id="@+id/bt_add"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="确定" />

</LinearLayout>


//3.删除数据的文件
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
        android:text="设置删除数据的页面"
        android:textSize="25sp" />

    <EditText
        android:id="@+id/del_id"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="请输入要删除数据的id值" />

    <Button
        android:id="@+id/bt_del"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="确定删除" />

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
        android:text="设置修改数据的页面"
        android:textSize="25sp" />

    <EditText
        android:id="@+id/up_name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="请输入要修改成的名字" />

    <EditText
        android:id="@+id/up_age"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="请输入要修改成的年龄" />

    <EditText
        android:id="@+id/where_id"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="请输入要修改的条件的id值" />

    <Button
        android:id="@+id/bt_update"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="确定修改" />

</LinearLayout>


// java 文件

Mydb.java

public class Mydb extends SQLiteOpenHelper {

	public Mydb(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		// 创建表的sql语句
		String sql = "create table stu(id integer primary key autoincrement,name text,age text)";

		// 执行sql语句
		db.execSQL(sql);

		// 添加数据 初始化的数据
		ContentValues values = new ContentValues();
		values.put("name", "郭磊");
		values.put("age", "22");

		// 把数据插入到表中
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

		// 获取控件对象
		Button bt = (Button) findViewById(R.id.bt);
		Button btget = (Button) findViewById(R.id.btget);
		Button btadd = (Button) findViewById(R.id.btadd);
		Button btup = (Button) findViewById(R.id.btup);
		Button btdel = (Button) findViewById(R.id.btdel);
		tv = (TextView) findViewById(R.id.tv);

		// 设置监听
		bt.setOnClickListener(this);
		btget.setOnClickListener(this);
		btadd.setOnClickListener(this);
		btup.setOnClickListener(this);
		btdel.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		// 初始化数据
		case R.id.bt:

			// 创建一个数据库
			Mydb mydb = new Mydb(MainActivity.this, "Person.db", null, 1);
			SQLiteDatabase db = mydb.getWritableDatabase();

			break;

		// 查看数据
		case R.id.btget:

			String s = "";

			// 使用指定的数据库
			Mydb mydb2 = new Mydb(MainActivity.this, "Person.db", null, 1);
			SQLiteDatabase db1 = mydb2.getWritableDatabase();

			// 编写查询语句
			String sql = "SELECT * FROM stu";
			Cursor query = db1.rawQuery(sql, null);

			// 循环判断有没有下一个数据
			while (query.moveToNext()) {

				// 获取数据 通过列名， 获取列的下标，再通过下标获得对应的值
				int id = query.getInt(query.getColumnIndex("id"));
				String name = query.getString(query.getColumnIndex("name"));
				String age = query.getString(query.getColumnIndex("age"));

				Log.i("tag", "id===>" + id + "    name===>" + name + "    age===>" + age);

				// 获取数据，并给布局设置上
				s += "id===>" + id + "    name===>" + name + "    age===>" + age + " ；" + "\r\n";

			}
			tv.setText(s);

			break;

		// 添加数据
		case R.id.btadd:

			AlertDialog_add();

			break;

		// 修改数据
		case R.id.btup:

			Update();

			break;

		// 删除数据
		case R.id.btdel:

			delete();

			break;

		default:
			break;
		}

	}

	// 添加数据（弹出对话框设置要添加的数据）
	public void AlertDialog_add() {

		// 调用的对话框的布局 及 控件对象
		View add = getLayoutInflater().inflate(R.layout.item_add, null);
		final EditText add_name = (EditText) add.findViewById(R.id.add_name);
		final EditText add_age = (EditText) add.findViewById(R.id.add_age);
		Button bt_add = (Button) add.findViewById(R.id.bt_add);

		// 跳出对话框，设置数据
		final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setView(add).create();
		dialog.show();

		// 如果点击了确认按钮，获取页面的数据，并给 插入语句
		bt_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// 获取要添加的数据
				String name = add_name.getText().toString();
				String age = add_age.getText().toString();

				// 使用指定的数据库
				Mydb mydb3 = new Mydb(MainActivity.this, "Person.db", null, 1);
				SQLiteDatabase db2 = mydb3.getWritableDatabase();

				// 用来存储你要 添加的数据
				ContentValues values = new ContentValues();
				values.put("name", name);
				values.put("age", age);

				// 插入数据 values到stu表中
				db2.insert("stu", null, values);

				// 关闭数据库
				db2.close();

				Toast.makeText(MainActivity.this, "插入数据成功", Toast.LENGTH_SHORT).show();

				// 关闭对话框
				dialog.dismiss();

			}
		});

	}

	// 修改数据（弹出对话框设置修改的数据，与条件数据）
	public void Update() {

		// 调用的对话框的布局 及 控件方法
		View Update = getLayoutInflater().inflate(R.layout.item_update, null);
		final EditText et_name = (EditText) Update.findViewById(R.id.up_name);
		final EditText et_age = (EditText) Update.findViewById(R.id.up_age);
		final EditText where_id = (EditText) Update.findViewById(R.id.where_id);
		Button bt = (Button) Update.findViewById(R.id.bt_update);

		// 跳出对话框，设置数据
		final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setView(Update).create();
		dialog.show();

		// 如果点击了确认按钮，获取页面的数据，并赋值给 修改的语句
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// 获取要修改成的数据，及条件id值
				String name = et_name.getText().toString();
				String age = et_age.getText().toString();
				String id = where_id.getText().toString();

				// 使用指定的数据库
				Mydb mydb4 = new Mydb(MainActivity.this, "Person.db", null, 1);
				SQLiteDatabase db3 = mydb4.getWritableDatabase();

				// 编写修改数据的 sql 语句
				String up = "UPDATE stu set name = '" + name + "' ,age = '" + age + "' WHERE id = '" + id + "'";

				db3.execSQL(up);

				db3.close();

				Toast.makeText(MainActivity.this, "修改成功", Toast.LENGTH_SHORT).show();

				dialog.dismiss();

			}
		});

	}

	// 删除数据（设置要删除的条件的id值）
	public void delete() {

		// 调用的对话框的布局 及 控件的对象
		View delete = getLayoutInflater().inflate(R.layout.item_del, null);
		final EditText del_id = (EditText) delete.findViewById(R.id.del_id);
		Button bt = (Button) delete.findViewById(R.id.bt_del);

		// 跳出对话框，设置数据
		final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setView(delete).create();
		dialog.show();

		// 如果点击了确认按钮，获取页面的值，并赋值给 删除语句
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// 获取要删除数据的id值
				String id = del_id.getText().toString();

				// 使用指定的数据库
				Mydb mydb5 = new Mydb(MainActivity.this, "Person.db", null, 1);
				SQLiteDatabase db4 = mydb5.getWritableDatabase();

				// 创建删除数据的语句
				String del = "DELETE FROM stu WHERE id = '" + id + "'";

				db4.execSQL(del);

				db4.close();

				Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();

				dialog.dismiss();

			}
		});

	}

}

