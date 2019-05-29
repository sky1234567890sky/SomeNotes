package com.example.day04_homework;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class BlankFragment2 extends Fragment {

    private ArrayList<Student> list;
    private int index;
    private MyAdapter myAdapter;
    private GridView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv = getView().findViewById(R.id.gv);
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Student("小明","我爱JAVA",R.drawable.e));
        }

        myAdapter = new MyAdapter(getActivity(), list);
        lv.setAdapter(myAdapter);
        registerForContextMenu(lv);
        //设置点击事件以获取position
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1,0,1,"删除");
        menu.add(1,1,1,"修改");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String s= null;
        switch (item.getItemId()) {
            case 0://删除
                s = item.getTitle().toString();
                Student student = list.get(index);
                list.remove(student);
                myAdapter.notifyDataSetChanged();
                break;
            case 1://修改
                s = item.getTitle().toString();
                break;
        }
        Toast.makeText(getContext(),"你"+s+"了",Toast.LENGTH_LONG).show();
        return super.onContextItemSelected(item);
    }

    private class MyAdapter extends BaseAdapter {
        private final FragmentActivity activity;
        private final ArrayList<Student> list;

        public MyAdapter(FragmentActivity activity, ArrayList<Student> list) {

            this.activity = activity;
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
            ViewHolder vh = null;
            if (convertView ==null){
                vh  = new ViewHolder();
                convertView= LayoutInflater.from(activity).inflate(R.layout.layout_gv, null, false);
                //vh.tv1 = convertView.findViewById(R.id.tv1);
                //vh.tv2 = convertView.findViewById(R.id.tv2);
                vh.img = convertView.findViewById(R.id.img);
                convertView.setTag(vh);
            }else{
                convertView.getTag();
            }
            Student student = list.get(position);
           // vh.tv1.setText(student.getName());
            //vh.tv2.setText(student.getContent());
            vh.img.setImageResource(student.getImg());
            return convertView;
        }
        class ViewHolder {
            TextView tv1;
            TextView tv2;
            ImageView img;

        }
    }
}
