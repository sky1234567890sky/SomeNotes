GridView 控件使用


// xml 文件

1.activity_main.xml

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.yuekao1.GridActivity" >

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:paddingBottom="@dimen/activity_vertical_margin"
        android:text="商品列表"
        android:textSize="20sp" />

    <GridView
        android:id="@+id/gv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:numColumns="2" >
    </GridView>

</LinearLayout>


2.item.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical" >

    <ImageView
        android:id="@+id/iv_item"
        android:layout_width="match_parent"
        android:layout_height="170dp"
        android:src="@drawable/d" />

    <TextView
        android:id="@+id/tv_item"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="疯狂java讲义"
        android:textSize="15sp" />

</LinearLayout>


// java 文件


1.GridActivity.java

package com.example.yuekao1;

import java.io.Serializable;
import java.util.ArrayList;

import adapter.Myadapter;
import adapter.Mysql;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import bean.Book;

public class GridActivity extends Activity implements OnItemLongClickListener {

	private ArrayList<Book> list;
	private GridView gv;
	// 自定义一个小标变量，用于存储指定的条目下标
	private int index = 0;
	private Myadapter myadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);

		// 创建一个集合，用于存储数据库中的数据
		list = new ArrayList<Book>();

		// 创建数据库
		Mysql mysql = new Mysql(GridActivity.this, "goods.db", null, 1);
		SQLiteDatabase db = mysql.getWritableDatabase();

		// 获取gv的控件对象
		gv = (GridView) findViewById(R.id.gv);

		// 使用查找数据库的方法，获得数据，并存入到对象中，把对象存入到集合中
		select();

		// 如果长按点击了条目，会跳转到修改页面
		gv.setOnItemLongClickListener(this);

	}

	private void select() {

		// 使用数据库
		Mysql mysql = new Mysql(GridActivity.this, "goods.db", null, 1);

		// 获取数据库的读写方法
		SQLiteDatabase db = mysql.getWritableDatabase();

		// 调用查询方法
		String sql = "select * from goods";

		Cursor query = db.rawQuery(sql, null);

		// 循环判断是否有下一条数据
		while (query.moveToNext()) {

			// 通过id值，获取后面的数据
			int id = query.getInt(query.getColumnIndex("id"));
			int imgid = query.getInt(query.getColumnIndex("imgid"));
			String name = query.getString(query.getColumnIndex("name"));
			String intro = query.getString(query.getColumnIndex("intro"));

			// 添加数据对象
			list.add(new Book(id, imgid, name, intro));

		}

		// 创建适配器
		myadapter = new Myadapter(GridActivity.this, list);

		// 绑定适配器
		gv.setAdapter(myadapter);

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

		index = position;

		// 如果长按点击了指定的条目，获取当前条目的数据，并存入到对象中，跳转携带过去
		Book book = list.get(position);

		Intent intent = new Intent(GridActivity.this, XiugaiActivity.class);
		intent.putExtra("object", book);

		startActivityForResult(intent, 10);

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 10 && resultCode == 20) {

			// 如果请求码与响应码符合的话，获取携带的数据，并赋值给当前页面
			Book book = (Book) data.getSerializableExtra("object");

			Log.i("tag", "=====> "+book.toString());
			
			// 修改数据库
			Mysql mysql = new Mysql(GridActivity.this, "goods.db", null, 1);
			SQLiteDatabase db = mysql.getWritableDatabase();

			// 获取要存入的数据
			ContentValues values = new ContentValues();
			values.put("name", book.getName());
			values.put("intro", book.getIntro());

			// 修改指定条目的数据
			db.update("goods", values, "id = ?", new String[] { "" + book.getId() });

			// 修改集合中的指定下标的数据
			list.set(index, book);

			// 刷新适配器
			myadapter.notifyDataSetChanged();

		}

	}

}



2.Book.java

package bean;

import java.io.Serializable;

public class Book implements Serializable {

	private int id;
	private int imgid;
	private String name;
	private String intro;

	public Book(int id, int imgid, String name, String intro) {
		super();
		this.id = id;
		this.imgid = imgid;
		this.name = name;
		this.intro = intro;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getImgid() {
		return imgid;
	}

	public void setImgid(int imgid) {
		this.imgid = imgid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", imgid=" + imgid + ", name=" + name + ", intro=" + intro + "]";
	}

}


3.Mysql.java

package adapter;

import com.example.yuekao1.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Mysql extends SQLiteOpenHelper {

	public Mysql(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	// 创建初始化数据,
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		// 创建表“goods”，含有字段：自增长ID，图片ID，商品名称，商品简介
		String sql = "create table goods(id integer primary key autoincrement,imgid integer,name text,intro text)";

		// 执行语句
		db.execSQL(sql);

		// 创建初始化数据
		for (int i = 0; i < 20; i++) {

			ContentValues values = new ContentValues();
			values.put("imgid", R.drawable.d);
			values.put("name", "疯狂java讲义");
			values.put("intro", "您的选择很正确，未来的路线正在其中，静待你的查找");

			// 插入数据
			db.insert("goods", null, values);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}


4.Myadapter.java

package adapter;

import com.example.yuekao1.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Mysql extends SQLiteOpenHelper {

	public Mysql(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	// 创建初始化数据,
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		// 创建表“goods”，含有字段：自增长ID，图片ID，商品名称，商品简介
		String sql = "create table goods(id integer primary key autoincrement,imgid integer,name text,intro text)";

		// 执行语句
		db.execSQL(sql);

		// 创建初始化数据
		for (int i = 0; i < 20; i++) {

			ContentValues values = new ContentValues();
			values.put("imgid", R.drawable.d);
			values.put("name", "疯狂java讲义");
			values.put("intro", "您的选择很正确，未来的路线正在其中，静待你的查找");

			// 插入数据
			db.insert("goods", null, values);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}








