package com.example.day04_homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /** 苏克阳 1909A 3.6
     * Toolbar
     */
    private TextView mTv;
    private ViewPager mVp;
    private LinearLayout mLl;
    private DrawerLayout mDl;
    private Toolbar tb;
    private FrameLayout fram;
    private BlankFragment1 blankFragment1;
    private BlankFragment2 blankFragment2;
    private ListView lv;
    private GridView gv;
    private int index ;
    private ArrayList<String> list;
    private MyAdapter myAdapter;
    private MyGlAdapter myGlAdapter;
    private String s1;
    private int deleter;
    private ArrayList<String> list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
       // mVp = (ViewPager) findViewById(R.id.vp);
        //mLl = (LinearLayout) findViewById(R.id.ll);
       // mDl = (DrawerLayout) findViewById(R.id.dl);
        tb = findViewById(R.id.toolbar);

        lv = findViewById(R.id.lv);
        gv = findViewById(R.id.gv);

        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("测试"+i);
        }
        myAdapter = new MyAdapter(this, list);
        lv.setAdapter(myAdapter);

        list1 = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list1.add("默认测试"+i);
        }

        myGlAdapter = new MyGlAdapter(this, list1);
        gv.setAdapter(myGlAdapter);
        //fram = findViewById(R.id.fram);
        tb.setTitle("");
        setSupportActionBar(tb);
        //注册上下文菜单
        registerForContextMenu(lv);
        registerForContextMenu(gv);
        //为数据设置点击监听 标记下标            deleter = position;
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleter = position;
                return false;
            }
        });
        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                return false;
            }
        });
//        blankFragment1 = new BlankFragment1();
//        blankFragment2 = new BlankFragment2();
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.fram, blankFragment1);
//        fragmentTransaction.add(R.id.fram, blankFragment2);
//        fragmentTransaction.show(blankFragment1).hide(blankFragment2);
//        fragmentTransaction.commit();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0,0,0,"删除");
        menu.add(1,1,1,"修改or添加");
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                s1 = item.getTitle().toString();
                String s = list.get(deleter);
                list.remove(s);
                myAdapter.notifyDataSetChanged();
                break;
            case 1:

                s1 = item.getTitle().toString();
                Intent intent = new Intent(this, DeActivity.class);
                String s2 = list.get(index);
                intent.putExtra("name",s2);
                startActivityForResult(intent,100);
                break;

        }
        Toast.makeText(this, "你点击了"+s1, Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode ==200){//修改
            String s = data.getStringExtra("s");
            String add = data.getStringExtra("add");
            list1.set(index,s);
            list1.add(add);
            myGlAdapter.notifyDataSetChanged();
        }
    }
    //创建选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.selector1:
                lv.setVisibility(View.VISIBLE);
                gv.setVisibility(View.INVISIBLE);
               // getSupportFragmentManager().beginTransaction().show(blankFragment1).hide(blankFragment2).commit();
                break;
            case R.id.selector2:
                gv.setVisibility(View.VISIBLE);
                lv.setVisibility(View.INVISIBLE);
              //  getSupportFragmentManager().beginTransaction().show(blankFragment2).hide(blankFragment1).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //处理事项 菜单图标问题
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);

                    m.setAccessible(true);

                    m.invoke(menu, true);

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    private class MyAdapter extends BaseAdapter{
        private final MainActivity mainActivity;
        private final ArrayList<String> list;

        public MyAdapter(MainActivity mainActivity, ArrayList<String> list) {

            this.mainActivity = mainActivity;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh=null;
            if (convertView == null){
                vh = new ViewHolder();
                convertView = LayoutInflater.from(mainActivity).inflate(R.layout.item_lv_gv, null, false);

                vh.tv = convertView.findViewById(R.id.tv);

                convertView.setTag(vh);

            }else{
                vh = (ViewHolder) convertView.getTag();
            }
            String s = list.get(position);
            vh.tv.setText(s);
            return convertView;
        }
        class ViewHolder{
            TextView tv;
        }
    }


    private class MyGlAdapter extends  BaseAdapter{

        private final MainActivity mainActivity;
        private final ArrayList<String> list;

        public MyGlAdapter(MainActivity mainActivity, ArrayList<String> list) {

            this.mainActivity = mainActivity;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh=null;
            if (convertView == null){
                vh = new ViewHolder();
                convertView = LayoutInflater.from(mainActivity).inflate(R.layout.item_lv_gv2, null, false);
                vh.tv = convertView.findViewById(R.id.tv);
                convertView.setTag(vh);
            }else{
                vh = (ViewHolder) convertView.getTag();
            }
            String s = list.get(position);
            vh.tv.setText(s);
            return convertView;
        }
        class ViewHolder{
            TextView tv;
        }

    }
}
