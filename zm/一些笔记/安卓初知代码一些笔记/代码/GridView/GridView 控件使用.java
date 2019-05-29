GridView �ؼ�ʹ��


// xml �ļ�

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
        android:text="��Ʒ�б�"
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
        android:text="���java����"
        android:textSize="15sp" />

</LinearLayout>


// java �ļ�


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
	// �Զ���һ��С����������ڴ洢ָ������Ŀ�±�
	private int index = 0;
	private Myadapter myadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);

		// ����һ�����ϣ����ڴ洢���ݿ��е�����
		list = new ArrayList<Book>();

		// �������ݿ�
		Mysql mysql = new Mysql(GridActivity.this, "goods.db", null, 1);
		SQLiteDatabase db = mysql.getWritableDatabase();

		// ��ȡgv�Ŀؼ�����
		gv = (GridView) findViewById(R.id.gv);

		// ʹ�ò������ݿ�ķ�����������ݣ������뵽�����У��Ѷ�����뵽������
		select();

		// ��������������Ŀ������ת���޸�ҳ��
		gv.setOnItemLongClickListener(this);

	}

	private void select() {

		// ʹ�����ݿ�
		Mysql mysql = new Mysql(GridActivity.this, "goods.db", null, 1);

		// ��ȡ���ݿ�Ķ�д����
		SQLiteDatabase db = mysql.getWritableDatabase();

		// ���ò�ѯ����
		String sql = "select * from goods";

		Cursor query = db.rawQuery(sql, null);

		// ѭ���ж��Ƿ�����һ������
		while (query.moveToNext()) {

			// ͨ��idֵ����ȡ���������
			int id = query.getInt(query.getColumnIndex("id"));
			int imgid = query.getInt(query.getColumnIndex("imgid"));
			String name = query.getString(query.getColumnIndex("name"));
			String intro = query.getString(query.getColumnIndex("intro"));

			// ������ݶ���
			list.add(new Book(id, imgid, name, intro));

		}

		// ����������
		myadapter = new Myadapter(GridActivity.this, list);

		// ��������
		gv.setAdapter(myadapter);

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

		index = position;

		// ������������ָ������Ŀ����ȡ��ǰ��Ŀ�����ݣ������뵽�����У���תЯ����ȥ
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

			// �������������Ӧ����ϵĻ�����ȡЯ�������ݣ�����ֵ����ǰҳ��
			Book book = (Book) data.getSerializableExtra("object");

			Log.i("tag", "=====> "+book.toString());
			
			// �޸����ݿ�
			Mysql mysql = new Mysql(GridActivity.this, "goods.db", null, 1);
			SQLiteDatabase db = mysql.getWritableDatabase();

			// ��ȡҪ���������
			ContentValues values = new ContentValues();
			values.put("name", book.getName());
			values.put("intro", book.getIntro());

			// �޸�ָ����Ŀ������
			db.update("goods", values, "id = ?", new String[] { "" + book.getId() });

			// �޸ļ����е�ָ���±������
			list.set(index, book);

			// ˢ��������
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
	// ������ʼ������,
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		// ������goods���������ֶΣ�������ID��ͼƬID����Ʒ���ƣ���Ʒ���
		String sql = "create table goods(id integer primary key autoincrement,imgid integer,name text,intro text)";

		// ִ�����
		db.execSQL(sql);

		// ������ʼ������
		for (int i = 0; i < 20; i++) {

			ContentValues values = new ContentValues();
			values.put("imgid", R.drawable.d);
			values.put("name", "���java����");
			values.put("intro", "����ѡ�����ȷ��δ����·���������У�������Ĳ���");

			// ��������
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
	// ������ʼ������,
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		// ������goods���������ֶΣ�������ID��ͼƬID����Ʒ���ƣ���Ʒ���
		String sql = "create table goods(id integer primary key autoincrement,imgid integer,name text,intro text)";

		// ִ�����
		db.execSQL(sql);

		// ������ʼ������
		for (int i = 0; i < 20; i++) {

			ContentValues values = new ContentValues();
			values.put("imgid", R.drawable.d);
			values.put("name", "���java����");
			values.put("intro", "����ѡ�����ȷ��δ����·���������У�������Ĳ���");

			// ��������
			db.insert("goods", null, values);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}








