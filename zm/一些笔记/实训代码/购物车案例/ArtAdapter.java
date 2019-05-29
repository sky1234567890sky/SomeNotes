package com.example.sukeyang_mid_term_examination.adapter;

import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sukeyang_mid_term_examination.MainActivity;
import com.example.sukeyang_mid_term_examination.R;
import com.example.sukeyang_mid_term_examination.bean.ArtDatas;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by 华为 on 2019/5/14.
 */

public class ArtAdapter extends RecyclerView.Adapter<ArtAdapter.MyHolder> {
    //1.首先我们要实例化一个SparseBooleanArray,这个集合对象是Android官方自带的，想了解的可以了解一下
    private SparseBooleanArray mCheckStates=new SparseBooleanArray();

    private final MainActivity mainActivity;
    private final List<ArtDatas.DataBean> list;
    private double dai;
    private double jin;
    private final ArrayList<Double> doubles;
    private Double piece1;
    private Onclick mlistener;

    public ArtAdapter(MainActivity mainActivity, List<ArtDatas.DataBean> list) {
        this.mainActivity = mainActivity;
        this.list = list;
        doubles = new ArrayList<>();
    }
    //创建ViewHolder
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(mainActivity, R.layout.item_art, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }
    //绑定ViewHolder
    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
        final ArtDatas.DataBean r = list.get(position);

        Glide.with(mainActivity).load(r.getThumbnail()).into(holder.img);
        holder.tv2.setText("每一袋价钱"+r.getBuySpeciTotalPrice());
        holder.tv1.setText("每一斤价钱"+r.getBuySpeciUnitPrice());
        holder.name.setText(r.getItemName());

        holder.myckecked.setTag(position);//在最开始适配的时候，将每一个CheckBox设置一个当前的Tag值，这样每个CheckBox都有了一个固定的标识
        //记录当前checkbox改变状态
        holder.myckecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            private String format;
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //3.
                //得到当前CheckBox的Tag值，由于之前保存过，所以不会出现索引错乱
                int  pos = (int) buttonView.getTag();
                if (isChecked){
//                    mCheckStates.put(pos,true);
                    dai = r.getBuySpeciTotalPrice();
                }else{
                    //否则将 当前CheckBox对象从SparseBooleanArray中移除
//                    mCheckStates.delete(pos);
                    dai = r.getBuySpeciTotalPrice();
                }
                mlistener.SetOnclick(isChecked,position,dai);

            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tv1;
        private TextView tv2;
        private CheckBox myckecked;
        private TextView name;
        public MyHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
            myckecked = (CheckBox) itemView.findViewById(R.id.myckecked);
            name = (TextView) itemView.findViewById(R.id.name);


        }
    }
   public interface  Onclick{
        void SetOnclick(Boolean myboolean,int position,Double  price);
   }
   public void MyAdapterOnclick(Onclick mlistener){
       this.mlistener = mlistener;
   }
}
