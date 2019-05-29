package com.example.along.day16_demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by long on 2019/2/22.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

    ArrayList<Info> list;
    Context context;

    public MyAdapter(ArrayList<Info> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        holder.textage.setText(list.get(position).getAge());
        holder.textname.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myOnclik!=null){
                    myOnclik.myOnClikPosition(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private TextView textname;
        private TextView textage;
        public MyHolder(View itemView) {
            super(itemView);
            textname = itemView.findViewById(R.id.tvname);
            textage = itemView.findViewById(R.id.tvage);
        }
    }

    //1  创建点击接口
    interface  MyOnclik{
        void myOnClikPosition(int position);
    }
    // 2 以接口为  数据类型  定义全部变量
    private  MyOnclik myOnclik;

    // 3 给全局变量  定义 set 方法
    public void setMyOnclik(MyOnclik myOnclik) {
        this.myOnclik = myOnclik;
    }

    //4写点击方法  全局变量调用接口的方法

    //5 在要使用的时候 要调用set 方法
}
