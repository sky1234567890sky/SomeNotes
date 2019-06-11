package com.example.a1.geeknewschr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.bean.gank.IsoBean;

import java.util.ArrayList;

/**
 * Created by 1 on 2019/4/22.
 */

public class RlIsoAdapter extends RecyclerView.Adapter<RlIsoAdapter.MyHolder> {

    private ArrayList<IsoBean.ResultsBean> mIsoList;
    private Context context;

    public void setIsoList(ArrayList<IsoBean.ResultsBean> pIsoList) {
        mIsoList = pIsoList;
    }

    public RlIsoAdapter(ArrayList<IsoBean.ResultsBean> pIsoList, Context pContext) {
        mIsoList = pIsoList;
        context = pContext;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_gank_iso_item, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.mAuthor.setText(mIsoList.get(position).getWho());
        holder.mTime.setText(mIsoList.get(position).getPublishedAt());
        holder.mTitle.setText(mIsoList.get(position).getDesc());
        Glide.with(context).load(mIsoList.get(position).getImages()).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return mIsoList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView mImg;
        private final TextView mTitle;
        private final TextView mTime;
        private final TextView mAuthor;

        public MyHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img_iso);
            mTitle = itemView.findViewById(R.id.iso_title);
            mTime = itemView.findViewById(R.id.iso_time);
            mAuthor = itemView.findViewById(R.id.iso_authour);
        }
    }
}
