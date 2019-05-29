package com.example.zhoutiandemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhoutiandemo.R;
import com.example.zhoutiandemo.bean.FuLiBean;

import java.util.ArrayList;

/**
 * 王董辉    class
 */
public class ShouYeAdapter extends RecyclerView.Adapter {
    private final Context context;
    private ArrayList<FuLiBean.ResultsBean> list;

    public ShouYeAdapter(ArrayList<FuLiBean.ResultsBean> list, FragmentActivity activity) {
        this.list = list;
        this.context = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        if (i == 1) {
            View inflate = from.inflate(R.layout.item_shouye1, null);
            return new MyViewHolder(inflate);
        } else {
            View inflate = from.inflate(R.layout.item_shouye2, null);
            return new MyViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyViewHolder viewHolder1 = (MyViewHolder) viewHolder;
        viewHolder1.txt.setText(list.get(i).getUrl());

        RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(context).load(list.get(i).getUrl()).apply(options).into(viewHolder1.img);

        viewHolder1.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onItemLongClickListenr != null) {
                    onItemLongClickListenr.onitemlongclick(i);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    public void setList(ArrayList<FuLiBean.ResultsBean> list) {
        this.list = list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView txt;
        private final ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_shouye_img);
            txt = itemView.findViewById(R.id.item_shouye_txt);
        }
    }

    private OnItemLongClickListenr onItemLongClickListenr;

    public interface OnItemLongClickListenr {
        void onitemlongclick(int position);
    }

    public void setOnItemLongClickListenr(OnItemLongClickListenr onItemLongClickListenr) {
        this.onItemLongClickListenr = onItemLongClickListenr;
    }
}
