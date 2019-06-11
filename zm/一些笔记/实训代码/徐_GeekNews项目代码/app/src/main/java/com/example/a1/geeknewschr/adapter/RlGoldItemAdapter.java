package com.example.a1.geeknewschr.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.bean.Gold_item;


import java.util.ArrayList;

/**
 * Created by 1 on 2019/5/5.
 */

public class RlGoldItemAdapter extends RecyclerView.Adapter<RlGoldItemAdapter.MyHolder> {

    private ArrayList<Gold_item.ResultsBean> mGoldItemList;
    private Context context;

    public void setGoldItemList(ArrayList<Gold_item.ResultsBean> pGoldItemList) {
        mGoldItemList = pGoldItemList;
    }

    public RlGoldItemAdapter(ArrayList<Gold_item.ResultsBean> pGoldItemList, Context pContext) {
        mGoldItemList = pGoldItemList;
        context = pContext;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.layout_gold_item,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.mTv1.setText(mGoldItemList.get(position).getDesc());
        holder.mTv2.setText(mGoldItemList.get(position).getType());
        Glide.with(context).load(mGoldItemList.get(position).getUrl()).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return mGoldItemList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final TextView mTv2;
        private final TextView mTv1;
        private final ImageView mImg;

        public MyHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img_gold_item);
            mTv1 = itemView.findViewById(R.id.tv1_gold_item);
            mTv2 = itemView.findViewById(R.id.tv2_gold_item);
        }
    }
}
