package com.jiyun.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnLongClickListener {

    private RecyclerView lv;
    private ArrayList<Person> list;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initQuery();
    }

    private void initQuery() {
        List<Person> query = DbUtils.getDbUtils().query();
        list.addAll(query);
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    private void initData() {

        ArrayList<Person> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Person person = new Person();
            person.setId(Long.valueOf(i));
            person.setAge(20+i);
            person.setName("张三"+i);
//            person.setSex("男");
            list.add(person);
        }

        DbUtils.getDbUtils().insertAll(list);
    }

    private void initView() {
        lv = (RecyclerView) findViewById(R.id.lv);

        list = new ArrayList<>();

        adapter = new MyAdapter(this,list);
        lv.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        lv.setLayoutManager(layoutManager);

        adapter.setOnLongClickListener(this);

        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo
            menuInfo) {
        menu.add(1,100,100,"增加");
        menu.add(1,200,100,"删除");
        menu.add(1,300,100,"修改");
        menu.add(1,400,100,"查询");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 100:
                insert();
                break;
            case 200:
                delete();
                break;
            case 300:
                update();
                break;
            case 400:
                query();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void query() {
        List<Person> people = DbUtils.getDbUtils().queryPerson(person.getName());
        Toast.makeText(this,people.toString(),Toast.LENGTH_SHORT).show();
    }

    private void update() {
        person.setName("lisi");
        DbUtils.getDbUtils().update(person);

        /*list.clear();
        initQuery();*/

        list.set(positon,person);
        adapter.notifyDataSetChanged();
    }

    private void delete() {
        DbUtils.getDbUtils().delete(person);

        /*list.clear();
        initQuery();*/

        list.remove(positon);
        adapter.notifyDataSetChanged();
    }

    private void insert() {
        Person person = new Person();
        person.setId(Long.valueOf(100));
        person.setAge(20);
        person.setName("张三");
//        person.setSex("男");
        DbUtils.getDbUtils().insert(person);

        /*list.clear();
        initQuery();*/

        list.add(person);
        adapter.notifyDataSetChanged();
    }


    //
    private int positon;
    private Person person;

    @Override
    public void onLongClikc(int positon, Person person) {
        this.positon = positon;
        this.person = person;
    }
}
