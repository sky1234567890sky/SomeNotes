package com.example.sukeyang_mid_term_examination;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sukeyang_mid_term_examination.adapter.ArtAdapter;
import com.example.sukeyang_mid_term_examination.app.DbUtils;
import com.example.sukeyang_mid_term_examination.app.Student;
import com.example.sukeyang_mid_term_examination.bean.ArtDatas;
import com.example.sukeyang_mid_term_examination.m.MyModelimpl;
import com.example.sukeyang_mid_term_examination.p.MyPresenterImpl;
import com.example.sukeyang_mid_term_examination.v.MyView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyView {
    //苏克阳  H1809A
    //B场
    private Toolbar tol;
    /**
     * 合计：xxxx
     */
    private TextView tv;
    private XRecyclerView lv;
    private List<ArtDatas.DataBean> list;
    private ArtAdapter adapter;
    private Double piece1=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化试图
        initView();
        initData();
        //http://106.13.63.54:8080/sys/xin195qz/jiekou1.json
    }
    private void initData() {
        //调用p层，获取数据
        MyPresenterImpl myPresenter = new MyPresenterImpl(new MyModelimpl(), this);
        myPresenter.getData();
    }

    private void initView() {
        //找控件
        tol = (Toolbar) findViewById(R.id.tol);
        tv = (TextView) findViewById(R.id.tv);
        lv = (XRecyclerView) findViewById(R.id.lv);
        tol.setTitle("购物车");
        setSupportActionBar(tol);
        //创建适配器并绑定适配器
        lv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new ArtAdapter(this, list);
        lv.setAdapter(adapter);

        adapter.MyAdapterOnclick(new ArtAdapter.Onclick() {
            private String format;
            @Override
            public void SetOnclick(Boolean myboolean,int position, Double price) {
                ArrayList<Double> doubles = new ArrayList<>();
            if(myboolean){
                doubles.add(price);
                for (Double aDouble : doubles) {
                    piece1 += aDouble;
                    Log.d("adapter", piece1+"");
                    format = String.format("%.2f", piece1);
                }
                Log.d("mainactivity", "SetOnclick: "+format);
                tv.setText(format+"");
            }else{
                doubles.add(price);
                for (Double aDouble : doubles) {
                    piece1 -= aDouble;
                    Log.d("adapter", piece1+"");
                    format = String.format("%.2f", piece1);
                }
                Log.d("mainactivity", "SetOnclick: "+format);
                tv.setText(format+"");
            }
            }
        });

    }
    //重写成功和失败的方法
    @Override
    public void onSuccess(List<ArtDatas.DataBean> list1) {
        list.addAll(list1);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
