package com.jiyun.day05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiyun.day05.dao.StudentDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button insert;
    private Button update;
    private Button delete;
    private Button query;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        insert = (Button) findViewById(R.id.insert);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        query = (Button) findViewById(R.id.query);
        txt = (TextView) findViewById(R.id.txt);

        insert.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert:
                insert();
                break;
            case R.id.update:
                update();
                break;
            case R.id.delete:
                delete();
                break;
            case R.id.query:
                query();
                break;
        }
    }

    private void query() {
        //1.
        /*StudentDao studentDao = MyApp.getApp().getStudentDao();
        List<Student> list = studentDao.queryBuilder().list();
        List<Student> list2 = studentDao.queryBuilder().where(StudentDao.Properties.Name.eq("张三2"))
                .list();*/

        //2.
        List<Student> list = DbHelper.getDbHelper().queryAll();

        txt.setText(list.toString());
    }

    private void delete() {

        Student student = new Student();
        student.setId(Long.valueOf(1));
        student.setAge(20 + 1);
        student.setName("张三" + 1 );
        student.setSex("男");

        //1.
        /*StudentDao studentDao = MyApp.getApp().getStudentDao();
        studentDao.delete(student);*/

        //2.
        DbHelper.getDbHelper().delete(student);
    }

    private void update() {

        Student student = new Student();
        student.setId(Long.valueOf(0));
        student.setAge(20 + 0);
        student.setName("李四" + 0 );
        student.setSex("男");

        //1.
        /*StudentDao studentDao = MyApp.getApp().getStudentDao();
        studentDao.update(student);*/

        //2.
        DbHelper.getDbHelper().update(student);
    }

    private void insert() {

        ArrayList<Student> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Student student = new Student();
            student.setId(Long.valueOf(i));
            student.setAge(20 + i);
            student.setName("张三" + i );
            student.setSex("男");
            list.add(student);
        }

        //1.
        /*StudentDao studentDao = MyApp.getApp().getStudentDao();
        studentDao.insertOrReplaceInTx(list);*/

        //2.
        DbHelper.getDbHelper().insertAll(list);
    }
}
